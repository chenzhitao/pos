package com.xeld.cashier.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xeld.cashier.BaseAct;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;

/**
 * Created by caorongguan on 2018-04-02.
 */

public class KeyboardUtil {
    /**
     * 最有效的管理软键盘的方法
     */
    public static void showKeyboard(Activity activity, boolean isShow) {
        BaseAct baseActivity = null;
        try {
            baseActivity = (BaseAct) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baseActivity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == imm)
            return;
        View currentFocus = baseActivity.getCurrentFocus();
        if (isShow) {
            if (currentFocus != null) {
                //有焦点打开
                imm.showSoftInput(currentFocus, 0);
            } else {
                //无焦点打开
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        } else {
            if (currentFocus != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }

    /**
     * 隐藏输入法键盘
     *
     * @param activity
     */
    public static void closeInputKeyboard(Activity activity) {
        showKeyboard(activity, false);
    }

    private static KeyboardUtil sKeyboardUtil = new KeyboardUtil();

    public static KeyboardUtil getInstance() {
        return sKeyboardUtil;
    }

    private static int lastheight = 0;
    private static int keyboardHeight = 300;//软键盘的高度

    public static void setListener(Activity activity, OnKeyboardListener listener) {
        setOnKeyboardListener(listener);
        //拿到页面的共同布局
        final View decorView = activity.getWindow().getDecorView();
        //设置最底层布局的变化监听
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            //int measuredHeight = decorView.getMeasuredHeight();//拿尺寸不好用
            Rect rect = new Rect();
            //拿这个控件在屏幕上的可见区域
            decorView.getWindowVisibleDisplayFrame(rect);
            int height = rect.height();
            //第一次刚进来的时候,给上一次的可见高度赋一个初始值,
            // 然后不需要再做什么比较了,直接return即可
            if (lastheight == 0) {
                lastheight = height;
                return;
            }
            //当前这一次的可见高度比上一次的可见高度要小(有比较大的高度差,大于300了),
            // 认为是软键盘弹出
            if (lastheight - height > keyboardHeight) {
                //隐藏这个RoomFragment中的控件
                if (mOnKeyboardListener != null) {
                    mOnKeyboardListener.onSoftKeyboardStatueChange(true);
                }
            }
            //当前这一次的可见高度比上一次的可见高度要大,认为是软键盘收缩
            if (height - lastheight > keyboardHeight) {
                if (mOnKeyboardListener != null) {
                    mOnKeyboardListener.onSoftKeyboardStatueChange(true);
                }
            }
            //记录下来
            lastheight = height;
        });
    }

    private static OnKeyboardListener mOnKeyboardListener;

    public static void setOnKeyboardListener(OnKeyboardListener keyboardListener) {
        mOnKeyboardListener = keyboardListener;
    }

    public interface OnKeyboardListener {
        void onSoftKeyboardStatueChange(boolean ifOpen);
    }

    public static void scrollDown(final boolean ifDown, final ScrollView scrollView) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ifDown) {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                } else {
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                }
            }
        }, 50);
    }

    public void setKeyboardListener(Activity context, final ScrollView scrollView) {
        setListener(context, (ifOpen) -> {
            scrollDown(ifOpen, scrollView);
        });
    }

    /**
     * 修改输入法回车键为搜索。
     * android:imeOptions 和 android:singleLine 注意这两个属性要加上！虽然现在 singleLine API 过期了，
     * 但是设置新的API maxLine = 1 无效，所以还是需要设置 singleLine = true，否则不生效！！！
     */
    public static void changeKeyEnterWithSearch(Activity activity, EditText editText, OnRequestSuccess<String> listener) {
        editText.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            //判断是否是搜索键
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //隐藏软键盘
                showKeyboard(activity, false);
                if (listener != null) {
                    String text = EditTextUtils.etGetText(editText);
                    CommonViewUtils.showToast(text);
                    listener.onResult(text);
                }
                return true;
            }
            return false;
        });
    }


}
