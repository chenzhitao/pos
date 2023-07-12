package com.xeld.cashier.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * author : caorongguan
 * date : 2019/1/23 10:11
 * description : 处理时间 日期相关
 */
@SuppressLint("WrongConstant")
public class DateUtils {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";//时间
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private static long month = 31 * day;// 月 月大
    private static long year = 365 * day;// 年    31536 000 000
    private static long februaryMouth = 28 * day;// 润年二月
    private static long smallMonth = 30 * day;// 小月
    private static long currentMouth = 31 * day;// 当前月 默认为闰月
    private static int[] bigMothList = new int[]{1, 3, 5, 7, 8, 10, 12};
    private static int[] smallMothList = new int[]{4, 6, 9, 11};
    private static boolean isScheme = false,//闰年
            mathSuccess = true; //初始化精准计算数据是否成功，如不成功，则使用粗略计算：年阀值为365天，月阀值为31天
    /**
     * 距离时间，跨年需要的总时长，跨月需要的总时长
     */
    private static long diff = 0, overYearDiff = 0, overMouthDiff = 0;
    private static int yearCount1, mouthCount1, dayCount1;
    /**
     * yearInt 发布年
     * mouthInt 发布月
     * cYearInt 当前年
     * cMouthInt 当前月
     */
    private static int yearInt, mouthInt, dayInt, cYearInt, cMouthInt, cDayInt;
    private static int needPlusDays;//包含闰年的个数 也即需要添加的天数
    private static String testText = "1979-03-29 00:00:00";

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @param
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (mathSuccess) {//计算成功
            if (yearCount1 > 0) {
                long yearCount = overYearMath();
                if (yearCount > 0) {
                    return yearCount + "年前";
                }
            }
            if (diff > februaryMouth) {//超过28天 就有可能跨月 小于12个月
                long mouthCount = overMouthMath();
                if (mouthCount > 0) {
                    return mouthCount + "个月前";
                }
            }
        } else { //计算失败的处理
            if (diff > year) { //超过1年
                r = (diff / year);
                return r + "年前";
            }
            if (diff > month) {
                r = (diff / month);
                return r + "个月前";
            }
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }


    /**
     * 跨月判断阀值计算
     */
    private static long overMouthMath() {
        long count = 0;
        List<Integer> mouthList = getMouthCount();  //计算时长阀值 long
        long ss = diff - overMouthDiff;   //ss为跨月所需总月份时长
        if (ss >= 0) {
            count = mouthList.size();
        } else {
            count = mouthList.size() - 1;
        }
        return count % 12;
    }

    /**
     * 跨年阀值计算 单位年
     */
    private static long overYearMath() {
        long count = 0;
        //超过1年  仅需判断包含几个润年
        List<Integer> list = getYearCount();
        long ss = diff - overYearDiff;
        if (ss >= 0) {
            count = list.size();
        } else {
            count = list.size() - 1;
        }
        return count;
    }

    /**
     * 年内获取每月的月份集合 不存在mouthCount1为0的情况，前面已经做了判断
     * 在此已计算好跨月所需的总时长 overMouthDiff
     */
    private static List<Integer> getMouthCount() {
        ArrayList<Integer> list = new ArrayList<>();
        if (mouthCount1 < 0) {
            mouthCount1 += 12;
        }
        overMouthDiff = 0;
        for (int b = 0; b < mouthCount1; b++) {
            int mouth = (b + mouthInt) % 12;
            list.add(mouth);
            if (mouth == 2) {//二月  已做过平/闰年处理
                overMouthDiff += currentMouth;
            } else if (Arrays.asList(bigMothList).contains(mouth)) { //大月
                overMouthDiff += month;
            } else {  //小月
                overMouthDiff += smallMonth;
            }
        }
        return list;
    }

