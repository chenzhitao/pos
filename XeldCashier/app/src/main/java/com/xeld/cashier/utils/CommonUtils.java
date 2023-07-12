package com.xeld.cashier.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.xeld.cashier.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 曹荣冠 on 2017/10/25.
 */

public class CommonUtils {
    final String reg = "^1[0-9]{10}$";
    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static int bitmapLength;

    //1、在子线程里面执行任务
    public static void runInThread(Runnable task) {
        threadPool.execute(task);
    }

    //2、创建Handler对象，把消息发送到主线程的消息队列里
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static Handler getHandler() {
        return handler;
    }

    //3、在主线程里面执行任务
    public static void myRunOnUIThread(Runnable task) {
        handler.post(task);
    }


    //5、dp-->px
    public static float dpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    //6、sp-->px
    public static float spToPx(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //7、估算浮点型的值
    public static Float evaluateFloat(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }

    //11、获取当前当前时间
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        return format.format(new Date());
    }

    /**
     * 判断手机号
     */
    public static boolean isPhoneNO(String string) {
        //判断手机号，是否1开头，的11位数字。
        final String reg = "^1[0-9]{10}$";
        // final String reg="^[1][3578][0-9]{9}$";
        return string.matches(reg);
    }


    /**
     * 以下代码可以跳转到应用详情，可以通过应用详情跳转到权限界面(6.0系统测试可用)
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(localIntent);
    }


    private static float scale;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (pxValue / scale + 0.5f);
    }

    public static String setMd5(String psw, String phone) {
        return md5(md5(psw) + md5(phone));
    }

    /**
     * 计算字符串的MD5值
     *
     * @param string MD5值
     * @return
     */
    public static String md5(String string) {
        return md5(string, SLAT);
    }

    public static String SLAT = "mechanicalOwner@weiweikeji.com";

    private static String md5(String string, String slat) {
        String str = "";
        slat = ""; //加密暂时不用SLAT
        if (TextUtils.isEmpty(string)) {
            return str;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            Log.e("md5", "加密失败  " + e.toString());
        }
        return str;
    }


