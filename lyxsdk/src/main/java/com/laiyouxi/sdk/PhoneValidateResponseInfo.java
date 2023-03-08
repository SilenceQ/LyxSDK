package com.laiyouxi.sdk;

public class PhoneValidateResponseInfo {
    private String Phone;
    private String Code;
    private boolean isValid;

    public PhoneValidateResponseInfo() {}

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
