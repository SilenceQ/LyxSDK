package com.antiaddiction.sdk.view;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;

import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.antiaddiction.sdk.OnResultListener;
import com.antiaddiction.sdk.utils.PxUtil;
import com.antiaddiction.sdk.utils.Res;
import com.antiaddiction.sdk.utils.RexCheckUtil;
import com.antiaddiction.sdk.utils.UnitUtils;
import com.laiyouxi.sdk.LaiyouxiSdk;
import com.laiyouxi.sdk.NetworkLoadUtil;
import com.laiyouxi.sdk.OnRealNameListener;
import com.laiyouxi.sdk.OnUserAntiListener;
import com.laiyouxi.sdk.PublicParameters;
import com.laiyouxi.sdk.R;
import com.laiyouxi.sdk.RealNameCertificationInfo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class RealNameAndPhoneDialog extends BaseDialog {
    private PopupWindow popupWindow;
    private LinearLayout ll_container;
    private TextView tv_title;
    private TextView tv_question;
    private ImageView bt_close;
    private Button bt_back;
    private EditText et_name;
    private EditText et_identify;

    private Button bt_sumbit;

    //点击返回键返回上一个页面
    private BackPressListener backPressListener;
    private int strict = 2; //1 强制实名 2非强制实名 3强制实名无关闭 4通过openRealName接口调用
    public static boolean Real_Showing = false;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    private static Timer timer;

    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what>0){
//                tv.setText("倒计时"+msg.what);
            }else {
                timer.cancel(); //关闭定时器
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NetworkLoadUtil.getAntiInfo(new OnUserAntiListener() {
                            @Override
                            public void onSuccess(int code, String msg, int todayPlayTime) {
                                if (code == 0) {
                                    openTime();
                                } else if (code == -10) {
                                    AntiDialog.showAntiTip(msg);
                                } else if (code == -11) {
                                    AntiDialog.showAntiTip(msg);
                                } else if (code == -12) {
                                    AntiDialog.showAntiTip(msg);
                                }
                            }
                        });
                    }
                }).start();

            }
        }
    };

    private static void openTime() {
        //定义定时器
        timer=new Timer();
        timer.schedule(new TimerTask() {
            int i=300;
            @Override
            public void run() {
                //定义一个消息传过去
                Message msg=new Message();
                msg.what=i--;
                handler.sendMessage(msg);
            }
        },0,1000);
    }

    public RealNameAndPhoneDialog(Context context,OnResultListener onResultListener) {
        super(context);

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Real_Showing = true;

                LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver,new IntentFilter("real_name.close_unable"));
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Real_Showing = false;
                LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
            }
        });
        setCancelable(false);
    }

    public RealNameAndPhoneDialog(Context context,int strict, OnResultListener onResultListener) {
        this(context,onResultListener);
        this.strict = strict;
    }

    public RealNameAndPhoneDialog(Context context,int strict, OnResultListener onResultListener, BackPressListener backPressListener) {
        this(context,onResultListener);
        this.strict = strict;
        this.backPressListener = backPressListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setWindowAnimations(Res.style(getContext(),"dialogWindowAnim"));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(Res.layout(getContext(), "dialog_real_name"));

        ll_container = (LinearLayout) findViewById("ll_real_container");
        tv_title = (TextView) findViewById("tv_real_title");
        bt_back = (Button) findViewById("iv_auth_back");
        bt_close = (ImageView) findViewById("iv_auth_close");
        tv_question = (TextView) findViewById("tv_question");

        tv_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(PublicParameters.questionurl);
                intent.setData(content_url);
                getContext().startActivity(intent);
            }
        });

            bt_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    backPressListener.onBack();
//                    dismiss();
                }
            });



        bt_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(strict == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("确定退出？");
                        builder.setPositiveButton("确定", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dismiss();
                                System.exit(0);
                            }
                        });
                        builder.setNegativeButton("取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.create().show();
                    }else{
//                        AntiDialog.showAntiTip("");
                        dismiss();
                        System.exit(0);
                    }

                }
            });
