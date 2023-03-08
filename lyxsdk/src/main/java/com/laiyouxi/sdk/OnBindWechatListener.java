package com.laiyouxi.sdk;

public interface OnBindWechatListener {
    void onSuccess(int code, String msg);

    void onFail(int code, String msg);
}
