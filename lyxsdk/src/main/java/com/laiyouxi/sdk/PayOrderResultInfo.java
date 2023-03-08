package com.laiyouxi.sdk;

public class PayOrderResultInfo {

    private String url = "";
    private String payMethod = "";
    private String prepayId = "";

    public PayOrderResultInfo() {}

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String platId) {
        this.payMethod = payMethod;
    }

    public String getPrepayId() {
        return this.prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PayOrderResult{" +
                "url='" + url + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", prepayId='" + prepayId + '\'' +
                '}';
    }
}
