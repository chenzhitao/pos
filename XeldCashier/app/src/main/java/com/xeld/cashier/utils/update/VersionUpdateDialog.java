package com.xeld.cashier.utils.update;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xeld.cashier.R;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.base.BaseDialog;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caorongguan on 2019/1/15.
 */

public class VersionUpdateDialog extends BaseDialog {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_tv)
    TextView progressTv;
    @BindView(R.id.sure_tv)
    TextView sureTv;
    @BindView(R.id.cancel_tv)
    TextView cancelTv;
    OnRequestSuccess listener;
    private String mUrl;
    private boolean isFinish = false, isLoading = false;
    private Activity mAct;
    private int count;
    OnRequestSuccess<String> listener1;

    public VersionUpdateDialog(Activity context, final String url, OnRequestSuccess<String> listener) {
        super(context);
        showDialog();
        mAct = context;
        mUrl = url;
        this.listener1 = listener;
        initView();
        setOutSideTouch(true);//拦截外部点击
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_version_update;
    }

    protected void initView() {
        isFinish = false;
        isLoading = false;
        CommonViewUtils.setText(sureTv, "下载");
        String titleStr = "检测到新版本";
        String left = "立即更新";
        String right = "以后再说";
        CommonViewUtils.setText(title, titleStr);
        CommonViewUtils.setText(sureTv, left);
        CommonViewUtils.setText(cancelTv, right);
        count = 0;
        setProgress(0);
    }

    public void setType(int needUpdate) {
        switch (needUpdate) {
            case 1://可选
                break;
            case 2://强制
                CommonViewUtils.setGone(cancelTv, false);
                break;
            default:
             //   dismiss();
                break;
        }
    }

    public void setListener(OnRequestSuccess<Integer> mListener) {
        listener = mListener;
    }

    @OnClick({R.id.sure_tv, R.id.cancel_tv})
    public void onClick(View v) {
        if (CommonUtils.isFastDoubleClick()) return;
        switch (v.getId()) {
            case R.id.sure_tv:
                if (isLoading) return;
                isLoading = true;
                sureTv.setText("正在下载...");
                UploadAppProxy.downloadApp(mAct, mUrl, result -> {
                    setProgress(result);
                });
                break;
            case R.id.cancel_tv:
                if (listener1 != null)
                    listener1.onResult("");
                CommonViewUtils.showToast("当前不可取消更新。");
                break;
            default:
                break;
        }
    }


    /**
     * 设置进度
     */
    public void setProgress(int progress) {
        if (isFinish) {
            progressBar.setProgress(100);
            CommonViewUtils.setText(progressTv, "下载完成");
            return;
        }
        if (progress < 0) {
            count++;
            if (count > 3) {
                progress = 100;
            } else {
                return;
            }
        }
        String strProgress = progress + "%";
        progressBar.setProgress(progress);
        CommonViewUtils.setText(progressTv, strProgress);
        if (progress == 100) {
            isFinish = true;
            CommonViewUtils.setGone(sureTv, isFinish);
            CommonViewUtils.setText(progressTv, "下载完成");
            sureTv.postDelayed(() -> {
              //  dismiss();
            }, 1000);
        }
    }

}
