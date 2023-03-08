package com.laiyouxi.sdk;

public class LoginTelCodeInfo {
    private String mchId = "";
    private String appId = "";
    private String tel = "";
    private String telCode = "";
    private String password = "";

    public LoginTelCodeInfo() {}

    public String getMchId() {
        return this.mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelCode() {
        return this.telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginTelCode{" +
                "mchId='" + mchId + '\'' +
                ", appId='" + appId + '\'' +
                ", tel='" + tel + '\'' +
                ", telCode='" + telCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
