package com.laiyouxi.sdk;

public interface OnUserStatusListener {
    void onSuccess(int code, String msg, UserStatusInfo userStatusInfo);

    void onFail(int code, String msg);
}
