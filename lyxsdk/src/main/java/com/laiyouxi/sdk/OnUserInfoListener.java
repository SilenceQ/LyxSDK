package com.laiyouxi.sdk;

public interface OnUserInfoListener {
    void onSuccess(int code, String msg, GetUserInfo getUserInfo);

    void onFail(int code, String msg);
}
