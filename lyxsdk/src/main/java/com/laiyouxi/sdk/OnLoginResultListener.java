package com.laiyouxi.sdk;

public interface OnLoginResultListener {
    void onSuccess(int code, String msg, AccountInfo accountInfo);

    void onFail(int code, String msg);
}
