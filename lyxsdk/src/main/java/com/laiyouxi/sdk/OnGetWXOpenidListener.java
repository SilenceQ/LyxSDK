package com.laiyouxi.sdk;

public interface OnGetWXOpenidListener {
    void onSuccess(int code, String msg, String wxOpenId);

    void onFail(int code, String msg);
}
