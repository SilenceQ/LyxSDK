package com.laiyouxi.sdk;

public interface OnPayOrderListener {
    void onSuccess(int code, String msg, PayOrderResultInfo payOrderResult);

    void onFail(int code, String msg);
}
