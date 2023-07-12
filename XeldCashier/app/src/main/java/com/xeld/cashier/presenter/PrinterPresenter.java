package com.xeld.cashier.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sunmi.devicemanager.cons.PrtCts;
import com.sunmi.devicemanager.device.Device;
import com.sunmi.devicesdk.core.PrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.xeld.cashier.R;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.MenuBean;
import com.xeld.cashier.bean.RechargeBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.fragment.HomePayFragment;
import com.xeld.cashier.utils.BigDecimalUtils;
import com.xeld.cashier.utils.BitmapUtils;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xeld.cashier.utils.ResourcesUtils;
import com.xeld.cashier.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//import com.sunmi.sunmit2demo.dialog.PayDialog;
//import com.sunmi.sunmit2demo.ui.MainActivity;


/**
 * Created by zhicheng.liu on 2018/4/4
 * address :liuzhicheng@sunmi.com
 * description :
 */

public class PrinterPresenter {
    private Context context;
    private static final String TAG = "PrinterPresenter";
    public SunmiPrinterService printerService;
    private PrinterManager mManager;

    private RecordsBean relustOrder;
    private HomeMemberInfoBean.DataBean memberInfoBean;


    public void setMemberInfoBean(HomeMemberInfoBean.DataBean memberInfoBean) {
        this.memberInfoBean = memberInfoBean;
    }

    public RecordsBean getRelustOrder() {
        return relustOrder;
    }

    public void setRelustOrder(RecordsBean relustOrder) {
        this.relustOrder = relustOrder;
    }

    public PrinterPresenter(Context context, SunmiPrinterService printerService) {
        this.context = context;
        this.printerService = printerService;
        mManager = PrinterManager.getInstance();
    }

