package com.laiyouxi.sdk;


public interface OnRealNameListener {

    void onSuccess(int code, String message);

    void onFail(int code, String mesage);
}
