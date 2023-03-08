package com.laiyouxi.sdk;


public interface OnSetPasswordListener {

    void onSuccess(int code, String message);

    void onFail(int code, String mesage);
}
