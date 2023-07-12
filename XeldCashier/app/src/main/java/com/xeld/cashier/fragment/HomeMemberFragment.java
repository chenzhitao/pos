package com.xeld.cashier.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.adapter.MemberListAdapter;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.base.fragment.HomeBaseFragment;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.ui.login.TestActivity;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.bean.ListMemberInfoBean;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import butterknife.BindView;

public class HomeMemberFragment extends HomeBaseFragment {
    private static final String EIGHT = "8";
    private static final String ZERO = "0";
    private TextView txOne;
    private TextView txTwo;
    private TextView txThree;
    private TextView txFour;
    private TextView txFive;
    private TextView txSix;
    private TextView txServen;
    private TextView txEight;
    private TextView txNine;
    private TextView txZero;

    private TextView txCancel;
    private TextView txOk;


    private XEditText etSelectMemberPhone;

    private TextView tvCloseFragment;
    private TextView tx_delete_one_chart;

    private LinearLayout layout_member_null;

    @BindView(R.id.lv_member_list)
    ListView lv_member_list;


    private BaseAct mBact;
    private String memberPhone = "";
    //记录自定义键盘的输入数字
    private StringBuffer phoneBuffer;

    private TestActivity testActivity;

    private List<HomeMemberInfoBean.DataBean> list;
    private MemberListAdapter memberListAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_main_select_member_layout;
    }

    @Override
    public void initView() {
        mBact = getAct();
        testActivity = (TestActivity) getAct();
        tvCloseFragment = getViewById(R.id.tv_close_member_fragment);
        tx_delete_one_chart = getViewById(R.id.tx_delete_one_chart);

        layout_member_null = getViewById(R.id.layout_member_null);
        layout_member_null.setVisibility(View.GONE);

        list = new ArrayList<>();
//        memberListAdapter = new MemberListAdapter(mBact, list);
//        lv_member_list.setAdapter(memberListAdapter);
        lv_member_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_MEMBER_FRAGMENT);
                eventBean.setValue(memberListAdapter.getList().get(position));
                EventBus.getDefault().post(eventBean);

                //重置View
                testActivity.showProductFragment();
                etSelectMemberPhone.setText("");
                memberListAdapter.getList().removeAll(memberListAdapter.getList());
                memberListAdapter.notifyDataSetChanged();
            }
        });


        CommonViewUtils.setOnClick(tvCloseFragment, view -> {
            testActivity.showProductFragment();
//            BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_HOME_FRAGMENT);
//            EventBus.getDefault().post(eventBean);
        });

        CommonViewUtils.setOnClick(tx_delete_one_chart, view -> {
//            BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_HOME_FRAGMENT);
//            EventBus.getDefault().post(eventBean);
            deleteOneChart();
        });


        CommonViewUtils.setOnClick(layout_member_null, view -> {
            testActivity.showAddMemberFragment();

            etSelectMemberPhone.setText("");
            layout_member_null.setVisibility(View.GONE);
        });

        txOne = getViewById(R.id.tv_select_member_one);
        CommonViewUtils.setOnClick(txOne, view -> {
            setEtPhone("1");
        });
        txTwo = getViewById(R.id.tv_select_member_two);
        CommonViewUtils.setOnClick(txTwo, view -> {
            setEtPhone("2");
        });
        txThree = getViewById(R.id.tv_select_member_three);
        CommonViewUtils.setOnClick(txThree, view -> {
            setEtPhone("3");
        });

        txFour = getViewById(R.id.tv_select_member_four);
        CommonViewUtils.setOnClick(txFour, view -> {
            setEtPhone("4");
        });
        txFive = getViewById(R.id.tv_select_member_five);
        CommonViewUtils.setOnClick(txFive, view -> {
            setEtPhone("5");
        });
        txSix = getViewById(R.id.tv_select_member_six);
        CommonViewUtils.setOnClick(txSix, view -> {
            setEtPhone("6");
        });
        txServen = getViewById(R.id.tv_select_member_seven);
        CommonViewUtils.setOnClick(txServen, view -> {
            setEtPhone("7");
        });
        txEight = getViewById(R.id.tv_select_member_eight);
        CommonViewUtils.setOnClick(txEight, view -> {
            setEtPhone("8");
        });
        txNine = getViewById(R.id.tv_select_member_nine);
        CommonViewUtils.setOnClick(txNine, view -> {
            setEtPhone("9");
        });
        txZero = getViewById(R.id.tv_select_member_zero);
        CommonViewUtils.setOnClick(txZero, view -> {
            setEtPhone(ZERO);
        });

        txCancel = getViewById(R.id.tv_select_member_cancel);
        CommonViewUtils.setOnClick(txCancel, view -> {
            etSelectMemberPhone.setText("请输入会员手机号码");
            phoneBuffer = null;
        });

        txOk = getViewById(R.id.tv_select_member_ok);
        CommonViewUtils.setOnClick(txOk, view -> {
            getMemberData();
        });


        etSelectMemberPhone = getViewById(R.id.phone_et_member);

        CommonViewUtils.getInputEnterResult(mBact, etSelectMemberPhone);

        etSelectMemberPhone.setOnXTextChangeListener(new XEditText.OnXTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setPhoneBuffer(s.toString());

                if (phoneBuffer.length() != 0){

                    Thread loginThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(100);//使程序休眠五秒
                                getMemberData();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    loginThread.start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getMemberData() {
        String userMobile = etSelectMemberPhone.getText().toString().trim();
        if (TextUtils.isEmpty(userMobile)) {
            showToast("请输入手机号码");
            return;
        }
        try {
            JSONObject json = new JSONObject();
//            json.put("tel", userMobile);
//            mBact.showLoading();
            String url = URL.DEBUG_URL + URL.GETMEMBER;
            StringBuffer buffer = new StringBuffer(url);
            buffer.append("?tel=" + userMobile);

            String stringUrl = buffer.toString();
            XLog.d(stringUrl);

            easyPost(json, stringUrl, HomeMemberInfoBean.class, result -> {
                onMemberResult(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }

    }

    private void onMemberResult(HomeMemberInfoBean relust) {
//        if (relust.getData() == null) {
//            showToast("会员信息为空");
//            layout_member_null.setVisibility(View.VISIBLE);
//            return;
//        } else {
//            layout_member_null.setVisibility(View.GONE);
//        }

        if (relust == null) {
            return;
        }

        if (relust.getData() != null) {
            layout_member_null.setVisibility(View.GONE);
        } else {
            showToast("会员信息为空");
            layout_member_null.setVisibility(View.VISIBLE);
            return;
        }
//        BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_MEMBER_FRAGMENT);
//        eventBean.setValue(relust);
//        EventBus.getDefault().post(eventBean);
//
//        testActivity.showProductFragment();
//        etSelectMemberPhone.setText("");
//        layout_member_null.setVisibility(View.INVISIBLE);

        memberListAdapter = new MemberListAdapter(mBact, relust.getData());
        lv_member_list.setAdapter(memberListAdapter);
//        memberListAdapter.setDataList(relust.getData());

    }

    private void setEtPhone(String chart) {
        XLog.d(chart);
        if (phoneBuffer == null)
            phoneBuffer = new StringBuffer();

        if (phoneBuffer.length() > 11) {
            showToast("手机号码只有11位");
            return;
        }
        phoneBuffer.append(chart);
        String numberStr = phoneBuffer.toString();
        etSelectMemberPhone.setText(numberStr);
        if (phoneBuffer.length() == 11)
            etSelectMemberPhone.setSelection(numberStr.length() - 1);
        else
            etSelectMemberPhone.setSelection(numberStr.length());

        XLog.d(numberStr);
    }

    private void setPhoneBuffer(String chart) {
        phoneBuffer = new StringBuffer(chart);
    }

    private void deleteOneChart() {
        if (phoneBuffer == null) {
            showToast("用户输入为空");
            return;
        } else {

            if (phoneBuffer.length() == 0) {
                showToast("用户输入为空");
                return;
            }
            phoneBuffer = phoneBuffer.deleteCharAt(phoneBuffer.length() - 1);
            etSelectMemberPhone.setText(phoneBuffer.toString());

            if (phoneBuffer.length() == 11)
                etSelectMemberPhone.setSelection(phoneBuffer.length() - 1);
            else
                etSelectMemberPhone.setSelection(phoneBuffer.length());

        }

    }

}
