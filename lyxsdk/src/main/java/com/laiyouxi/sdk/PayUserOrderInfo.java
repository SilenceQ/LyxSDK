package com.laiyouxi.sdk;

public class PayUserOrderInfo {
    private String payMethod = "";
    private String mchOrderId = "";
    private String goodsId = "";
    private String goodsNum = "";
    private String goodsTitle = "";
    private String totalPrice = "";
    private String unionId = "";
    private String openId = "";
    private String platId = "";
    private String idType = "";
    private String returnUrl = "";
    private String wxOpenId = "";
    private String orderTime = "";
    private String alipayPcLinkType = "";

    public PayUserOrderInfo() {}

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String platId) {
        this.payMethod = payMethod;
    }

    public String getMchOrderId() {
        return this.mchOrderId;
    }

    public void setMchOrderId(String mchOrderId) {
        this.mchOrderId = mchOrderId;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNum() {
        return this.goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsTitle() {
        return this.goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getPlatId() {
        return this.platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getWxOpenId() {
        return this.wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getAlipayPcLinkType() {
        return this.alipayPcLinkType;
    }

    public void setAlipayPcLinkType(String alipayPcLinkType) {
        this.alipayPcLinkType = alipayPcLinkType;
    }

    @Override
    public String toString() {
        return "PayUserOrder{" +
                "payMethod='" + payMethod + '\'' +
                ", mchOrderId='" + mchOrderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", unionId='" + unionId + '\'' +
                ", openId='" + openId + '\'' +
                ", platId='" + platId + '\'' +
                ", idType='" + idType + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", alipayPcLinkType='" + alipayPcLinkType + '\'' +
                '}';
    }
}
