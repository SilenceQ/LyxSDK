package com.laiyouxi.sdk;

public class AccountInfo {
    private String platId = "";
    private String unionId = "";
    private String openId = "";
    private String token = "";
    private boolean reg;

    public AccountInfo() {}

    public String getPlatId() {
        return this.platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isReg() {
        return this.reg;
    }

    public void setReg(boolean reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "platId='" + this.platId + '\'' +
                ", unionId='" + this.unionId + '\'' +
                ", openId='" + this.openId + '\'' +
                ", token='" + this.token + '\'' +
                ", reg=" + this.reg +
                '}';
    }

}
