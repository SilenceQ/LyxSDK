package com.laiyouxi.sdk;

public class WechatUserInfo {
    private String wxAuthCode = "";
    private String wxAppId = "";

    public WechatUserInfo() {}

    public String getWxAuthCode() {
        return this.wxAuthCode;
    }

    public void setWxAuthCode(String wxAuthCode) {
        this.wxAuthCode = wxAuthCode;
    }

    public String getWxAppId() {
        return this.wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    @Override
    public String toString() {
        return "WechatUserInfo{" +
                "wxAuthCode='" + wxAuthCode + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                '}';
    }
}
