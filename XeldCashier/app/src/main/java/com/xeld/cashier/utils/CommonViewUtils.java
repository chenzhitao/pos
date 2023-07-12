package com.xeld.cashier.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xeld.cashier.App;
import com.xeld.cashier.R;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by caorongguan on 2018/11/9.
 */

public class CommonViewUtils {
    private static ArrayList<TextView> list;
    private static ArrayList<EditText> etList;

    private static boolean isFirst = true;

    public static void logError(Exception e) {
        Log.e("test_commonViewUtils", e.toString());
    }

    public static void setLog(String key, String value) {
        CommonUtils.setLog(key, value + "  ……");
    }

    public static void setLogHttp(String key, String value) {
        Log.e("Http_" + key, value + "");
    }

    public static void setLog(String key, List list) {
        CommonUtils.setLog(key, list == null ? "null" : list.size() + "");
    }

    public static void setV(View view, boolean isV) {
        if (view == null) return;
        if (isV) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void setGone(View view, boolean isV) {
        if (view == null) return;
        if (isV) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void showToast(String str) {
        CommonUtils.showToast(App.getContext(), str);
    }


    /**
     * 将指定text变色。
     */
    public static void changeSomeTextColor(TextView tv, String text) {
        SpannableString newString = new SpannableString(text);
        if (text.contains("/")) {
            int startPosition = text.indexOf("(") + 1;
            int endPosition = text.indexOf("/");
            newString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFE74F18")),
                    startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(newString);
    }

    /**
     * 统一的点击事件的方法
     */
    public static void setOnClick(View view, View.OnClickListener listener) {
        if (view == null) return;
        view.setOnClickListener(v -> {
            if (!CommonUtils.isFastDoubleClick()) {
                listener.onClick(v);
            }
        });
    }

    static Toast toast;

    @SuppressLint("WrongConstant")
    public static void showToast(Context context, String msg) {
        if (context == null) return;
        if (TextUtils.isEmpty(msg)) return;
        if (msg.startsWith("null")) return;
        Log.e("test_toast", msg);
        String text = msg;
        if (msg.startsWith("token")) {
            text = "会话已失效，请重新登录";
            //  EventBus.getDefault().post(new BaseEventBean(BaseEventBean.TYPE_TOKEN_INVALID));
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(text);
        }

    }

    @SuppressLint("WrongConstant")
    public static void showToast(Context context, int msgCode) {
        if (context == null) return;
        Toast.makeText(context, msgCode, Toast.LENGTH_SHORT).show();

    }

    public static void getInputEnterResult(Activity act, EditText et) {
        et.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            //判断是否是“完成”键
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                case EditorInfo.IME_ACTION_SEARCH:
                case EditorInfo.IME_ACTION_GO:
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    KeyboardUtil.closeInputKeyboard(act);
                    return true;
                default:
                    break;
            }
            return false;
        });
    }

    public static void setText(TextView textView, String text) {
        CommonUtils.setText(textView, text);
    }

    public static int getColor(Context context, int id) {
        return ContextCompat.getColor(context, id);
    }

    public static int getColorBase(Context mContext, int id) {
        return ContextCompat.getColor(mContext, id);
    }

    public static Drawable getDrawableBase(Context mContext, int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    public static void loadCircleImg(ImageView imageView, String url) {
        if (!isNull(imageView)) {
            Glide.with(imageView.getContext()).asBitmap().apply(getOption()).load(url).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    private static boolean isNull(ImageView imageView) {
        return imageView == null;
    }
    public static RequestOptions getOption() {
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(false);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.priority(Priority.HIGH);
        options.error(R.mipmap.icon_img_bg);
        //设置占位符,默认
        options.placeholder(R.mipmap.icon_img_bg);
        //设置错误符,默认
        options.error(R.mipmap.icon_img_bg);
        return options;
    }
}
