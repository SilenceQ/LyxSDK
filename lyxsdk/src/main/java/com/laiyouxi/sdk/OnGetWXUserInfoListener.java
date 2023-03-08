package com.laiyouxi.sdk;

public interface OnGetWXUserInfoListener {
    void onSuccess(int code, String msg, String openid, String unionid);

    void onFail(int code, String msg);
}
