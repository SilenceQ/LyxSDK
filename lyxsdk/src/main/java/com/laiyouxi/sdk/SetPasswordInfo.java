package com.laiyouxi.sdk;

public class SetPasswordInfo {

    private String telCode = "";
    private String password = "";

    public SetPasswordInfo() {}

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
        return "SetPasswordInfo{" +
                "telCode='" + telCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
