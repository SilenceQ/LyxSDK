package com.laiyouxi.sdk;
import android.app.Activity;

import com.antiaddiction.sdk.view.RealNameAndPhoneDialog;

public class LaiyouxiSdk {

    private static Activity activity;

    public static Activity getActivity(){
        return activity;
    }

    private static String appId = "";

    private static String mchId = "";

    //sdk初始化
    public static void init(Activity activity, String appId, String mchId) {
        LaiyouxiSdk.activity = activity;
        LaiyouxiSdk.appId = appId;
        LaiyouxiSdk.mchId = mchId;
    }

    //手机号登录
    public static void login(OnLoginResultListener onLoginResultListener) {
        LoginPhoneInitUI.loginPhoneShowDialog(activity, appId, mchId, onLoginResultListener);
    }

    public static void logout() {
//        AntiAddictionKit.logout();
        System.exit(0);
    }


    //实名认证
    public static void openRealNameAndPhoneDialog() {
        RealNameAndPhoneDialog.openRealNameAndPhoneDialog(1);
    }

    //获取用户实名状态
    public static void requireUserStatus(OnUserStatusListener onUserStatusListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.requireUserStatus(onUserStatusListener);
            }
        }).start();
    }

    //用户充值
    public static void payUserOrder(PayUserOrderInfo payUserOrder, OnPayOrderListener onPayOrderListener) {
        LoginPhoneInitUI.payOrderShowDialog(activity, mchId, appId, payUserOrder, onPayOrderListener);
    }

    //获取用户的微信openid
    public static void getWXOpenid(WechatOpenidInfo wxOpenidInfo, OnGetWXOpenidListener onGetWXOpenidListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.getWXOpenid(wxOpenidInfo, onGetWXOpenidListener);
            }
        }).start();
    }

    //设置密码
    public static void setPassword(SetPasswordInfo setPasswordInfo, OnSetPasswordListener onSetPasswordListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.setPassword(setPasswordInfo, onSetPasswordListener);
            }
        }).start();
    }

    //获取用户微信个人信息
    public static void getWXUserInfo(WechatUserInfo wechatUserInfo, OnGetWXUserInfoListener onGetWXUserInfoListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.getWXUserInfo(wechatUserInfo, onGetWXUserInfoListener);
            }
        }).start();
    }

    //绑定微信
    public static void bindWechat(BindWechatInfo bindWechatInfo, OnBindWechatListener onBindWechatListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.bindWechat(bindWechatInfo, onBindWechatListener);
            }
        }).start();
    }

    //绑定手机
    public static void bindPhone(BindPhoneInfo bindPhoneInfo, OnBindPhoneListener onBindPhoneListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.bindPhone(bindPhoneInfo, onBindPhoneListener);
            }
        }).start();
    }

    //获取用户信息
    public static void getUserInfo(String platId, OnUserInfoListener onUserInfoListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.getUserInfo(platId, onUserInfoListener);
            }
        }).start();
    }

    //修改昵称头像
    public static void modifyNicknameAvatar(ModifyNicknameAvatarInfo modifyNicknameAvatarInfo, OnModifyNicknameAvatarListener onModifyNicknameAvatarListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkLoadUtil.modifyNicknameAvatar(modifyNicknameAvatarInfo, onModifyNicknameAvatarListener);
            }
        }).start();
    }

}
