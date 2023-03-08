package com.laiyouxi.sdk;

public interface OnBindPhoneListener {
    void onSuccess(int code, String msg);

    void onFail(int code, String msg);
}
