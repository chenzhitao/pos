package com.xeld.cashier.mvp.http;


import com.xeld.cashier.App;
import com.xeld.cashier.constant.Constant;

/**
 * Created by caorongguan on 2019/5/5.
 */

public class URL {
    //    public static final String DEBUG_URL = "https://test.51xeld.com";  //开发
    public static final String DEBUG_URL = "https://app.xiaoerlangdang.com";  //正式
//    public static final String DEBUG_URL = "http://192.168.10.6:8088";  //开发
    //public static final String DEBUG_URL = "http://113.57.119.203:9997";  //开发
//    public static final String UPDATE_VERSION_URL = "http://cloud.hbyjtech.com:2640";  //自动更新
    public static final String UPDATE_VERSION_URL = "https://wise.hbyjtech.com/";  //自动更新
    private static final String RELEASE_URL = "http://113.57.119.203:9997";  //正式
    public static final String RELEASE_URL1 = "http://cloud.hbyjtech.com:18080";  //钟换辅助统计接口ip地址 正式域名
    //  public static final String RELEASE_URL1 = "http://192.168.1.94:18080";  //钟换辅助统计接口ip地址
    public static final String BASE_URL = App.debug ? DEBUG_URL : RELEASE_URL;

    public static final String GET_OPENED_CITY_LIST = "/area/getOpenedCityList";
    public static final String APP_VERSION_UPDATE = "/system_service/appVersion/checkAndroidNeedUpdate";//APP版本更新

    //    public static String CHECK_VERSION = "api2/community/version/getOne/6";
    public static String CHECK_VERSION = "/app/homeData/getAppVersion";


    //e 上传类型, resident:居民, black: 黑名单, search: 搜索
    public static final String UPLOAD_FACE1 = "/api/business/upload/face/resident";        //上传头像 4.1.1
    public static final String UPLOAD_FACE2 = "/api/business/upload/face/black";        //上传头像 4.1.1
    public static final String UPLOAD_FACE3 = "/api/business/upload/face/search";        //上传头像4.1.1

    // public static final String LOGIN = "/api/manage/user/login";
    //登录
    public static final String LOGIN = "/login";
    //获取产品信息
    public static final String GETPRODUCTS = "/app/appProduct/getProducts";
    public static final String GETPRODUCTBYBARCODE = "/app/homeData/getProductByBarCode";
    //根据条形码获取某一个商品
    public static final String GETPRODUCTBYSHOPID = "/app/homeData/getProductByShopId";
    //获取产品分类
    public static final String GETCATEGORY = "/app/homeData/getCategory";

    public static final String CONFIRMORDERURL = "/app/order/confirmOrder";
    //提交订单
    public static final String UNIONPAYURL = "/app/order/confirmOrder";

    //添加会员信息
    public static final String ADDNEWMEMBER = "/app/homeData/addNewMember";
    //获取会员信息
    public static final String GETMEMBER = "/app/homeData/getMember";

    public static final String GETALLORDERBYSHOPID = "/app/order/getAllOrderByshopId";
    public static final String CASH_PAYMENT = "/app/pay/payment";
    public static final String DEPOSITMONEY = "/app/depositMoney/insertDepositMoneyRecord";
    public static final String DEPOSITMONEYBYCASH = "/app/depositMoney/depositMoneyByCash";
    public static final String DEPOSITMONEYBYUMS = "/app/depositMoney/depositMoneyByUms";
    //退款
    public static final String REFUND = "/app/payOrder/refund";
    //撤销
    public static final String BACKOUT = "/app/payOrder/backOut";
    //发送验证码
    public static final String LOGINBYSENDSMS = "/p/sms/loginbySendSms";
    //获取店铺信息
    public static final String APPSHOPDETAIL = "/app/appShopDetail/info";
    public static final String USERSHIFTSTATISTICS = "/app/appCashRegisterSystemUser/userShiftStatistics";
    public static final String ADDPRODUCT = "/app/appProduct/addProduct";
    public static final String GETUSERBYUSERID = "/app/homeData/getUserByUserId";

    public static final String ORDERBUCHA = "/app/pay/bucha";
    public static final String SELECTDEPOSITMONEYRECORD = "/app/depositMoney/selectDepositMoneyRecord";

    public static final String PRODUCEQRCODE = "/app/appProduct/produceQRCode";
    public static final String GETTOPAIDORDER = "/app/order/getToPaidOrder";


}