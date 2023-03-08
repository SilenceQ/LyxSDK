package com.laiyouxi.sdk;

public interface OnInitSdkListener {
    void onSuccess(int code, String msg, InitSdkInfo initSdkInfo);

    void onFail(int code, String msg);
}