    /**
     * 计算文件的MD5
     *
     * @param file 文件
     * @return
     */
    public static String md5(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte[] buffer = new byte[8192];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 返回文件的md5值
     * <p>
     * 要加密的文件的路径
     *
     * @return 文件的md5值
     */
    public static String getFileMD5(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            //获取MD5加密器
            MessageDigest md = MessageDigest.getInstance("md5");
            //类似读取文件
            byte[] bytes = new byte[10240];//一次读取写入10k
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {//从原目的地读取数据
                //把数据写到md加密器，类比fos.write(bytes, 0, len);
                md.update(bytes, 0, len);
            }
            //读完整个文件数据，并写到md加密器中
            byte[] digest = md.digest();//完成加密，得到md5值，但是是byte类型的。还要做最后的转换
            for (byte b : digest) {//遍历字节，把每个字节拼接起来
                //把每个字节转换成16进制数
                int d = b & 0xff;//只保留后两位数
                String herString = Integer.toHexString(d);//把int类型数据转为16进制字符串表示
                //如果只有一位，则在前面补0.让其也是两位
                if (herString.length() == 1) {//字节高4位为0
                    herString = "0" + herString;//拼接字符串，拼成两位表示
                }
                sb.append(herString);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
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


    /**
     * 根据bitmap的方向旋转
     *
     * @param angle
     * @param bitmap
     */
    public static Bitmap rotateImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * @param isQrCode true 是二维码，false 是图片
     */
    public static String getFileName(boolean isQrCode) {
        String str = "";
        String randomNumber = String.valueOf(Math.abs(new Random().nextInt()) % 100 + 1);//生成100以内非0正随机整数
        String str2 = getStringCurrentDetailDate() + randomNumber + ".png";
        if (isQrCode) {
            str = "qr_" + str2;
        } else {
            str = "image_" + str2;
        }
        return str;
    }

    /**
     * @param ifInsertMap 是否插入图库
     */
    public static File saveBitmap(Context context, final Bitmap bm, boolean ifInsertMap) {
        if (context == null) return null;
        if (bm == null) return null;
        File dcimDir = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DCIM);
        if (!dcimDir.exists()) {
            dcimDir.mkdir();
        }
        //创建存放图片的目录
        File photoDir = new File(dcimDir, context.getResources().getString(R.string.app_name));
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
        //创建保存图片的文件
        String photoFileName = getFileName(false);
        File photoFile = new File(photoDir, photoFileName);
        try {
            photoFile.createNewFile();
            FileOutputStream out = new FileOutputStream(photoFile);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            String path = photoFile.getAbsolutePath();
            Log.e("path", path);
            if (ifInsertMap) {
                //将图片文件保存到图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), bm, context.getResources().getString(R.string.app_name), null);
                // mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(path)));
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
                Log.e("path", "图片已保存到图库");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return photoFile;
    }

//    public static void saveFile(File file) {
//        if (FileUtil.isFile(file)) {
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//            saveBitmap(App.getContext(), bitmap, true);
//        }
//    }

    private static int mathQuality() {
        int length = 0;
        if (bitmapLength != 0) {
            length = bitmapLength;
        }
        Log.e("test_length", length + "");
        double quality = 100; //默认不压缩，只有图片过大才压缩
        if (length > 300) {
            quality = 20000 / length;
        }
        Log.e("test_length_quality", length + " , " + quality);
        length = doubleToInt(quality);
        if (length > 100) length = 100;
        if (length <= 0) length = 50;
        Log.e("test_length_quality", length + " , " + quality);
        return length;
    }
    //blog.csdn.net/jason_996/article/details/80833540    int numInt= formatDoubleTOInt(num,100.0);

    /**
     * 精准计算double转int ,四舍五入。
     */
    public static int doubleToInt(double num) {
        return formatDoubleTOInt(num, 100.0);
    }

    public static int formatDoubleTOInt(double dou1, double dou2) {
        BigDecimal big1 = new BigDecimal(Double.valueOf(dou1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal big2 = new BigDecimal(Double.valueOf(dou2));
        return big1.multiply(big2).intValue();
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public static String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }


    /**
     * 旋转图片
     */
    public static Bitmap xuanZhuan(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        // 设置旋转角度
        matrix.setRotate(-90);
        // 重新绘制Bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }

    /***
     * 质量压缩
     * */

    public static Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 90;
        //循环判断如果压缩后图片是否大于100kb,大于继续压缩
        bitmapLength = baos.toByteArray().length / 1024;
        while (baos.toByteArray().length / 1024 > 100) {
            //重置baos即清空baos
            baos.reset();
            //这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            //每次都减少10
            options -= 10;
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //把ByteArrayInputStream数据生成图片
        Bitmap compBitmap = BitmapFactory.decodeStream(bais, null, null);
        return compBitmap;
    }

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";                    //时间
    public static final String DATE_TIME_FORMAT_MOUTH = "yyyy-MM";                    //月
    public static final String DATE_TIME_FORMAT_DAY = "yyyy-MM-dd";                    //日

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 通过路径压缩图片
     */
    public static Bitmap compressPath(String path) {
        //先将所选图片转化为流的形式，path所得到的图片路径
        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);//此时返回bm为空
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;//这里设置高度为800f
            float ww = 480f;//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(path, newOpts);
            return compressBitmap(bitmap);//压缩好比例大小后再进行质量压缩
        } catch (Exception e) {

        }


        return null;
    }


    /**
     * 给时间加上几个小时
     *
     * @param day  当前时间 格式：yyyy-MM-dd hh:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    @SuppressLint("WrongConstant")
    public static String addDateMinut(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws Exception {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * 获取字符串最后一位字母
     */
    public static String getLastStr(String str) {
        String text = "";
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
            if (str.length() > 0) {
                text = str.substring(str.length() - 1);
            }
        }
        return text;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd hh:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd hh:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatType);
            date = formatter.parse(strTime);
        } catch (Exception e) {

        }
        return date;
    }

    @SuppressLint("WrongConstant")
    public static String addMouth(int num) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");//格式化为2017-10
        Calendar calendar = Calendar.getInstance();//得到Calendar实例
        calendar.add(Calendar.MONTH, num);//把月份减三个月
        Date starDate = calendar.getTime();//得到时间赋给Data
        String stardtr = formatter.format(starDate);//使用格式化Data
        return stardtr;
    }

    public static String getCurrentDateTime() {
        long now = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date d1 = new Date(now);
        return format.format(d1);
    }

    public static String getCurrentDay() {
        long now = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT_DAY);
        Date d1 = new Date(now);
        return format.format(d1);
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws Exception {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getSelectImagePathAfterKitkat(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat) {
            if (!DocumentsContract.isDocumentUri(context, uri)) {
                return getSelectImagePath(context, uri);
            }

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The mContext.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static String getSelectImagePath(Context context, Uri uri) {
        if (uri != null) {
            String uriStr = uri.toString();
            if (uriStr.startsWith("file://")) {
                String path = uriStr.substring(7);
                return path;
            }
        }
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static Bitmap compressBitmapFromFile(String photoPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 300f;//
        float ww = 200f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;// 设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 默认ARGB_8888 ,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(photoPath, newOpts);

        return bitmap;
    }

    public static int changeTextColorPosition(String str) {
        int lenth = str.length();
        return 3 + lenth;
    }

    /**
     * 禁用表情输入
     */

    public static InputFilter emojiFilter(final Context context) {
        InputFilter emojiFilter = new InputFilter() {
            @SuppressLint("WrongConstant")
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    showToast(context, "不支持输入表情");
                    return "";
                }
                return null;
            }
        };
        return emojiFilter;
    }


    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - lastClickTime;
        if (timeDifference < 500)          //500毫秒以内的点击，都认为是双击
        {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

    /**
     * 获取系统时间戳
     */
    public static String getTime() {
        String str = "";
        try {
            //long time = System.currentimeMillis() / 1000;//获取系统时间的10位的时间戳
            long time = System.currentTimeMillis();
            str = String.valueOf(time);
        } catch (Exception e) {

        }
        return str;

    }

//    public static String getTime(String time) {
//        time = com.cloudpolice.safetyschool.utils.CommonUtils.ifStringEmpty(time);
//        if (time.length() > 19) {
//            time = time.substring(0, 19);
//        }
//        return time;
//    }


    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * yyyy-MM-dd HH:mm  年月日
     *
     * @param time
     * @return
     */
    public static String formatDDateTime(String time) {
        long dateTaken = Long.parseLong(time);
        return android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", dateTaken).toString();
    }


    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        if (TextUtils.isEmpty(s)) return "";
        String res = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(Long.parseLong(s));
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return s;
        }
        return res;
    }


    /**
     * list转字符串
     */
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 判断是否开启定位
     */
    public static boolean isLocationEnabled(Activity activity) {
        int locationMode;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(activity.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    /**
     * 跳到位置服务设置
     */
    public static void jumpToPositionSetting(final Activity activity) {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 拨打电话跳转
     */
    public static void callPhone(Context activity, String mPhone) {
        if (!TextUtils.isEmpty(mPhone)) {
            String number = getPhoneNumber(mPhone);
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number.trim()));//跳转到拨号界面，同时传递电话号码
            activity.startActivity(dialIntent);
        } else {
            showToast(activity, "号码是空号");
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     * 注意这个是要权限的，只跳转是不需要权限的
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone1(Context activity, String phoneNum) {
        if (activity == null) return;
        try {
            if (!TextUtils.isEmpty(phoneNum)) {
                String number = getPhoneNumber(phoneNum);
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + number);
                intent.setData(data);
                activity.startActivity(intent);
            } else {
                showToast(activity, "号码是空号");
            }
        } catch (Exception e) {
            setLog("callPhone1", e.getMessage()); //直接拨打
            callPhone(activity, phoneNum);
        }
    }

    /**
     * 将字符串转为数字字符串
     */
    public static String getPhoneNumber(String str) {
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        return str2;
    }

    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth(Activity context) {
        WindowManager manager = context.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;  //以要素为单位
        // int height = metrics.heightPixels;
        return width;
    }

    /**
     * 获取屏幕的宽高
     */
    public static int getScreenHeight(Activity context) {
        WindowManager manager = context.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        //int width = metrics.widthPixels;  //以要素为单位
        int height = metrics.heightPixels;
        return height;
    }

    //当 View 有一点点不可见时立即返回false!
    public static boolean isVisibleLocal(View target) {
        Rect rect = new Rect();
        target.getLocalVisibleRect(rect);
        return rect.top == 0;
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * 关闭输入法键盘
     */
    public static void closeSoft(Activity context) {
        @SuppressLint("WrongConstant") InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken()
                , InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 获取系统版本号
     */

    public static String getAppVersion(Context context) {
        String currentVersion = "";
        try {
            currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentVersion;
    }


    /**
     * 过滤字符串为空的情况
     */
    public static String ifStringEmpty(String str) {
        if (TextUtils.isEmpty(str)) str = "";
        if (TextUtils.equals(str, "null")) str = "";
        return str;
    }

    public static void setText(TextView textView, String text) {
        if (textView == null) return;
        text = ifStringEmpty(text);
        textView.setText(text);
    }

    public static void setG(View view, boolean isV) {
        if (view == null) return;
        if (isV) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void setV(View view, boolean isV) {
        if (view == null) return;
        if (isV) {
            view.setVisibility(View.VISIBLE);
        } else {
                view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 替换字符串中指定字符，为某个字符
     *
     * @param needChangeText 需要替换的字符
     * @param changeToText   替换为的字符。
     */
    public static String changeTextOfAssign(String text, String needChangeText, String changeToText) {
        text = ifStringEmpty(text);
        int index = text.indexOf(needChangeText);
        text = text.substring(0, index) + changeToText + text.substring(index + needChangeText.length());
        return text;
    }


    /**
     * 向后延迟*天
     */
    public static Calendar addDateDay(int day, String format) {
        long now = System.currentTimeMillis();
        Date d1 = new Date(now);
        String dateString = dateToString(d1, format);
        String time = addDateHour(dateString, 24 * day);
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(time);
            calendar.setTime(date);
        } catch (Exception e) {
        }
        return calendar;
    }

    /**
     * 给时间加上几个小时
     *
     * @param day  当前时间 格式：yyyy-MM-dd hh:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    @SuppressLint("WrongConstant")
    public static String addDateHour(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        // System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);
    }

    /**
     * 给时间加上几分钟
     *
     * @param day 当前时间 格式：yyyy-MM-dd hh:mm:ss
     * @param min 需要加的时间
     * @return
     */
    @SuppressLint("WrongConstant")
    public static String addDateMin(String day, int min) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min);// 分钟60
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }

    /**
     * 密码必须为数字或者字母
     */
    public static boolean checkPassword(String str) {
        String strPattern = "^[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间的 yyyyMMddHHmmss 格式
     */
    public static String getStringCurrentDetailDate() {
        return getStringCurrentTime(new Date(), "yyyyMMddHHmmss");
    }


    /**
     * 将指定的时间转换成 指定的格式
     *
     * @return 时间的自定义字符串表示
     */
    public static String getStringCurrentTime(Date date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 判断手机号
     */
    public static boolean ifPhoneNum(Context mContext, String phone) {
        boolean include = true;
        if (TextUtils.isEmpty(phone)) {
            showToast(mContext, "手机号为空");
            include = false;
        } else {
            if (!isPhoneNO(phone)) {
                showToast(mContext, "请输入正确的手机号");
                include = false;
            }
        }
        return include;
    }

    private static TypedValue mTmpValue = new TypedValue();

    /**
     * 获取到的都是dp值，需要*3
     */
    public static int getXmlDef(Context context, int id) {
        synchronized (mTmpValue) {
            TypedValue value = mTmpValue;
            context.getResources().getValue(id, value, true);
            return (int) TypedValue.complexToFloat(value.data) * 3;
        }
    }

    /**
     * @param max 生成1到max的正整数
     */
    public static int getRandomInt(int max) {
        int ss = new Random().nextInt();
        if (ss == 0) ss += 1;
        if (max == 0) max = 1;
        return ss % max + 1;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param endTime   the first date
     * @param startTime the second date
     * @return true <br/>false
     */
    public static boolean isDateOneBigger(String endTime, String startTime) {
        boolean isBigger = false;
        //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null, dt2 = null;
        try {
            dt1 = sdf.parse(endTime);
            dt2 = sdf.parse(startTime);
            if (dt1.getTime() > dt2.getTime()) {
                isBigger = true;
            } else {
                isBigger = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return isBigger;
        }
    }

    /**
     * 比较两个时间的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isTimeOneBigger(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) return false;
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dt1 == null || dt2 == null) return false;
        if (dt1.getTime() >= dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 判断日期在某个范围
     */
    public static boolean isDateValid(String str, String str1, String str2) {
        boolean isValid = false;
        if (isDateOneBigger(str, str1) && isDateOneBigger(str2, str)) {
            isValid = true;
        }
        return isValid;
    }

    private static Bitmap bitmap;

    public static Bitmap stringToBitMap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return bitmap;
    }

    public static boolean ifListNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    public static int scrollY = 0;

    public static void clearScrollY() {
        scrollY = 0;
    }


    /**
     * 生成自然随机数
     *
     * @param maxValue 1到 count 之间的随机数
     */
    public static int getRandom(int maxValue) {
        if (maxValue <= 0) maxValue = 10;
        return Math.abs(new Random().nextInt()) % maxValue + 1;
    }

    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static void setLog(String key, String value) {
        Log.e("test_" + key, value + "");
    }


    /*
     *获取当前版本信息
     */
    public static PackageInfo getAppPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 返回byte的数据大小对应的文本  精确到0.0；
     *
     * @param size
     * @return
     */
    public static String getDataSizeM(long size) {
        DecimalFormat formater = new DecimalFormat("####.0");
        float mbsize = size / 1024f / 1024f;
        return formater.format(mbsize) + "M";
    }

    /**
     * （1）四舍五入把double转化int整型，0.5进一，小于0.5不进一
     *
     * @param number
     * @return
     */
    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }


    /**
     * （2）四舍五入把double转化为int类型整数,0.5也舍去,0.51进一
     *
     * @param dou
     * @return
     */
    public static int DoubleFormatInt(Double dou) {
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        return Integer.parseInt(df.format(dou));
    }


    /**
     * double类型转百分比
     *
     * @param number
     * @return
     */
    public static int ceilInt(double number) {
        return (int) Math.ceil(number);
    }

    public static String getPercentFormat(double d) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(3);//小数点前保留几位
        nf.setMinimumFractionDigits(2);// 小数点后保留几位
        String str = nf.format(d);
        return str;
    }

    public static String commonGetDayStart(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (text.length() > 10) {
                text = text.substring(0, 10) + "T00:00:00";
            }
        }
        return text;
    }

    public static String commonGetDayEnd(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (text.length() > 10) {
                text = text.substring(0, 10) + "T23:59:59";
            }
        }
        return text;
    }


    /**
     * 自增
     */
    public static String setAutoNumber(EditText et) {
        String frequencyStr = et.getText().toString();
        Number number = Double.parseDouble(frequencyStr);
        return String.valueOf(number.intValue() + 1);
    }

    /**
     * 自减
     */
    public static String setAutoreduction(EditText et) {
        String frequencyStr = et.getText().toString();
        Number number = Double.parseDouble(frequencyStr);
        int frequencyNum = number.intValue() - 1;
        if (frequencyNum < 0)
            frequencyNum = 0;

        return String.valueOf(frequencyNum);
    }

    public static String listToStr(List<String> list, String f) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else if (i < list.size() - 1) {
                    sb.append(list.get(i) + f);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }


    public static int getInt(String str) {
        if (TextUtils.isEmpty(str))
            return 0;

        try {
            Number number = Double.parseDouble(str);
            return number.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static String getStr(String str) {
        if (TextUtils.isEmpty(str))
            return "";
        return str;
    }


    /**
     * 精确到
     */
    public static String changeStrDouble(double count) {
        return new DecimalFormat("0.00").format(count);
    }

    /**
     * 精确到
     */
    public static String changeStrDouble2(double count) {
        return new DecimalFormat("0.0").format(count);
    }

    public static void setTime(TextView textView, String type, String time) {
//        time = com.cloudpolice.safetyschool.utils.CommonUtils.ifStringEmpty(time);
//        if (time.length() > 19) {
//            time = time.substring(0, 19);
//        }
//        if (time.contains("T")) {
//            time = time.replace("T", "  ");
//        }
//        setText(textView, type + time);
    }


    public static double getDouble(String dou) {
        double d = Double.parseDouble(dou);
//        BigDecimal bigDecimal = new BigDecimal(d).setScale(4, RoundingMode.HALF_UP);
        return d;
    }


//    public static String handlerGradeIntdex() {
//        String njStr = "";
//        try {
//            if (!TextUtils.isEmpty(Constant.gradeClsStr) && Constant.gradeClsStr.contains("年级")) {
//                String[] nianjiArr = Constant.gradeClsStr.split("年级");
//                if (nianjiArr != null && nianjiArr.length > 0) {
//                    String nianjiStr = nianjiArr[0];
//                    int index = com.cloudpolice.safetyschool.utils.CommonUtils.getInt(nianjiStr);
//                    if (index == 0) {
//                        njStr = nianjiStr;
//                    } else {
//                        njStr = index + "";
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return njStr;
//    }
}