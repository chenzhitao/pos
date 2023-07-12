package com.xeld.cashier.utils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.mvp.utils.NetWorkUtils;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.R;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caorongguan on 2018/11/26.
 * 代码设置EditText的通用属性
 */

public class EditTextUtils {
    public static int NAME_LENGTH = 16;  //姓名长度
    public static int PHONE_LENGTH = 11;  //手机号长度
    public static int ID_CARD_LENGTH = 18; //身份证长度
    public static int CHECK_CODE_LENGTH = 6; //验证码长度
    public static int PSW = 20; //密码最大长度
    public static int ICBC = 15; //工商注册号
    //限制仅汉字 符号并没限制
    static InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!isChinese(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    };
    //限制符号
    static InputFilter filter1 = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern pattern = Pattern.compile(speChat);
            Matcher matcher = pattern.matcher(source.toString());
            if (matcher.find()) return "";
            else return null;
        }
    };

    public static void setNameEt(EditText et) {
        InputFilter[] filters = {filter, filter1, new InputFilter.LengthFilter(NAME_LENGTH)}; //最多输入16个字符
        et.setFilters(filters);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    /**
     * 判定输入汉字 符号未做区分
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static String getNameEtText(EditText et) {
        return getNameEtText(et, "");
    }

    public static String getNameEtText(EditText et, String nameDesc) {
        String name = etGetText(et);
        Context mContext = et.getContext();
        nameDesc = CommonUtils.ifStringEmpty(nameDesc);
        if (TextUtils.isEmpty(name)) {
            CommonUtils.showToast(mContext, "请输入" + nameDesc + "姓名");
            return "";
        }
        if (name.length() < 2) {
            CommonUtils.showToast(mContext, "姓名的长度不得低于两个汉字");
            return "";
        }
        return name;
    }

    public static void setPhoneEt(EditText et) {
        et.setHint("请输入手机号");
        commonEt(et, InputType.TYPE_CLASS_NUMBER, PHONE_LENGTH);
    }

    public static void setIdCardEt(EditText et) {
        et.setHint("请输入身份证号码");
        commonEt(et, InputType.TYPE_CLASS_TEXT, ID_CARD_LENGTH);
        et.setKeyListener(DigitsKeyListener.getInstance(Constant.idCardDigits));
    }

    public static void setCheckCodeEt(EditText et) {
        et.setHint("请输入验证码");
        et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        commonEt(et, InputType.TYPE_CLASS_NUMBER, CHECK_CODE_LENGTH);
    }

    public static void setPswEt(EditText et) {
        et.setHint("请输入密码");
        commonEt(et, InputType.TYPE_TEXT_VARIATION_PASSWORD, PSW);
        et.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
    }

    public static void setICBCEtText(EditText et) {
        et.setHint("请输入工商注册号");
        commonEt(et, InputType.TYPE_CLASS_NUMBER, ICBC);
    }

    public static void commonEt(EditText et, int inputType, int maxLength) {
        et.setInputType(inputType);
        //  et.setRawInputType(Configuration.KEYBOARD_QWERTY);  //默认弹出数字键盘，可输入中文字符
        InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)}; //最多输入20个字符
        et.setFilters(filters);
    }

    public static void setEtHintColor(EditText et, int id) {
        et.setHintTextColor(CommonViewUtils.getColor(et.getContext(), id));
    }


    public static String etGetText(EditText et) {
        String str = "";
        if (et == null) {
            return str;
        }
        str = et.getText().toString().trim();
        return str;
    }

    public static String getPhoneEtText(EditText et) {
        String phone = etGetText(et);
        Context mContext = et.getContext();
        if (TextUtils.isEmpty(phone)) {
            CommonUtils.showToast(mContext, "请输入手机号");
            return "";
        }
        if (!CommonUtils.isPhoneNO(phone)) {
            CommonUtils.showToast(mContext, "请输入正确的手机号");
            return "";
        }
        return phone;
    }

    public static String getPswEtText(EditText et) {
        String psw = etGetText(et);
        Context mContext = et.getContext();
        if (!isPsw(mContext, psw)) {
            return "";
        }
      /*  if (!SmsUtils.isLetterDigit(et.getContext(), psw)) {
            return "";
        }*/
        psw = md5(psw);
        return psw;
    }


    public static boolean isPsw(Context mContext, String psw) {
        if (TextUtils.isEmpty(psw)) {
            CommonUtils.showToast(mContext, "请输入密码");
            return false;
        }
        if (psw.length() < 6) {
            CommonUtils.showToast(mContext, "密码为6到20位数字或字母");
            return false;
        }
        return true;
    }

    /**
     * 201902262加密处理
     */
    public static String newMD5(String phone, String psw) {
        return md5(md5(psw) + md5(phone));
    }

    private static String md5(String str) {
        return CommonUtils.md5(str);
    }

    /**
     * 忘记密码 身份验证 提示语
     */
    public static String changeNameHint(String name) {
        name = CommonUtils.ifStringEmpty(name);
        String str = "";
        if (name.length() >= 2) {
            for (int a = 0; a < name.length(); a++) {
                if (a != name.length() - 1) {
                    str += star;
                } else {
                    str += name.substring(name.length() - 1);
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = "***";
        }
        return str + "（请输入完整姓名）";
    }

    public static boolean ifPswSame(Context context, String str1, String str2) {
        if (!TextUtils.equals(str1, str2)) {
            CommonUtils.showToast(context, "两次输入密码不一致");
            return false;
        }
        return true;
    }

    public static String getCheckCodeEtText(EditText et) {
        String code = etGetText(et);
        Context mContext = et.getContext();
        if (TextUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "请输入验证码");
            return "";
        }
        if (code.trim().length() != 6) {
            CommonUtils.showToast(mContext, "验证码错误");
            return "";
        }
        return code;
    }

    /**
     * 获取身份证号码
     */
    public static String getIDcardEtText(EditText et) {
        String code = etGetText(et);
        Context mContext = et.getContext();
        if (isIdCard(mContext, code)) {
            return code;
        } else {
            return "";
        }
    }

    public static boolean isIdCard(Context mContext, String code) {
        code = CommonUtils.ifStringEmpty(code).trim();
        if (TextUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "请输入身份证号");
            return false;
        }
        if (code.length() != ID_CARD_LENGTH) {
            CommonUtils.showToast(mContext, "身份证号码错误");
            return false;
        }
        String str17 = code.substring(0, 17);
        CommonViewUtils.setLog("str17", str17);
        if (!CommonUtils.isNum(str17)) {
            CommonUtils.showToast(mContext, "身份证号码错误");
            return false;
        }
        return true;
    }

    public static String getIDcardEtText1(EditText et) {
        String code = etGetText(et);
        if (isIdCard1(code)) {
            return code;
        } else {
            return "";
        }
    }

    public static boolean isIdCard1(String code) {
        code = CommonUtils.ifStringEmpty(code).trim();
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        if (code.length() != ID_CARD_LENGTH) {
            return false;
        }
        String str17 = code.substring(0, 17);
        CommonViewUtils.setLog("str17", str17);
        if (!CommonUtils.isNum(str17)) {
            return false;
        }
        return true;
    }


    /**
     * 获取工商注册号
     */
    public static String getICBCEtText(EditText et) {
        String code = etGetText(et);
        Context mContext = et.getContext();
        if (TextUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "请输入工商注册号");
            return "";
        }
        if (code.trim().length() < 15) {
            CommonUtils.showToast(mContext, "工商注册号码错误");
            return "";
        }
        return code;
    }

    /**
     * 获取银行卡号
     */
    public static String getBankEtText(EditText et) {
        String code = etGetText(et);
        Context mContext = et.getContext();
        if (TextUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "请输入银行卡号");
            return "";
        }
        if (code.trim().length() < 16 || code.trim().length() > 19) {
            CommonUtils.showToast(mContext, "银行卡号码错误");
            return "";
        }
        return code;
    }


    /**
     * 输入内容不能为空
     */
    public static String getContentEtText(EditText et) {
        int minLength = 15;
        String code = etGetText(et);
        CommonViewUtils.setLog("content", code);
        Context mContext = et.getContext();
        if (TextUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "输入内容不能为空");
            return "";
        }
        if (code.trim().length() < minLength) {
            CommonUtils.showToast(mContext, "输入内容不能低于" + minLength + "个字");
            return "";
        }
        return code;
    }

    public static void clearEtList(ArrayList<EditText> list) {
        if (list != null && list.size() > 0) {
            for (int a = 0; a < list.size(); a++) {
                clearFocus(list.get(a));
            }
        }
    }

    public static void requestEtList(ArrayList<EditText> list) {
        if (list != null && list.size() > 0) {
            for (int a = list.size() - 1; a > 0; a--) {  //倒序设置，这样光标会在最上方
                requestFocus(list.get(a));
            }
        }
    }


    //将光标移至文字末尾
    public static void moveCursorEnd(EditText editText) {
        if (editText == null) return;
        editText.setSelection(editText.getText().toString().trim().length());//将光标移至文字末尾
    }

    public static void clearFocus(EditText editText) {
        if (editText == null) return;
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static void clearFocus(View editText) {
        if (editText == null) return;
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static void requestFocus(EditText editText) {
        if (editText == null) return;
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
        editText.requestFocus();
    }


    /**
     * 无焦点设置身份证号
     * 注意在设置之前一定要将cardCode设置为全局变量
     */
//    public static void setIdCardWithOutFocus(EditText editText, String cardCode) {
//        // cardCode = bean.getCardCode();
//        com.cloudpolice.safetyschool.mvp.utils.EditTextUtils.requestFocus(editText);
//        // CommonViewUtils.setText(editText, EditTextUtils.changeIdCardText(cardCode));
//        CommonViewUtils.setText(editText, cardCode);
//        com.cloudpolice.safetyschool.mvp.utils.EditTextUtils.clearFocus(editText);
//    }


    /**
     * 设置监听
     */
    final static String[] str = {""};
    final static String[] str1 = {""};

    public static void setIdCardEditListener(EditText editText, OnRequestSuccess<String> listener) {
        if (editText == null) return;
        str[0] = "";
        str1[0] = "";
        editText.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!hasFocus) {
                str[0] = editText.getText().toString();
                str[0] = changeIdCardLast(str[0]);
                listener.onResult(str[0]);
                str1[0] = EditTextUtils.changeIdCardText(str[0]);
                editText.setText(str1[0]);
            } else { //再次获取到焦点
                if (TextUtils.isEmpty(str[0])) {
                    str[0] = editText.getText().toString();
                }
                CommonViewUtils.setText(editText, str[0]);
            }
        });
        CommonViewUtils.setLog("IdCard_str", str[0] + "");
        CommonViewUtils.setLog("IdCard_str1", str1[0] + "");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.hasFocus()) {
                    str[0] = s.toString();
                    str[0] = changeIdCardLast(str[0]);
                    listener.onResult(str[0]);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnClickListener(v -> {
            requestFocus(editText);
            //  moveCursorEnd(editText);
        });
    }

    public static void setIdcardWithStar(String cardCode) {
        str[0] = cardCode;
        str1[0] = changeIdCardLast(cardCode);
    }

    //当身份证的最后一位是小写的时候切换为大写
    public static String changeIdCardLast(String idCard) {
        if (idCard.length() == ID_CARD_LENGTH) {//当编辑的汉字为小写的时候切换为大写
            String lastStr = CommonUtils.getLastStr(idCard);
            if (TextUtils.equals("x", lastStr)) {
                lastStr = "X";
            }
            idCard = idCard.substring(0, ID_CARD_LENGTH - 1) + lastStr;
        }
        return idCard;
    }

    /**
     * 将context转成act
     */
    public static Activity getActFromContext(Context context) {
        Activity act = null;
        if (context instanceof Activity) {
            act = (Activity) context;
            CommonViewUtils.setLog("act", act.getClass().getSimpleName() + "");
        }
        return act;
    }

    /**
     * 将卡号中间字符隐藏
     */
    public static String star = "*";

    public static String changeIdCardText(String str) {
        return changeTextCenterToStar(str, 6, 4);
    }

    /**
     * 将文字转换成中间带星的文本。
     *
     * @param startLength 前部分显示明文的长度
     * @param endLength   后部分显示明文的长度
     */
    public static String changeTextCenterToStar(String str, int startLength, int endLength) {
        str = CommonUtils.ifStringEmpty(str);
        int length = str.length();
        int centerMaxLength = ID_CARD_LENGTH - startLength - endLength;
        String text = "", startStr, endStr = "", center;
        if (length <= startLength) {
            text = str;
        } else if (length > startLength) {
            startStr = str.substring(0, startLength);
            if (length <= centerMaxLength + startLength) {
                center = getStars(length - startLength);
            } else {
                center = getStars(centerMaxLength);
                endStr = str.substring(centerMaxLength + startLength, length);
            }
            text = startStr + center + endStr;
        }
        return text;
    }

    private static String getStars(int length) {
        String str = "";
        for (int a = 0; a < length; a++) {
            str += star;
        }
        return str;
    }


    /**
     * @param maxLength 最大文字长度
     */
    public static void setTextWatchListener(EditText editText, TextView textView, int maxLength) {
        if (editText == null) return;
        if (textView == null) return;
        if (maxLength <= 0) return;
        InputFilter emojiFilter = CommonUtils.emojiFilter(editText.getContext());
        InputFilter[] filters = {emojiFilter, new InputFilter.LengthFilter(maxLength)}; //最多输入maxLength个字符
        editText.setFilters(filters);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString() + "";
                textView.setText(str.length() + "/" + maxLength);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    /**
     * 禁用表情
     */

    public static void setEmojiFilter(EditText editText) {
        if (editText == null) return;
        InputFilter emojiFilter = CommonUtils.emojiFilter(editText.getContext());
        editText.setFilters(new InputFilter[]{emojiFilter});
    }

    /*
     * 设置edit 输入数字*/
    public static void setEditTextInput(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String string = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(string)) {
                    if (string.length() == 1 && TextUtils.equals("0", string)) {
                        editText.setText("");
                    } else {
                        if (string.substring(0, 1).equals("0")) {
                            editText.setText(string.substring(1));
                        }
                    }
                }
            }
        });
    }

    /**
     * * 计算字符串宽度超过文本框宽度的时候，显示带...的字符串
     * * @param text
     * * @param textView
     * * @return
     *     
     */
    public static String getEllipsisValue(TextView textView, String text, boolean withPoint) {
        text = CommonUtils.ifStringEmpty(text);
        text = removeEmptyText(text);
        if (!withPoint) {
            return text;
        }
        String total = "";
        float textViewWdith = textView.getWidth();
        // Log.e("xb", "-->文本框宽度：" + textViewWdith);
        boolean isCut = false;
        //循环计算追加后的字符串长度是否大于文本框长度
        for (int i = 1; i <= text.length(); i++) {
            total = text.substring(0, i);
            float totalWidth = getTextValueWidth(textView, total);
            //Log.e("xb", "-->当前字符长度：" + totalWdith + "，字符串：" + total);
            if (totalWidth > textViewWdith) {
                isCut = true;
                break;
            }
        }
        int length = 3;
        //这里判断文本框长度是为了，有时候粗心，在view没有绘制好的时候，得到的宽度为0，不然substring就蹦了
        if (isCut && total.length() > length) {
            //可以截取3，但"..."占大概2个中文字符宽度左右
            return total.substring(0, total.length() - length) + "...";
        }
        return text;
    }

    /**
     * 单行显示 多余显示...
     */
    public static void setSingleLineText(TextView textView) {
        if (textView == null) return;
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * * 获取字符串所占屏幕长度
     * * @param text
     * * @param textView
     *     
     */
    public static float getTextValueWidth(TextView textView, String text) {
        if (null == text || "".equals(text)) {
            return 0;
        }
        TextPaint textPaint = textView.getPaint();
        return textPaint.measureText(text);
    }

    /**
     * 去掉字符串中所有空格
     */
    public static String removeEmptyText(String str) {
        str = CommonUtils.ifStringEmpty(str);
        return str.trim().replaceAll(" ", "");
    }

    /**
     * 有过滤标点符号的withPoint为false
     */
    public static void setElpText(TextView textView, String text, boolean withPoint) {
        text = getEllipsisValue(textView, text, withPoint);
        CommonViewUtils.setLog("elp", text);
        if (textView == null) return;
        setSingleLineText(textView);
        CommonViewUtils.setText(textView, text);
    }

    public static void initEditLines(final LinearLayout linearLayout, final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resetEditTextHeight(linearLayout, editText, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static void onTextChanged(EditText editText, OnRequestSuccess<String> listener) {
        listener.onResult(EditTextUtils.etGetText(editText));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onResult(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 根据文字内容计算重绘EditText的高度
     *
     * @param linearLayout 父控件
     * @param editText     文本控件
     * @param contents     文字内容
     */
    private static void resetEditTextHeight(final LinearLayout linearLayout, final EditText editText, final String contents) {
        // 监听控件绘制
        ViewTreeObserver vto = editText.getViewTreeObserver();
        vto.addOnPreDrawListener(() -> {
            Boolean hasMessured = (Boolean) editText.getTag();
            if (hasMessured == false) {
                editText.setTag(true);
                int width = editText.getWidth(); // 控件宽度
                int height = editText.getHeight(); // 控件高度
                if (width != 0 && height != 0) {
                    if (!TextUtils.isEmpty(contents)) {
                        // 显示文字个数字数
                        int len = contents.length();
                        // 得到字体像素
                        float px = editText.getTextSize();
                        CommonViewUtils.setLog("lines", "字体像素：" + px + "，控件宽度：" + width);
                        double length = Math.floor(width / px); // 能容纳字母个数
                        if (len > length) {
                            int llWidth = linearLayout.getLayoutParams().width;
                            int offset = (int) (len / length); // 计算出需要行数
                            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(llWidth, (int) (height + px * offset)));
                        }
                    }
                }
            }
            return true;
        });
    }

    public static void checkIp(EditText editText, OnRequestSuccess<Integer> listener) {
        String result = EditTextUtils.etGetText(editText);
        if (!TextUtils.isEmpty(result)) {
            NetWorkUtils.isNetWorkAvailable(result, (Boolean aBoolean) -> {
                        CommonViewUtils.setLog("checkIp", aBoolean.toString());
                        if (aBoolean) {//网络正常
                            PreferencesUtil.save(editText.getContext(), Constant.CHECK_IP_KEY, result);
                        } else {//网络错误
                            CommonViewUtils.showToast("您输入的服务器地址或端口号有误");
                        }
                        listener.onResult(aBoolean ? 0 : 1);
                        return 0;
                    }
            );
        } else {
            CommonViewUtils.showToast("请输入服务器地址及端口号");
            listener.onResult(-1);
        }
    }

    /**
     * 设置密码显示隐藏
     */
    public static void setPswHide(EditText etPsw, ImageView eyeIcon, boolean ifShow) {
        if (ifShow) {//如果选中，显示密码
            etPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {//否则隐藏密码
            etPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        eyeIcon.setImageResource(ifShow ? R.mipmap.icon_eye_black : R.mipmap.icon_eye);
        EditTextUtils.moveCursorEnd(etPsw);
    }


}
