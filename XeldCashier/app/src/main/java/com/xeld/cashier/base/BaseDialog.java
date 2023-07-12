package com.xeld.cashier.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.xeld.cashier.R;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.ScreenUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;

/**
 * create by 曹荣冠
 * on 2019/12/19
 */
public abstract class BaseDialog extends Dialog {
    private int animations = R.style.main_menu_animStyle;
    protected Unbinder unBinder;
    protected Context mContext;
    public OnRequestSuccess<Integer> listener;
    protected int SCREEN_WIDTH;
    protected int SCREEN_HEIGHT;
    public int popMaxHeight;

    public BaseDialog(@NonNull Context mContext, int animations) {
        super(mContext, R.style.alert_dialog);
        this.mContext = mContext;
        this.animations = animations;
        popMaxHeight = ScreenUtils.getScreenHeight(mContext) / 2;
    }

    public BaseDialog(@NonNull Context mContext) {
        super(mContext, R.style.alert_dialog);
        this.mContext = mContext;
        popMaxHeight = ScreenUtils.getScreenHeight(mContext) / 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDimension();
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        Window window = this.getWindow();
        window.setLayout(setDialogWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(getGravity());

        if (needAnimations()) {
            window.setWindowAnimations(animations);
        }
        initView();
    }

    public void setListener(OnRequestSuccess<Integer> listener) {
        this.listener = listener;
    }

    protected abstract int getLayoutId();

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected boolean needAnimations() {
        return false;
    }

    protected abstract void initView();


    protected int setDialogWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        if (unBinder != null) {
//            unBinder.unbind();
//        }
    }



    public boolean ifIntercept = false;

    /**
     * 拦截外部点击事件
     * 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
     */
    public void setOutSideTouch(boolean ifIntercept) {
        this.ifIntercept = ifIntercept;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        //点击弹窗外部区域
        if (isOutOfBounds(getContext(), event)) {
            if (ifIntercept) {
                return true;
            } else {
                dismiss();
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();//相对弹窗左上角的x坐标
        final int y = (int) event.getY();//相对弹窗左上角的y坐标
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();//最小识别距离
        final View decorView = getWindow().getDecorView();//弹窗的根View
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }


    public void showDialog() {
        show();
        //7.0以后需要show了以后再设置dialog的宽高
        WindowManager.LayoutParams params = getWindow().getAttributes();
        // params.height = 270;
        params.width = ScreenUtils.getScreenWidth(mContext) * 2 / 3; //设置为屏幕的2/3
        getWindow().setAttributes(params);
        // getWindow().setBackgroundDrawable(mAct.getResources().getDrawable(R.drawable.shape_loading_dialog));
        getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_white_4dp));
    }

    public void showDialog(int height) {
        show();
        //7.0以后需要show了以后再设置dialog的宽高
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (height > popMaxHeight) {
            height = popMaxHeight;
        }
        params.width = ScreenUtils.getScreenWidth(mContext) * 2 / 3; //设置为屏幕的2/3
        params.height = height;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_white_4dp));
    }

    public int getColorBase(int id) {
        return ContextCompat.getColor(mContext, id);
    }

    public Drawable getDrawableBase(int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    public void setText(TextView textView, String text) {
        CommonUtils.setText(textView, text);
    }

    public String getTime(String time) {
        time = CommonUtils.ifStringEmpty(time);
        if (time.length() > 19) {
            time = time.substring(0, 19);
        }
        return time;
    }


    private void getDimension() {
        /** 获取屏幕的宽和高 */
        DisplayMetrics dm = new DisplayMetrics();
        Activity activity = (Activity) mContext;
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
    }



}
