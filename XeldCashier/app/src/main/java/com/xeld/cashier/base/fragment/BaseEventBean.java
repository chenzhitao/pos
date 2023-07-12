package com.xeld.cashier.base.fragment;

import android.os.Bundle;

import java.util.HashMap;

public class BaseEventBean {
    public static final int TYPE_FINISH_HOME = 99;//关闭主页
    public static final int TYPE_TOKEN_INVALID = 100;//token
    public static final int TYPE_TOKEN_LOGIN_OUT = 101;//token失效
    public static final int TYPE_CHANGE_ADD_MONITOR = 102;//切换添加布控后的列表展示
    public static final int TYPE_CHANGE_STATUS = 103;//历史记录切换状态
    public static final int TYPE_CHANGE_STATUS_EXPANDABLE = 104;//可扩展历史记录切换状态
    public static final int TYPE_SHOW_MONITOR_REPORT = 105;//全局弹框
    public static final int TYPE_CLOSE_MONITOR_REPORT = 106;//关闭全局弹框
    public static final int TYPE_CLOSE_MONITOR_DIALOG = 107;//关闭全局弹框 刷新首页数据
    /**
     * 人像文字检索
     */
    public static final int TYPE_SELECT_FACE_IMG_RESULT = 108;//选择人脸 返回结果
    public static final int TYPE_SELECT_LIB_IMG_RESULT = 109;//选择布控库 返回结果
    public static final int TYPE_SEARCH_HISTORY = 110;//搜索返回结果。
    /**
     * 同行分析
     */
    public static final int TYPE_SELECT_LIB_IMG_RESULT_TOGETHER = 1091;//选择布控库 返回结果
    public static final int TYPE_SEARCH_HISTORY_TOGETHER = 1101;//搜索返回结果。

    public static final int HOME_PAY_FRAGMENT = 1102;//pay fragment
    public static final int TYPE_GOTO_HOME_FRAGMENT = 1103;//pay fragment
    public static final int TYPE_GOTO_MEMBER_FRAGMENT = 1104;//member fragment
    public static final int TYPE_GOTO_ADD_MEMBER_FRAGMENT = 1105;//member fragment
    public static final int TYPE_GOTO_PAY_FRAGMENT = 1106;//pay fragment
    public static final int PAY_SUSSESS_CLEAR_CRAT = 1107;//支付成功以后，清空购物车，注销会员信息

    public static final int TYPE_GRIDVIEW_ITEM_CLICK = 1108;//gridView click
    public static final int SCAN_CODE_MSG = 1109;//gridView click
    public static final int GETUI_PUSH_MSG = 1110;//geitui
    public static final int RECHARGE_MEMBER_FRAGMENT = 1111;//recharge fragment
    public static final int UPDATE_MEMBER_FRAGMENT = 1112;//update member info
    public static final int TEMP_ORDER_DATA = 1113;//update member info
    public static final int AGAIN_ORDER = 1114;//重新提交订单


    private Bundle serializableData;
    private int type;
    private Object value;

    public void setValue(Object value) {
        this.value = value;
    }

    private HashMap<String, Object> data;

    public BaseEventBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = new HashMap<>();
        this.data.putAll(data);
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void put(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setSerializableData(Bundle serializableData) {
        this.serializableData = serializableData;
    }

    public Bundle getSerializableData() {
        return serializableData;
    }
}
