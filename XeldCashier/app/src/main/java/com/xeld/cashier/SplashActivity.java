package com.xeld.cashier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.elvishew.xlog.XLog;
import com.king.zxing.util.CodeUtils;
import com.xeld.cashier.ui.login.LoginActivity;
import com.xeld.cashier.utils.actionbar.StatusBarUtil;

import androidx.appcompat.app.ActionBar;

import butterknife.BindView;

public class SplashActivity extends BaseAct {

    @BindView(R.id.iv_splash_bg)
    ImageView splashImageView;
    ImageView iv_code_test;

    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 横屏在androidMainfest文件配置了
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


        setContentView(R.layout.activity_splash);



//        splashImageView = findViewById(R.id.iv_splash_bg);
        iv_code_test = findViewById(R.id.iv_code_test);
        System.out.println("日志打印结束");
//        splashImageView.setBackground(R.id.a);
        String imageUrl = "https://images.51xeld.com/2020/09/2fb3bad11ae14d3790087f690c1c2c1f.jpg";
//        Glide.with(SplashActivity.this).load(splashImageView).into(splashImageView);

//        Glide
//                .with(this)
//                .load(imageUrl)
//                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(splashImageView);

//        splashImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        Thread loginThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);//使程序休眠五秒
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();//关闭当前活动
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        loginThread.start();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        XLog.e("screenWidth =  " + displayMetrics.widthPixels);
        XLog.e("screenHeight =  " + displayMetrics.heightPixels);
        XLog.e("density =  " + displayMetrics.density);
        XLog.e("densityDpi =  " + displayMetrics.densityDpi);

        //生成二维码
        String data = "18611128418";
        Bitmap qrCode = CodeUtils.createBarCode(data, 290, 50);
        iv_code_test.setImageBitmap(qrCode);
        System.out.println("日志打印结束");
    }

    /**
     * 设置状态栏透明
     *
     * @param context 上下文，尽量使用Activity
     */
    public static void setTransparent(Context context) {
        if (context instanceof Activity) {
            setTransparent(((Activity) context).getWindow());
        }
    }

    /**
     * 设置状态栏透明
     *
     * @param window 窗口，可用于Activity和Dialog等
     */
    public static void setTransparent(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(R.color.white);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //setColor(window, 0x80000000, true);

        }
    }

    private void initBar1() {

        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }

//        getSupportActionBar().hide();

    }


    private void initBar(){

// 全屏展示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 全屏显示，隐藏状态栏和导航栏，拉出状态栏和导航栏显示一会儿后消失。
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                // 全屏显示，隐藏状态栏
               getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        }

// 非全屏显示，显示状态栏和导航栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }


}