//        }


        et_name = (EditText) findViewById("et_name");
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = et_name.getText().toString();
                String reg = "[^\u4E00-\u9FA5]";
                String valid = Pattern.compile(reg).matcher(name).replaceAll("").trim();
                if(!TextUtils.equals(name,valid)){
                    et_name.setText(valid);
                    et_name.setSelection(valid.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_identify = (EditText) findViewById("et_identify");

        bt_sumbit = (Button) findViewById("bt_submit");
        bt_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String identify = et_identify.getText().toString().trim();

                if(name.length() < 2){
                    Toast.makeText(getContext(),"请输入有效姓名信息！",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!RexCheckUtil.checkIdentify(identify)){
                    Toast.makeText(getContext(),"请输入有效身份证！",Toast.LENGTH_SHORT).show();
                    return;
                }
                onSubmit(name,identify);
            }
        });

        resetDialogStyle();

    }

    private void resetDialogStyle(){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(UnitUtils.dpToPx(getContext(),12));
        gradientDrawable.setColor(Color.parseColor("#ffffff"));
        ll_container.setBackground(gradientDrawable);

        tv_title.setTextColor(Color.parseColor("#2b2b2b"));
        et_name.setTextColor(Color.parseColor("#000000"));
        et_identify.setTextColor(Color.parseColor("#000000"));


        GradientDrawable buttonDrawable = new GradientDrawable();
        buttonDrawable.setCornerRadius(UnitUtils.dpToPx(getContext(),17));
        buttonDrawable.setColor(Color.parseColor("#000000"));

    }

    public static void openRealNameAndPhoneDialog(int strict){
        new RealNameAndPhoneDialog(LaiyouxiSdk.getActivity(), strict, new OnResultListener() {
            @Override
            public void onResult(int type, String msg) {

            }
        }).show();
    }

    public static void openRealNameAndPhoneDialog(final int strict, final OnResultListener onResultListener){
        LaiyouxiSdk.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new RealNameAndPhoneDialog(LaiyouxiSdk.getActivity(),strict,onResultListener).show();
            }
        });
    }

    public static void openRealNameAndPhoneDialog(OnResultListener onResultListener, int strict, BackPressListener backPressListener){
        RealNameAndPhoneDialog realNameAndPhoneDialog = new RealNameAndPhoneDialog(LaiyouxiSdk.getActivity(),strict,onResultListener,backPressListener);
        realNameAndPhoneDialog.show();
    }

    private void onSubmit(String name, String identify){

        RealNameCertificationInfo realNameCertification = new RealNameCertificationInfo();
        realNameCertification.setName(name);
        realNameCertification.setCard(identify);
        new Thread(new Runnable() {
            @Override
            public void run() {

                NetworkLoadUtil.realNameCertification(realNameCertification, new OnRealNameListener() {
                    @Override
                    public void onSuccess(int code, String message) {
                        LaiyouxiSdk.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(getContext(),"信息提交成功！",Toast.LENGTH_SHORT).show();
                               LaiyouxiSdk.getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        View toastView = LayoutInflater.from(getContext()).inflate(R.layout.toast_sure, null);
                                        LinearLayout relativeLayout = (LinearLayout)toastView.findViewById(R.id.toast_linear);
                                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) PxUtil.dpToPx(getContext(), 120), (int)PxUtil.dpToPx(getContext(), 120));
                                        relativeLayout.setLayoutParams(layoutParams);

                                        Toast toast = new Toast(getContext());
                                        toast.setDuration((int) 3.0);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.setView(toastView);
                                        toast.show();

                                        dismiss();

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                openTime();
                                            }
                                        });
                                    }
                                });

                            }
                        });

                    }

                    @Override
                    public void onFail(int code, String mesage) {
                        LaiyouxiSdk.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RealNameFailDialog.showRealNameFailTip(mesage);
                            }
                        });
                    }
                });

            }
        }).start();


    }


    public interface BackPressListener{
        void onBack();
    }


}