    /**
     * 获取包含的年数集合 不存在yearCount1为0的情况，前面已经做了判断
     * 在此已计算好跨年所需的总时长 overYearDiff
     */
    private static List<Integer> getYearCount() {
        ArrayList<Integer> list = new ArrayList<>();
        if (yearCount1 > 0) {
            for (int b = 0; b < yearCount1; b++) {
                int mouth = (b + yearInt);
                list.add(mouth);
                if (yearInt % 4 == 0) {
                    needPlusDays++;
                }
            }
        }
        overYearDiff = (list.size() * year) + (needPlusDays * day);//这么些年实际需要的时间long类型
        return list;
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @param
     * @return
     */
    public static String getTimeFormatText(String date) {
        if (date == null) {
            return null;
        }
        diff = new Date().getTime() - timeStrToLong(date);
        String[] currentTimeArray = date.split(" ");
        long r = 0;
        if (diff > day) {
            r = (diff / day);
            return currentTimeArray[0];
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /*
     * 获取当前日期 XXXX-YY-MM
     * */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getCurrentDate1() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(date);
    }

    /*
     * 获取当前年月XXXX-YY
     * */
    public static String getCurrentY() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    /*
     * 获取当前年月XXXX-YY
     * */
    public static String getCurrentYM() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /*
     * 获取当前时间
     * */
    public static String getCurrentTime() {
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(date);
        return currentTime;
    }

    /*
     * 将日期转换成时间毫秒  date yyyy-MM-dd
     * */
    public static long dateStrToLong(String d) {
        if (TextUtils.isEmpty(d)) {
            return 0;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateStart = dateFormat.parse(d);
            return dateStart.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * 将日期转换成日历date  yyyy-MM-dd
     * */
    public static Calendar dateStringToCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        String[] starts = date.split("-");
        int year = Integer.valueOf(starts[0]);
        int month = Integer.valueOf(starts[1]);
        int day = Integer.valueOf(starts[2]);
        calendar.set(year, month - 1, day);
        return calendar;
    }

    /*
     * 将日历转换成日期date  yyyy-MM-dd
     * */
    public static String calendarTodateString(Calendar calendar) {
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH) + 1;
        int startDd = calendar.get(Calendar.DATE);

        String m = startMonth >= 10 ? startMonth + "" : "0" + startMonth;
        String d = startDd >= 10 ? startDd + "" : "0" + startDd;
        String startDate = startYear + "-" + m + "-" + d;
        return startDate;
    }

    /*
     * 将日期转换成时间毫秒  date yyyy-MM-dd HH:mm:ss
     * */
    public static long timeStrToLong(String d) {
        if (TextUtils.isEmpty(d)) {
            return 0;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = dateFormat.parse(d);
            return dateStart.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Calendar initCalendar() {
        Calendar cerStartDate = Calendar.getInstance();
        cerStartDate.set(cerStartDate.get(Calendar.YEAR), cerStartDate.get(Calendar.MONTH), cerStartDate.get(Calendar.DATE));
        return cerStartDate;
    }

    /*
     * 将时分转换成日期格式  date yyyy-MM-dd HH:mm:ss
     * */
    public static String HMTodate(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            boolean isNextDay = false;
            if (d.contains("次日")) {
                d = d.replace("次日", "");
                isNextDay = true;
            }
            Date dateStart = dateFormat.parse(d);
            Date date = null;
            if (isNextDay) {
                date = new Date(dateStart.getTime() + 24 * 60 * 60 * 1000);
            } else {
                date = new Date(dateStart.getTime());
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ 转换为时分
     * */
    public static String dateToHMString(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        d = d.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /*

     * */
    public static String dateToHMSString(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        d = d.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取小时
     *
     * @param d
     * @return
     */
    public static String dateToHStr(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        d = d.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取小时
     *
     * @param d
     * @return
     */
    public static String dateToYDM(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        d = d.replace("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Date getCruData(String d) {

        if (TextUtils.isEmpty(d)) return null;

        d = DateUtils.replaceT(d);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ 转换为年月日
     * */
    public static String dateToYMDString(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String dateTYMDStr(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /*
     * 年月日*/
    public static String getDate(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        String date = "";
        if (time.length() >= 10) {
            date = time.substring(0, 10);
        }
        return date;
    }

    /*
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ 转换为年月日时分秒
     * */
    public static String dateToTime(String d) {
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date dateStart = null;
        try {
            dateStart = format.parse(d);
            Date date = new Date(dateStart.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Calendar getMaxCalendar() {
        Calendar end = Calendar.getInstance();
        end.set(2030, 12, 30);
        return end;
    }

    public static Calendar getStartCalendar() {
        Calendar end = Calendar.getInstance();
        end.set(2019, 1, 3);
        return end;
    }

    /**
     * 选择开始时间和结束时间逻辑判断
     */
    public static boolean compareStartEndTime(Context mContext, String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime)) {
            CommonViewUtils.showToast(mContext, "请先选择开始时间");
            return false;
        }
        if (TextUtils.isEmpty(endTime)) {
            CommonViewUtils.showToast("请选择结束时间");
            return false;
        }
        if (startTime.compareTo(endTime) > 0) {
            CommonViewUtils.showToast(mContext, "开始时间不能晚于结束时间");
            return false;
        }
        return true;
    }

    /*
     * 毫秒转换成时分秒时长
     * */
    public static String getTimeString(long time) {
        long temp = time / 1000;
        long hour = temp / 60 / 60;
        String h = "00";
        String m = "00";
        String s = "00";
        if (hour > 0) {
            if (hour >= 10) {
                h = hour + "";
            } else {
                h = "0" + hour;
            }
            long minute = (temp - hour * 60 * 60) / 60;
            if (minute > 0) {
                if (minute >= 10) {
                    m = minute + "";
                } else {
                    m = "0" + minute;
                }
                long second = temp - hour * 60 * 60 - minute * 60;
                if (second >= 10) {
                    s = second + "";
                } else {
                    s = "0" + second;
                }
            } else {
                long second = temp - hour * 60 * 60;
                if (second >= 10) {
                    s = second + "";
                } else {
                    s = "0" + second;
                }
            }
        } else {
            long minute = temp / 60;
            if (minute > 0) {
                if (minute >= 10) {
                    m = minute + "";
                } else {
                    m = "0" + minute;
                }
                long second = temp - minute * 60;
                if (second >= 10) {
                    s = second + "";
                } else {
                    s = "0" + second;
                }
            } else {
                if (temp >= 10) {
                    s = temp + "";
                } else {
                    s = "0" + temp;
                }
            }
        }
        String timeString = h + ":" + m + ":" + s;
        return timeString;
    }

    /**
     * 获取年
     */
    public static String getYearFromTime(String str) {
        String year = "";
        str = CommonUtils.ifStringEmpty(str);
        if (str.length() > 4) {
            year = str.substring(0, 4);
        }
        return year;
    }

    /**
     * 获取过去X天的日期  带时间
     */
    public static String getYestSevenDayTime(int addDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, addDay);
        Date d = c.getTime();
        String day = format.format(d);
        day = replaceT(day);
        CommonUtils.setLog("getYestSevenDayTime", day);
        return day;
    }


    /**
     * 获取过去X天的日期  不带时间
     */
    public static String getYestSevenDayTime1(int addDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, addDay);
        Date d = c.getTime();
        String day = format.format(d);
        day = replaceT(day);
        CommonUtils.setLog("getYestSevenDayTime", day);
        return day;
    }

    public static String replaceT(String day) {
        day = day.replace(" ", "T");
        return day;
    }

    public static String replaceTString(String day) {
        if (!TextUtils.isEmpty(day) && day.contains("T")) {
            day = day.replace("T", " ");
        }
        return day;
    }

    public static String getCurrentDateT() {
        String startTime = CommonUtils.getCurrentDateTime();
        return replaceT(startTime);
    }


    /**
     * 指定时间，然后设置推迟一个月
     *
     * @param amount
     */
    public static String formatSpecifyDelayMonthTime(String timeString, int amount, String formatStr) {
        if (TextUtils.isEmpty(timeString))
            return "";

        SimpleDateFormat formatter1 = new SimpleDateFormat(formatStr);
        Date date = null;//取时间
        try {
            date = formatter1.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, amount);
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateString = formatter.format(date);
        System.out.println(dateString);
        return dateString;
    }


    /**
     * 计算时间差  返回距离还有多少天
     *
     * @param starTime 开始时间
     * @param endTime  结束时间
     *                 返回类型 ==1----天，时，分。 ==2----时
     * @return 返回时间差
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - parse.getTime();
            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");
//            long hour1 = diff / (60 * 60 * 1000);
//            String hourString = hour1 + "";
//            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
//            timeString = hour1 + "小时" + min1 + "分";
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");
            timeString = String.valueOf(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeString;

    }

    public static long getBetweenDateCount(String startTime, String endTime) {
        //设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long betweenDate = 0;
        try {
            if ((!TextUtils.isEmpty(startTime) && (!TextUtils.isEmpty(endTime)))) {
                Date startDate = sdf.parse(startTime);
                Date endDate = sdf.parse(endTime);
                //得到相差的天数 betweenDate
                betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);
            }
        } catch (Exception e) {
            CommonViewUtils.setLog("getBetweenDate", e.getMessage());
        } finally {
            return betweenDate;
        }
    }
    //https://blog.csdn.net/wsjzzcbq/article/details/84569845


    /**
     * 指定时间，然后设置推迟几天
     *
     * @param amount
     */
    public static String formatSpecifyDelayTime(String timeString, int amount, String formatStr) {
        if (TextUtils.isEmpty(timeString))
            return "";

        SimpleDateFormat formatter1 = new SimpleDateFormat(formatStr);
        Date date = null;//取时间
        try {
            date = formatter1.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, amount);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateString = formatter.format(date);
        System.out.println(dateString);
        return dateString;
    }
}
