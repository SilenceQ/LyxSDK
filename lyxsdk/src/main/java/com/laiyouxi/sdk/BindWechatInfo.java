package com.laiyouxi.sdk;

public class BindWechatInfo {
    private String wxAuthCode = "";
    private String changeBind = "1";
    private String telCode = "";

    public BindWechatInfo() {}

    public String getWxAuthCode() {
        return this.wxAuthCode;
    }

    public void setWxAuthCode(String wxAuthCode) {
        this.wxAuthCode = wxAuthCode;
    }

    public String getChangeBind() {
        return this.changeBind;
    }

    public void setChangeBind(String changeBind) {
        this.changeBind = changeBind;
    }

    public String getTelCode() {
        return this.telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }
}
