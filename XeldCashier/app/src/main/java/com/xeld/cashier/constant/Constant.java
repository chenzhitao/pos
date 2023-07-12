package com.xeld.cashier.constant;

import android.text.TextUtils;


import com.elvishew.xlog.XLog;
import com.xeld.cashier.App;
import com.xeld.cashier.bean.LoginBean;
import com.xeld.cashier.utils.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caorongguan on 2019/5/6.
 */

public class Constant {
    // 主页面导航菜单
    public static String digits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String idCardDigits = "0123456789xX";
    public static String fileProvider = "com.xeld.cashier.fileprovider";
    public static String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576747094827&di=e4cb3e1224bf9891a50768b02f9c24f9&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F28%2F2018062880925_TLjik.jpeg";


    public static String PHONE_KEY = "phone";
    public static String ACCOUNT_STR = "";
    public static String PSW = "psw";
    public static String TOKEN_KEY = "Authorization";
    public static String CHECK_IP_KEY = "checkIp"; //获取需要切换的ip键
    public static String REMEMBER_PSW_KEY = "if_remember_psw";
    public static String AUTO_LOGIN = "if_auto_login";
    public static String DEFAULT_HTTP = "default_http";
    public static String SP_LOGIN_ID = "loginId";
    public static String BOTTOM_TIPS = "鄂ICP备18006287号 湖北云警科技有限公司";
    public static String IF_SHOW_WARNING_POP = "if_show_warning_pop";
    public static String IF_SHOW_WARNING_BEAR = "if_show_warning_bear";
    public static String roleTypeRemark = ""; //默认 0家长，1教务，教师   多个家长逗号分隔   当既是领导又是老师默认显示高优先级的领导
    public static String gradeClsStr = ""; //年级 班级
    public static String token = "";
    public static String shopId = "";
    public static String userId = "";
    public static String cid = "";

    public static int nodeId = 0;
    public static int areaId = 0;
    public static int inAddressId = 0;
    public static int residentBasicId = 0;
    public static boolean isTeacher = true; // 区分教务还是教师
    public static final String[] token11 = new String[1];
    public static LoginBean.DataBean loginBean = null;
    // request参数
    public static final int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    public static final int REQ_PERM_CAMERA = 11003; // 打开摄像头
    public static final int REQ_PERM_EXTERNAL_STORAGE = 11004; // 读写文件
    public static boolean isFirst = true;
    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
    //  public static int versionType = 0;//0为不需要更新，1为有更新，2为强制更新。
    public static int locationHistoryCount = 30;//本利历史记录数量
    public static final String SP_TOKEN = "token";
    public static final String SP_SHOPID = "shopId";
    public static final String SP_USERID = "userId";
    public static final String SP_CID = "cid";
    public static final String SP_SHOPOWNER = "shopOwner";
    public static final String SP_SHOPONAME = "shopName";
    public static final String SP_SHOPOADDRESS = "shopAddress";
    public static final String SP_SHOPOMOBILE = "mobile";
    public static final String SP_LISTITEM_ID = "itemID";

    //监听 商米收银设备 扫码枪的广播
    public static final String ACTION_DATA_CODE_RECEIVED =
            "com.sunmi.scanner.ACTION_DATA_CODE_RECEIVED";
    public static final String ACTION_GETUI_PAY_MSG = "com.xeld.getui.pay.msg";
    //监听 商米收银设备 扫码枪的广播 key
    public static final String DATA = "data";
    //监听 商米收银设备 扫码枪的广播 key
    public static final String SOURCE = "source_byte";


    public static boolean isLogOut = false;//是否主动退出
//    public static SearchHistoryBean fieldBean;//个人信息

//    public static List<DeviceBean> pop3TypeList = new ArrayList<>();
//    public static ArrayList<String> pop3List = new ArrayList<>();
//
//    public static SearchHistoryBean getFieldBean() {
//        if (fieldBean == null)
//            fieldBean = new SearchHistoryBean();
//        return fieldBean;
//    }


    public static String getToken() {

//        if (TextUtils.isEmpty(token)) {
        //这种方式会导致 token不能刷新
//            token = PreferencesUtil.getString(App.getContext(), SP_TOKEN);
//        }
        token = PreferencesUtil.getString(App.getContext(), SP_TOKEN);
        return token;
    }

    public static String getShopId() {
        if (TextUtils.isEmpty(shopId)) {
            shopId = PreferencesUtil.getString(App.getContext(), SP_SHOPID);
        }
        return shopId;
    }

    public static String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            userId = PreferencesUtil.getString(App.getContext(), SP_USERID);
        }
        return userId;
    }

    public static String getCid() {
        if (TextUtils.isEmpty(userId)) {
            cid = PreferencesUtil.getString(App.getContext(), SP_CID);
            XLog.e(cid);
        }
        return cid;
    }

}
