package com.xeld.cashier.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.DateUtils;
import com.xeld.cashier.utils.UiUtils;
import com.trecyclerview.holder.BaseHolder;
import com.trecyclerview.multitype.AbsItemView;

import java.util.Date;

/**
 * Created by 曹荣冠
 * on 2022/1/9.
 */
public abstract class BaseAbsViewHolder<T, VH extends BaseHolder> extends AbsItemView<T, VH> {
    public Context mContext;

    public BaseAbsViewHolder(Context context) {
        this.mContext = context;
    }

    @NonNull
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return this.createViewHolder(inflater.inflate(this.getLayoutResId(), parent, false));
    }

    public abstract int getLayoutResId();

    public abstract VH createViewHolder(View var1);


    public static void setText(TextView textView, String text) {
        CommonViewUtils.setText(textView, text);
    }

    public static void setTextPer(TextView textView, double score) {
        if (score < 1) {
            score = score * 100;
        }
        String scoreStr = CommonUtils.changeStrDouble(score);
        setText(textView, scoreStr + "%");
    }

    public static void setText(TextView textView, String text, String text2) {
        CommonViewUtils.setText(textView, text + CommonUtils.ifStringEmpty(text2));
    }

    public static void setTime(TextView textView, String type, String time) {
        time = CommonUtils.ifStringEmpty(time);
        if (time.length() > 19) {
            time = time.substring(0, 19);
        }
        if (time.contains("T")) {
            time = time.replace("T", "  ");
        }
        setText(textView, type + time);
    }

    public static void setTime(TextView textView, String time) {
        time = CommonUtils.ifStringEmpty(time);
        if (time.length() > 19) {
            time = time.substring(0, 19);
        }
        if (time.contains("T")) {
            time = time.replace("T", "  ");
        }
        setText(textView, time);
    }

    public static void setAlbumTyp(TextView textView, int albumType) {
        String str = "未归档";
        switch (albumType) {
            case 5:
                str = "居民";
                break;
            case 6:
                str = "陌生人";
                break;
            case 22:
                str = "重点人员";
                break;
            default:
                break;
        }
        setText(textView, str);
    }

    public static void setGender(TextView textView, int gender){
        switch (gender) {
            case 1:
                setText(textView, "性别：", "男");
                break;
            case 2:
                setText(textView, "性别：", "女");
                break;
            default:
                break;
        }
    }
    public static void setTimeDesc(TextView textView, String time) {
        try {
            time = CommonUtils.ifStringEmpty(time);
            if (time.length() > 19) {
                time = time.substring(0, 19);
            }
            if (time.contains("T")) {
                time = time.replace("T", "  ");
            }
            Date date = CommonUtils.stringToDate(time, DateUtils.DATE_TIME_FORMAT);
            time = DateUtils.getTimeFormatText(date);
            setText(textView, time);
        } catch (Exception e) {
            CommonViewUtils.setLog("setTimeDesc", e.getMessage());
        }
    }

    public void jumpTo(Class<?> cls) {
        UiUtils.jumpToAct(mContext, cls);
    }

}