    public void print(final String json, final int payMode) {
        if (printerService == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                MenuBean menuBean = JSON.parseObject(json, MenuBean.class);
                int fontsizeTitle = 40;
                int fontsizeContent = 30;
                int fontsizeFoot = 35;
                String divide = "************************************" + "\n";

                String divide2 = "--------------------------------------" + "\n";

                if (true) {
                    divide = "************************************" + "\n";
                    divide2 = "--------------------------------------" + "\n";
                }


                int width = divide2.length();
                String goods = formatTitle(width);
                try {
                    if (printerService.updatePrinterState() != 1) {
                        return;
                    }

                    if (payMode == 0) {
//                        try {
//                            printerService.openDrawer(null);
//                        } catch (RemoteException e) {
//                            e.printStackTrace();
//                        }
                    }
                    printerService.setAlignment(1, null);
                    printerService.sendRAWData(boldOn(), null);
                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.menus_title) + "\n" + ResourcesUtils.getString(context, R.string.print_proofs) + "\n", "", fontsizeTitle, null);
                    printerService.setAlignment(0, null);
                    printerService.sendRAWData(boldOff(), null);
                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    // printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_number) + SystemClock.uptimeMillis() + "\n", "", fontsizeContent, null);
                    if (relustOrder != null) {
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_number) + relustOrder.getOrderNumber() + "\n", "", fontsizeContent, null);

                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_time) + relustOrder.getCreateTime() + "\n", "", fontsizeContent, null);
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_payment_method), "", fontsizeContent, null);
                        switch (payMode) {
                            case HomePayFragment.PAY_TYPE_CARH:
                                printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_money) + "\n", "", fontsizeContent, null);
                                break;
                            case HomePayFragment.PAY_TYPE_CODE:
                                printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_code) + "\n", "", fontsizeContent, null);
                                break;
                            case HomePayFragment.PAY_TYPE_MEMBER:
                                printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_member) + "\n", "", fontsizeContent, null);
                                break;
                            case HomePayFragment.PAY_TYPE_MEMBER_OLD:
                                printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_member) + "\n", "", fontsizeContent, null);
                                break;
                            case HomePayFragment.PAY_TYPE_MEMBER_HHZF:
                                printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_member_hhzf) + "\n", "", fontsizeContent, null);
                                break;
                            default:
                                break;
                        }
                    }

                    // printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_money) + "\n", "", fontsizeContent, null);

                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    printerService.printTextWithFont(goods + "\n", "", fontsizeContent, null);
                    printerService.printTextWithFont(divide2, "", fontsizeContent, null);
                    //打印商品信息
                    printGoods(menuBean, fontsizeContent, divide2, payMode, width);

                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    printerService.sendRAWData(boldOn(), null);

                    if (memberInfoBean != null) {
                        printerService.printTextWithFont(divide, "", fontsizeContent, null);
                        printerService.sendRAWData(boldOff(), null);
                        printerService.printTextWithFont("会员名称:" + memberInfoBean.getNickName() + "\n", "", fontsizeContent, null);
                        printerService.printTextWithFont("手机号码:" + replaceMobile(memberInfoBean.getUserMobile()) + "\n", "", fontsizeContent, null);

                        if (memberInfoBean.getOldAccount() > 0) {
//                            printerService.printTextWithFont("原账户余额:" + memberInfoBean.getOldAccount() + "\n", "", fontsizeContent, null);

                            String accountMoney = BigDecimalUtils.add(memberInfoBean.getOldAccount(), memberInfoBean.getUserAccount(), 2);
                            printerService.printTextWithFont("账户余额:" + accountMoney + "\n", "", fontsizeContent, null);
                        } else {
                            printerService.printTextWithFont("账户余额:" + memberInfoBean.getUserAccount() + "\n", "", fontsizeContent, null);
                        }
                    }
                    printerService.printText("\n", null);

                    if (payMode != 0 && payMode != 1) {
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_tips_havemoney), "", fontsizeFoot, null);
                    } else {
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_tips_nomoney), "", fontsizeFoot, null);
                    }
                    printerService.sendRAWData(boldOff(), null);
                    printerService.lineWrap(1, null);

                    printerService.printTextWithFont(divide, "", fontsizeContent, null);

                    String shopName = PreferencesUtil.getString(context, Constant.SP_SHOPONAME);
                    String shopAddress = PreferencesUtil.getString(context, Constant.SP_SHOPOADDRESS);
                    String mobile = PreferencesUtil.getString(context, Constant.SP_SHOPOMOBILE);


                    printerService.printTextWithFont("店铺名称:" + shopName + "\n", "", fontsizeContent, null);
                    printerService.printTextWithFont("店铺地址:" + shopAddress + "\n", "", fontsizeContent, null);
                    printerService.printTextWithFont("联系方式:" + mobile + "\n", "", fontsizeContent, null);

                    printerService.lineWrap(1, null);


                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xeld_gzh_code);
                    if (bitmap.getWidth() > 384) {
                        int newHeight = (int) (1.0 * bitmap.getHeight() * 384 / bitmap.getWidth());
//                        bitmap = BitmapUtils.scale(bitmap, 384, newHeight);
                    }

                    bitmap = BitmapUtils.scale(bitmap, 184, 184);
                    printerService.printBitmap(bitmap, null);
                    printerService.printText("\n", null);
                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_gzh_tips), "", fontsizeContent, null);

                    printerService.printText("\n\n", null);
                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_thanks), "", fontsizeContent, null);

                    printerService.lineWrap(4, null);
                    printerService.cutPaper(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void printByDeviceManager(String json, int payMode, Device device) {
        if (mManager == null) return;
        mManager.setDefaultDevice(device);
        mManager.enter(true);
        mManager.initPrinter();
        MenuBean menuBean = JSON.parseObject(json, MenuBean.class);
        try {
            mManager.addBold(true);
            mManager.addTextSizeDouble();
            mManager.addTextAtCenter(ResourcesUtils.getString(context, R.string.menus_title));
            mManager.addTextAtCenter(ResourcesUtils.getString(context, R.string.print_proofs));
            mManager.addTextSizeNormal();
            mManager.addBold(false);
            mManager.addFeedDots(10);
            mManager.addHorizontalCharLine('*');
            mManager.addText(ResourcesUtils.getString(context, R.string.print_order_number) + SystemClock.uptimeMillis());
            mManager.addFeedDots(10);
            mManager.addText(ResourcesUtils.getString(context, R.string.print_order_time) + formatData(new Date()));
            mManager.addFeedDots(10);
            String mode = null;
//            switch (payMode) {
//                case PayDialog.PAY_MODE_0:
//                    mode = ResourcesUtils.getString(context, R.string.pay_money);
//                    break;
//                case PayDialog.PAY_MODE_5:
//                case PayDialog.PAY_MODE_2:
//                    mode = ResourcesUtils.getString(context, R.string.pay_face);
//                    break;
//                case PayDialog.PAY_MODE_1:
//                case PayDialog.PAY_MODE_3:
//                case PayDialog.PAY_MODE_4:
//                    mode = ResourcesUtils.getString(context, R.string.pay_code);
//                    break;
//                default:
//                    break;
//            }
            mode = ResourcesUtils.getString(context, R.string.pay_money);
            mManager.addText(ResourcesUtils.getString(context, R.string.print_payment_method) + mode);
            mManager.addHorizontalCharLine('*');
            mManager.addFeedDots(10);
            String[] title = {
                    ResourcesUtils.getString(context, R.string.shop_car_goods_name),
                    ResourcesUtils.getString(context, R.string.menus_unit_price),
                    ResourcesUtils.getString(context, R.string.menus_unit_num),
                    ResourcesUtils.getString(context, R.string.shop_car_unit_money),
            };
            mManager.addTextsAutoWrap(new float[]{3, 1, 2, 2},
                    new int[]{PrtCts.ALIGN_LEFT, PrtCts.ALIGN_CENTER, PrtCts.ALIGN_CENTER, PrtCts.ALIGN_RIGHT}
                    , title);
            mManager.addFeedDots(10);
            mManager.addHorizontalLine(0);
            mManager.addFeedDots(10);
            printGoodsByDeviceManager(menuBean);
            mManager.addFeedDots(10);
            mManager.addHorizontalCharLine('*');
            mManager.addBold(true);
            mManager.addTextSizeDouble();
            if (payMode != 0 && payMode != 1) {
                mManager.addText(ResourcesUtils.getString(context, R.string.print_tips_havemoney));
            } else {
                mManager.addText(ResourcesUtils.getString(context, R.string.print_tips_nomoney));
            }
            mManager.addTextSizeDouble();
            mManager.addBold(false);
            mManager.addFeedLine(1);

//            String wifi = SunmiOpenServiceWrapper.getInstance().getSunmilinkDynamicInfo();
//            if (!TextUtils.isEmpty(wifi)) {
//                mManager.addAlignCenter();
//                SunmiLink sunmiLink = JSON.parseObject(wifi, SunmiLink.class);
//                mManager.addText("Wi-Fi" + (isZh() ? "名称:" : ":") + sunmiLink.getData().getSsid());
//                mManager.addText((isZh() ? "Wi-Fi密码:" : "Password:") + sunmiLink.getData().getPassword());
//                mManager.addFeedLine(2);
//                mManager.addAlignLeft();
//            }

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_xeld_logo);
            if (bitmap.getWidth() > 384) {
                int newHeight = (int) (1.0 * bitmap.getHeight() * 384 / bitmap.getWidth());
//                bitmap = BitmapUtils.scale(bitmap, 384, newHeight);
            }
            bitmap = BitmapUtils.scale(bitmap, 284, 284);
            mManager.addImage(bitmap);
            mManager.addFeedLine(1);
            mManager.addText(ResourcesUtils.getString(context, R.string.print_thanks));
            mManager.addFeedLine(6);
            mManager.addCutter();
            mManager.commit(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printGoodsByDeviceManager(MenuBean menuBean) {
        for (MenuBean.ListBean listBean : menuBean.getList()) {
            int num;
            if (listBean.getType() == 1) {
                num = (int) (listBean.getNet() / 1000.000f);
            } else {
                num = 1;
            }
            mManager.addTextsAutoWrap(new float[]{3, 1, 2, 2},
                    new int[]{PrtCts.ALIGN_LEFT, PrtCts.ALIGN_CENTER, PrtCts.ALIGN_CENTER, PrtCts.ALIGN_RIGHT},
                    new String[]{listBean.getParam2(),
                            listBean.getParam3().replace(ResourcesUtils.getString(context, R.string.units_money), ""),
                            num + "",
                            listBean.getParam3().replace(ResourcesUtils.getString(context, R.string.units_money), "").trim()});
            mManager.addFeedDots(10);
        }
        mManager.addHorizontalLine(0);
        mManager.addFeedDots(10);
        String total = ResourcesUtils.getString(context, R.string.print_total_payment);
        String real = ResourcesUtils.getString(context, R.string.print_real_payment);
        mManager.addTextLeftRight(total, ResourcesUtils.getString(context, R.string.units_money_units) + menuBean.getKVPList().get(0).getValue());
        mManager.addFeedDots(10);
        mManager.addTextLeftRight(real, ResourcesUtils.getString(context, R.string.units_money_units) + /*PayDialog.PayMoney*/100);
    }

    private String formatTitle(int width) {
        Log.e("@@@@@", width + "=======");

        String[] title = {
                ResourcesUtils.getString(context, R.string.shop_car_goods_name),
                ResourcesUtils.getString(context, R.string.menus_unit_price),
                ResourcesUtils.getString(context, R.string.menus_unit_num),
                ResourcesUtils.getString(context, R.string.shop_car_unit_money),
        };
        StringBuffer sb = new StringBuffer();
        int blank1 = width * 1 / 3 - String_length(title[0]);
        int blank2 = width * 1 / 4 - String_length(title[1]);
        int blank3 = width * 1 / 4 - String_length(title[2]);

        sb.append(title[0]);
        sb.append(addblank(blank1));

        sb.append(title[1]);
        sb.append(addblank(blank2));

        sb.append(title[2]);
        sb.append(addblank(blank3));

        sb.append(title[3]);

        int w1 = width / 3;
        int w2 = width / 3 + 2;
        String str = String.format("%-" + w1 + "s%-" + w2 + "s%s", title[0], title[1], title[2]);
        return sb.toString();
    }

    private void printNewline(String str, int width, int fontsizeContent) throws RemoteException {
        List<String> strings = Utils.getStrList(str, width);
        for (String string : strings) {
            printerService.printTextWithFont(string + "\n", "", fontsizeContent, null);
        }
    }

    private void printGoods(MenuBean menuBean, int fontsizeContent, String divide2, int payMode, int width) throws RemoteException {
        int blank1;
        int blank2;
        int blank3;
        int maxNameWidth = isZh() ? (width * 1 / 3 - 2) / 2 : (width * 1 / 3 - 2);

        StringBuffer sb = new StringBuffer();
        for (MenuBean.ListBean listBean : menuBean.getList()) {
            sb.setLength(0);

            String name = listBean.getParam2();
            String name1 = name.length() > maxNameWidth ? name.substring(0, maxNameWidth) : "";

            blank1 = width * 1 / 3 - String_length(name.length() > maxNameWidth ? name1 : name) + 1;

            blank2 = width * 1 / 4 - String_length(listBean.getParam3().replace(ResourcesUtils.getString(context, R.string.units_money), ""));

            sb.append(name.length() > maxNameWidth ? name1 : name);
            sb.append(addblank(blank1));

            sb.append(listBean.getParam3().replace(ResourcesUtils.getString(context, R.string.units_money), ""));
            sb.append(addblank(blank2));

            if (listBean.getType() == 1) {
                sb.append(listBean.getNet() / 1000.000f);
                blank3 = width * 1 / 4 - (listBean.getNet() / 1000.000f + "").length();
            } else {
                sb.append(listBean.getNet());//商品数量
                blank3 = width * 1 / 4 - 1;
            }

            sb.append(addblank(blank3));
//            sb.append(listBean.getParam3());

            sb.append(autoalAmountPrice(listBean.getParam3(), listBean.getNet()));

            printerService.printTextWithFont(sb.toString() + "\n", "", fontsizeContent, null);

            if (name.length() > maxNameWidth) {
                printNewline(name.substring(maxNameWidth), maxNameWidth, fontsizeContent);
            }

        }
        printerService.printTextWithFont(divide2, "", fontsizeContent, null);
        String total = ResourcesUtils.getString(context, R.string.print_total_payment);
        String real = ResourcesUtils.getString(context, R.string.print_real_payment);

        sb.setLength(0);
        blank1 = width * 5 / 6 - String_length(total) - menuBean.getKVPList().get(0).getValue().length();
        blank2 = width * 5 / 6 - String_length(real) - menuBean.getKVPList().get(0).getValue().length();
        ;
        sb.append(total);
        sb.append(addblank(blank1));
        sb.append(ResourcesUtils.getString(context, R.string.units_money_units));
        sb.append(menuBean.getKVPList().get(0).getValue());

        printerService.printTextWithFont(sb.toString() + "\n", "", fontsizeContent, null);
        sb.setLength(0);
        sb.append(real);
        sb.append(addblank(blank2));
        sb.append(ResourcesUtils.getString(context, R.string.units_money_units));
        sb.append(menuBean.getKVPList().get(3).getValue());

//        sb.append(PayDialog.PayMoney);


        printerService.printTextWithFont(sb.toString() + "\n", "", fontsizeContent, null);
        sb.setLength(0);
    }

    public void printMemberInfo(HomeMemberInfoBean.DataBean memberInfoBean, RechargeBean bean, final int payMode) {
        if (printerService == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                int fontsizeTitle = 40;
                int fontsizeContent = 30;
                int fontsizeFoot = 35;
                String divide = "************************************" + "\n";


                String divide2 = "--------------------------------------" + "\n";

                String divide3 = "************消费之前****************" + "\n";

                String divide4 = "************消费之后****************" + "\n";

                if (true) {
                    divide = "************************************" + "\n";
                    divide2 = "--------------------------------------" + "\n";
                }


                int width = divide2.length();

                try {
                    if (printerService.updatePrinterState() != 1) {
                        return;
                    }

                    if (payMode == 0) {
//                        try {
//                            printerService.openDrawer(null);
//                        } catch (RemoteException e) {
//                            e.printStackTrace();
//                        }
                    }
                    printerService.setAlignment(1, null);
                    printerService.sendRAWData(boldOn(), null);
                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.menus_title) + "\n" + ResourcesUtils.getString(context, R.string.print_recharge_order) + "\n", "", fontsizeTitle, null);
                    printerService.setAlignment(0, null);
                    printerService.sendRAWData(boldOff(), null);
                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    // printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_number) + SystemClock.uptimeMillis() + "\n", "", fontsizeContent, null);

                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_number) + bean.getData() + "\n", "", fontsizeContent, null);

                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_order_time) + formatData(new Date()) + "\n", "", fontsizeContent, null);

                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_payment_method), "", fontsizeContent, null);

                    switch (payMode) {
                        case HomePayFragment.PAY_TYPE_CARH:
                            printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_money) + "\n", "", fontsizeContent, null);
                            break;
                        case HomePayFragment.PAY_TYPE_CODE:
                            printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.pay_code) + "\n", "", fontsizeContent, null);
                            break;
                        default:
                            break;
                    }

