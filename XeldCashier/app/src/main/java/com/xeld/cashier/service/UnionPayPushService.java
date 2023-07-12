package com.xeld.cashier.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.elvishew.xlog.XLog;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.utils.PreferencesUtil;

public class UnionPayPushService extends GTIntentService {
    // 接收 cid
    @Override
    public void onReceiveClientId(Context context, String s) {
        XLog.d("onReceiveClientId = " + s);
        PreferencesUtil.save(context, Constant.SP_CID, s);
    }

    @Override
    public void onReceiveServicePid(Context context, int i) {
        XLog.d("onReceiveServicePid = " + i);
    }

    // 处理透传消息
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        XLog.d("onReceiveMessageData getMessageId = " + gtTransmitMessage.getMessageId());
        XLog.d("onReceiveMessageData getPayloadId = " + gtTransmitMessage.getPayloadId());
        XLog.d("onReceiveMessageData getPayload = " + gtTransmitMessage.getPayload());
        XLog.d("onReceiveMessageData getTaskId = " + gtTransmitMessage.getTaskId());
        XLog.d("onReceiveMessageData getTaskId = " + gtTransmitMessage.getPkgName());
        System.out.println("日志打印开始");
        String text = new String(gtTransmitMessage.getPayload());

        XLog.d("onReceiveMessageData getPayload text = " + text);
        XLog.d("onReceiveMessageData getPayload text = " + text);

        gtTransmitMessage.getPkgName();

        Intent intent = new Intent();
        intent.putExtra("getui", text);
        intent.setAction("com.xeld.getui.pay.msg");
        sendBroadcast(intent);
        System.out.println("日志打印结束");
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    // 各种事件处理回执
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        XLog.d("onReceiveCommandResult getAppid = " + cmdMessage.getAppid());
        XLog.d("onReceiveCommandResult getClientId = " + cmdMessage.getClientId());
        XLog.d("onReceiveCommandResult getPkgName = " + cmdMessage.getPkgName());
        XLog.d("onReceiveCommandResult getAction = " + cmdMessage.getAction());
    }

    // 通知到达，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
        XLog.d("onNotificationMessageArrived getMessageId = " + msg.getMessageId());
        XLog.d("onNotificationMessageArrived getContent = " + msg.getContent());
        XLog.d("onNotificationMessageArrived getTaskId = " + msg.getTaskId());
        XLog.d("onNotificationMessageArrived getTitle = " + msg.getTitle());

        Intent intent = new Intent();
        intent.setAction("com.xeld.getui.pay.msg");
        sendBroadcast(intent);

    }

    // 通知点击，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {

        XLog.d("onNotificationMessageClicked getMessageId = " + msg.getMessageId());
        XLog.d("onNotificationMessageClicked getContent = " + msg.getContent());
        XLog.d("onNotificationMessageClicked getTaskId = " + msg.getTaskId());
        XLog.d("onNotificationMessageClicked getTitle = " + msg.getTitle());
    }
}
