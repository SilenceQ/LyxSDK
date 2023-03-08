package com.laiyouxi.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.antiaddiction.sdk.view.AntiDialog;
import com.antiaddiction.sdk.view.RealNameAndPhoneDialog;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LoginPhoneInitUI {

    private static boolean isSelect = false;

    private static boolean isWechat = false;

    private static Timer timer;

    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what>0){

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


    public static void loginPhoneShowDialog(Activity activity, String appId, String mchId, OnLoginResultListener onLoginResultListener) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = (RelativeLayout) inflater.inflate(R.layout.loginphone_dialog, null);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();

        Button backBtn = dialogView.findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();

            }
        });

        Button agreebtn = dialogView.findViewById(R.id.button_agree);
        if (isSelect) {
            agreebtn.setBackgroundResource(R.drawable.xuanzhong);
        } else {
            agreebtn.setBackgroundResource(R.drawable.weixuanzhong);
        }
        agreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isSelect = !isSelect;
                if (isSelect) {
                    agreebtn.setBackgroundResource(R.drawable.xuanzhong);
                } else {
                    agreebtn.setBackgroundResource(R.drawable.weixuanzhong);
                }
            }
        });

        EditText edittext = dialogView.findViewById(R.id.edittext_phone);
        Button nextBtn = dialogView.findViewById(R.id.button_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelect) {
                    PhoneValidateResponseInfo phoneValidateResponse = PhoneUtil.isPhoneNumberValidate(edittext.getText().toString(), "86");
                    if (phoneValidateResponse.isValid()) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //请求详情
                                NetworkLoadUtil.sendMessage(edittext.getText().toString(), new OnNetworkListener() {
                                    @Override
                                    public void onSuccess(int code,String message, Map<String, Object> dataList) {
                                        activity.runOnUiThread(new Runnable() {
                                            public void run() {
                                                dialog.cancel();
                                                loginSendMessageShowDialog(activity, appId, mchId, edittext.getText().toString(), onLoginResultListener);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFail(int code,String mesage) {
//                                        Toast toast = Toast.makeText(activity, mesage, (int) 2.0);
//                                        toast.setGravity(Gravity.CENTER, 0, 0);
//                                        toast.show();
                                    }
                                });


                            }

                        }).start();
                    } else {
                        Toast toast = Toast.makeText(activity, "手机号无效，请重新输入", (int) 2.0);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(activity, "请先阅读并同意\"来游戏用户协议\"和\"来游戏隐私政策\"", (int) 2.0);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        TextView textview = dialogView.findViewById(R.id.tv_agree);

        textview.setText("已阅读并同意");

        SpannableString clickString = new SpannableString("《来游戏用户协议》");
        clickString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(PublicParameters.userurl);
                intent.setData(content_url);
                activity.startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#1f8bff"));// 设置颜色
            }
        }, 0, clickString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.append(clickString);
        textview.append("及");
        SpannableString clickString2 = new SpannableString("《来游戏隐私政策》");
        clickString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(PublicParameters.privrurl);
                intent.setData(content_url);
                activity.startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#1f8bff"));// 设置颜色
            }
        }, 0, clickString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textview.append(clickString2);

        textview.setMovementMethod(LinkMovementMethod.getInstance());

        dialog.setView(dialogView, 0, 0, 0, 0);
        dialog.show();

        Window dialogWindow = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        if (d.getHeight() > d.getWidth()) {
            p.width = (int) (d.getWidth()*0.9);
            p.height = (int) ((d.getWidth()*0.9)*0.75);
        } else {
            p.height = (int) ((d.getHeight()*0.9)*0.75);
            p.width = (int) (d.getHeight()*0.9);
        }

        dialogWindow.setAttributes(p);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private static void loginSendMessageShowDialog(Activity activity, String appId, String mchId, String tel, OnLoginResultListener onLoginResultListener) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = (RelativeLayout) inflater.inflate(R.layout.loginmessage_dialog, null);

        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();

        TextView sendMessage_tv = dialogView.findViewById(R.id.sendmessage_tv);
        sendMessage_tv.setText("发送至"+tel);

        VerificationCode mVerificationCode = dialogView.findViewById(R.id.mVerificationCode);

        mVerificationCode.setOnInputListener(new VerificationCode.OnInputListener() {
            @Override
            public void onFinish(String code) {
//                Toast.makeText(activity,code,Toast.LENGTH_LONG).show();
                LoginTelCodeInfo loginTelCode = new LoginTelCodeInfo();
                loginTelCode.setTelCode(code);
                loginTelCode.setTel(tel);
                loginTelCode.setAppId(appId);
                loginTelCode.setMchId(mchId);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        NetworkLoadUtil.phoneLoginTelCode(loginTelCode, new OnNetworkListener() {
                            @Override
                            public void onSuccess(int code ,String message, Map<String, Object> dataList) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.cancel();
                                        AccountInfo model = new AccountInfo();
                                        model.setToken(String.valueOf(dataList.get("token")));
                                        model.setOpenId(String.valueOf(dataList.get("openId")));
                                        model.setPlatId(String.valueOf(dataList.get("platId")));
                                        model.setUnionId(String.valueOf(dataList.get("unionId")));
                                        model.setReg((Boolean) dataList.get("isReg"));
                                        onLoginResultListener.onSuccess(code, message, model);

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                NetworkLoadUtil.initSDKInfo(new OnInitSdkListener() {
                                                    @Override
                                                    public void onSuccess(int code, String msg, InitSdkInfo initSdkInfo) {
                                                        if (initSdkInfo.getOnline_limit() == 1) {
                                                            openTime();

                                                        }
                                                        if (initSdkInfo.getApp_verified() == 1) {
                                                            LaiyouxiSdk.requireUserStatus(new OnUserStatusListener() {
                                                                @Override
                                                                public void onSuccess(int code, String msg, UserStatusInfo userStatusInfo) {
                                                                    activity.runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (userStatusInfo.isStatus()) {
//                                                                                if (initSdkInfo.getOnline_limit() == 1) {
//                                                                                    openTime();
//                                                                                }
                                                                            } else {
                                                                                RealNameAndPhoneDialog.openRealNameAndPhoneDialog(1);
                                                                            }

                                                                        }
                                                                    });
                                                                }

                                                                @Override
                                                                public void onFail(int code, String msg) {
                                                                    onLoginResultListener.onFail(code, "未取到用户信息");
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onFail(int code, String msg) {
                                                        onLoginResultListener.onFail(code, "未取到初始化的值");
                                                    }
                                                });
                                            }
                                        }).start();


                                    }
                                });
                            }

                            @Override
                            public void onFail(int code, String mesage) {

                                onLoginResultListener.onFail(code, mesage);
//                                Toast toast = Toast.makeText(activity, mesage, (int) 2.0);
//                                toast.setGravity(Gravity.CENTER, 0, 0);
//                                toast.show();

                            }
                        });
                    }
                }).start();

            }
        });

        Countdown countdown_time = dialogView.findViewById(R.id.countdown_time);
        countdown_time.setOnFinshListener(new Countdown.OnFinshListener() {
            @Override
            public void onFinish() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求详情
                        NetworkLoadUtil.sendMessage(tel, new OnNetworkListener() {
                            @Override
                            public void onSuccess(int code,String message, Map<String, Object> dataList) {
                                activity.runOnUiThread(new Runnable() {
                                    public void run() {

                                    }
                                });
                            }

                            @Override
                            public void onFail(int code,String mesage) {

//                                Toast toast = Toast.makeText(activity, mesage, (int) 2.0);
//                                toast.setGravity(Gravity.CENTER, 0, 0);
//                                toast.show();

                            }
                        });


                    }

                }).start();
            }
        });

        Button backBtn = dialogView.findViewById(R.id.button_message_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();
                loginPhoneShowDialog(activity, appId, mchId, onLoginResultListener);
            }
        });

        Button button_close = dialogView.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });


        Button button_question = dialogView.findViewById(R.id.button_question);
        button_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(PublicParameters.questionurl);
                intent.setData(content_url);
                activity.startActivity(intent);
            }
        });

        dialog.setView(dialogView, 0, 0, 0, 0);
        dialog.show();

        Window dialogWindow = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        if (d.getHeight() > d.getWidth()) {
            p.width = (int) (d.getWidth()*0.9);
            p.height = (int) ((d.getWidth()*0.9)*0.75);
        } else {
            p.height = (int) ((d.getHeight()*0.9)*0.75);
            p.width = (int) (d.getHeight()*0.9);
        }
        dialogWindow.setAttributes(p);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public static void payOrderShowDialog(Activity activity, String mchid, String appId, PayUserOrderInfo payUserOrder, OnPayOrderListener payOrderListener) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = (RelativeLayout) inflater.inflate(R.layout.payorder_dialog, null);

        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();

        payUserOrder.setPayMethod("wechat_h5");

        RelativeLayout rl_alpay = dialogView.findViewById(R.id.rl_alpay);
        LinearLayout ll_wechat = dialogView.findViewById(R.id.ll_wechat);
        TextView tv_alpay = dialogView.findViewById(R.id.tv_alpay);
        TextView tv_wechat = dialogView.findViewById(R.id.tv_wechat);
        Button btn_sure = dialogView.findViewById(R.id.btn_sure);
        TextView pay_title = dialogView.findViewById(R.id.pay_title);
        TextView pay_amount = dialogView.findViewById(R.id.pay_amount);
        pay_title.setText(payUserOrder.getGoodsTitle());
        pay_amount.setText("￥"+Double.parseDouble(payUserOrder.getTotalPrice())/100);
        rl_alpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_alpay.setBackgroundResource(R.drawable.input_blue_bg);
                ll_wechat.setBackgroundResource(R.drawable.input_gray_bg);
                tv_alpay.setTextColor(Color.parseColor("#333333"));
                tv_wechat.setTextColor(Color.parseColor("#999999"));
                isWechat = false;
            }
        });

        ll_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_alpay.setBackgroundResource(R.drawable.input_gray_bg);
                ll_wechat.setBackgroundResource(R.drawable.input_blue_bg);
                tv_alpay.setTextColor(Color.parseColor("#999999"));
                tv_wechat.setTextColor(Color.parseColor("#333333"));
                isWechat = true;
            }
        });

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWechat) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            payUserOrder.setPayMethod("wechat_h5");

                            NetworkLoadUtil.payUserOrder(mchid, appId, payUserOrder, new OnPayOrderListener() {
                                @Override
                                public void onSuccess(int code, String msg, PayOrderResultInfo payOrderResult) {
                                    payOrderListener.onSuccess(code, msg, payOrderResult);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            Uri content_url = Uri.parse(payOrderResult.getUrl());
                                            intent.setData(content_url);
                                            activity.startActivity(intent);
                                        }
                                    });

                                }

                                @Override
                                public void onFail(int code, String msg) {
                                    payOrderListener.onFail(code, msg);
                                }
                            });
                        }


                    }).start();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            payUserOrder.setPayMethod("alipay_h5");
                            NetworkLoadUtil.payUserOrder(mchid, appId, payUserOrder, new OnPayOrderListener() {
                                @Override
                                public void onSuccess(int code, String msg, PayOrderResultInfo payOrderResult) {
                                    payOrderListener.onSuccess(code, msg, payOrderResult);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            Uri content_url = Uri.parse(payOrderResult.getUrl());
                                            intent.setData(content_url);
                                            activity.startActivity(intent);
                                        }
                                    });

                                }

                                @Override
                                public void onFail(int code, String msg) {
                                    payOrderListener.onFail(code, msg);
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        Button backBtn = dialogView.findViewById(R.id.btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.cancel();

            }
        });

        dialog.setView(dialogView, 0, 0, 0, 0);
        dialog.show();

        Window dialogWindow = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        if (d.getHeight() > d.getWidth()) {
            p.width = (int) (d.getWidth()*0.9);
            p.height = (int) ((d.getWidth()*0.9)*0.8);
        } else {
            p.height = (int) ((d.getHeight()*0.9)*0.8);
            p.width = (int) (d.getHeight()*0.9);
        }
        dialogWindow.setAttributes(p);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

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
}