//                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    printerService.printTextWithFont(divide2, "", fontsizeContent, null);


                    if (memberInfoBean != null) {
//                        printerService.printTextWithFont(divide, "", fontsizeContent, null);
//                        printerService.printTextWithFont(divide3, "", fontsizeContent, null);
//                        printerService.sendRAWData(boldOff(), null);
//                        printerService.printTextWithFont("会员名称:" + memberInfoBean.getNickName() + "\n", "", fontsizeContent, null);
//                        printerService.printTextWithFont("账户余额:" + memberInfoBean.getUserAccount() + "\n", "", fontsizeContent, null);
//                        if (memberInfoBean.getOldAccount() > 0) {
//                            printerService.printTextWithFont("原账户余额:" + memberInfoBean.getOldAccount() + "\n", "", fontsizeContent, null);
//                        }

                        printerService.printTextWithFont(divide4, "", fontsizeContent, null);
                        printerService.printTextWithFont("会员名称:" + memberInfoBean.getNickName() + "\n", "", fontsizeContent, null);
                        printerService.printTextWithFont("手机号码:" + replaceMobile(memberInfoBean.getUserMobile()) + "\n", "", fontsizeContent, null);

                        if (memberInfoBean.getOldAccount() > 0) {
//                            printerService.printTextWithFont("原账户余额:" + memberInfoBean.getOldAccount() + "\n", "", fontsizeContent, null);

                            String accountMoney = BigDecimalUtils.add(memberInfoBean.getOldAccount(), memberInfoBean.getUserAccount(), 2);
                            printerService.printTextWithFont("账户余额:" + accountMoney + "\n", "", fontsizeContent, null);
                        } else {
                            printerService.printTextWithFont("账户余额:" + memberInfoBean.getUserAccount() + "\n", "", fontsizeContent, null);
                        }

                    }
                    printerService.printTextWithFont(divide, "", fontsizeContent, null);
                    printerService.printText("\n", null);

                    if (payMode != 0 && payMode != 1) {
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_tips_havemoney), "", fontsizeFoot, null);
                    } else {
                        printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_tips_nomoney), "", fontsizeFoot, null);
                    }
                    printerService.sendRAWData(boldOff(), null);
                    printerService.lineWrap(1, null);

                    printerService.printTextWithFont(divide, "", fontsizeContent, null);

                    String shopName = PreferencesUtil.getString(context, Constant.SP_SHOPONAME);
                    String shopAddress = PreferencesUtil.getString(context, Constant.SP_SHOPOADDRESS);
                    String mobile = PreferencesUtil.getString(context, Constant.SP_SHOPOMOBILE);


                    printerService.printTextWithFont("店铺名称:" + shopName + "\n", "", fontsizeContent, null);
                    printerService.printTextWithFont("店铺地址:" + shopAddress + "\n", "", fontsizeContent, null);
                    printerService.printTextWithFont("联系方式:" + mobile + "\n", "", fontsizeContent, null);

                    printerService.lineWrap(1, null);

                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xeld_gzh_code);
                    if (bitmap.getWidth() > 384) {
                        int newHeight = (int) (1.0 * bitmap.getHeight() * 384 / bitmap.getWidth());
//                        bitmap = BitmapUtils.scale(bitmap, 384, newHeight);
                    }
                    bitmap = BitmapUtils.scale(bitmap, 184, 184);
                    printerService.printBitmap(bitmap, null);
                    printerService.printText("\n", null);

                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_gzh_tips), "", fontsizeContent, null);

                    printerService.printText("\n\n", null);
                    printerService.printTextWithFont(ResourcesUtils.getString(context, R.string.print_thanks), "", fontsizeContent, null);

                    printerService.lineWrap(4, null);
                    printerService.cutPaper(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private String formatData(Date nowTime) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return time.format(nowTime);
    }

    private String addblank(int count) {
        String st = "";
        if (count < 0) {
            count = 0;
        }
        for (int i = 0; i < count; i++) {
            st = st + " ";
        }
        return st;
    }

    private static final byte ESC = 0x1B;// Escape

    /**
     * 字体加粗
     */
    private byte[] boldOn() {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 69;
        result[2] = 0xF;
        return result;
    }

    /**
     * 取消字体加粗
     */
    private byte[] boldOff() {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 69;
        return result;
    }

    private boolean isZh() {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language.endsWith("zh");
    }

    private int String_length(String rawString) {
        return rawString.replaceAll("[\\u4e00-\\u9fa5]", "SH").length();
    }

    private String autoalAmountPrice(String money, int net) {
        String strAmount = "";
        try {
            double price = Double.parseDouble(money);
            double amountPrice = BigDecimalUtils.mul(price, net, 2);
            strAmount = amountPrice + "";

        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        return strAmount;
    }

    private String replaceMobile(String mobile) {
        String newMobile = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        return newMobile;
    }
}
