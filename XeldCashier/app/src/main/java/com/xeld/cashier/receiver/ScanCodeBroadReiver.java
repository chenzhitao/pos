package com.xeld.cashier.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.callback.SanCodeCallback;
import com.xeld.cashier.constant.Constant;

import org.greenrobot.eventbus.EventBus;

public class ScanCodeBroadReiver extends BroadcastReceiver {

//    private SanCodeCallback sanCodeCallback;
//
//    public ScanCodeBroadReiver(SanCodeCallback sanCodeCallback) {
//        this.sanCodeCallback = sanCodeCallback;
//    }

    private boolean isOnePost = false;
    private int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constant.ACTION_DATA_CODE_RECEIVED.equals(intent.getAction())) {
            count++;
            String code = intent.getStringExtra(Constant.DATA);
            StringBuffer buffer = new StringBuffer(code);
            buffer.deleteCharAt(buffer.length() - 1);
            String codestr = buffer.toString();
            byte[] arr = intent.getByteArrayExtra(Constant.SOURCE);

//            if (count == 1) {
////                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
//                XLog.d("个推收到广播1");
//            } else if (count == 2) {
////                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
//                XLog.d("个推收到广播2");
//            }

//            Toast.makeText(context, code, Toast.LENGTH_SHORT).show();
            if (count == 1) {

                if (code != null && !code.isEmpty()) {
                    BaseEventBean bean = new BaseEventBean(BaseEventBean.SCAN_CODE_MSG);
                    bean.setValue(codestr);
                    EventBus.getDefault().post(bean);
                    count = 0;
                }
            }

        } else if (Constant.ACTION_GETUI_PAY_MSG.equals(intent.getAction())) {
            XLog.d("个推收到广播");
            BaseEventBean bean = new BaseEventBean(BaseEventBean.GETUI_PUSH_MSG);
            String msg = intent.getStringExtra("getui");
            if (!"".equals(msg))
                bean.setValue(msg);

            EventBus.getDefault().post(bean);
        }
    }

}
