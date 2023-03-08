package com.laiyouxi.sdk;

import java.util.Map;

public interface OnNetworkListener {
    void onSuccess(int code, String message, Map<String, Object> dataList);

    void onFail(int code, String mesage);
}
