package com.laiyouxi.sdk;

public class WechatOpenidInfo {
    private String wxCode = "";
    private String wxAppId = "";

    public WechatOpenidInfo() {}

    public String getWxCode() {
        return this.wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getWxAppId() {
        return this.wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    @Override
    public String toString() {
        return "WechatOpenid{" +
                "wxCode='" + wxCode + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                '}';
    }
}
