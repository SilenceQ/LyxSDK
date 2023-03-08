package com.laiyouxi.sdk;

public class InitSdkInfo {
    private int online_limit;
    private int app_verified;

    public InitSdkInfo() {}

    public int getOnline_limit() {
        return this.online_limit;
    }

    public void setOnline_limit(int online_limit) {
        this.online_limit = online_limit;
    }

    public int getApp_verified() {
        return this.app_verified;
    }

    public void setApp_verified(int app_verified) {
        this.app_verified = app_verified;
    }

    @Override
    public String toString() {
        return "InitSdkInfo{" +
                "online_limit=" + online_limit +
                ", app_verified=" + app_verified +
                '}';
    }
}
