package com.laiyouxi.sdk;

public class BindPhoneInfo {
    private String tel = "";
    private String changeBind = "1";
    private String telCode = "";
    private String oldTel = "";

    public BindPhoneInfo() {}

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getOldTel() {
        return this.oldTel;
    }

    public void setOldTel(String oldTel) {
        this.oldTel = oldTel;
    }

    @Override
    public String toString() {
        return "BindPhoneInfo{" +
                "tel='" + tel + '\'' +
                ", changeBind='" + changeBind + '\'' +
                ", telCode='" + telCode + '\'' +
                ", oldTel='" + oldTel + '\'' +
                '}';
    }
}
