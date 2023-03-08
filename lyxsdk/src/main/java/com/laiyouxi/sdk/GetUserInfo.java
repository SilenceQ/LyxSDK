package com.laiyouxi.sdk;

public class GetUserInfo {
    private String nick = "";
    private String avatar = "";
    private String tel = "";
    private int regTime;
    private int loginTime;
    private String loginIp = "";
    private int lastLoginTime;
    private int age;
    private String card = "";
    private String name = "";

    public GetUserInfo() {}

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getRegTime() {
        return this.regTime;
    }

    public void setRegTime(int regTime) {
        this.regTime = regTime;
    }

    public int getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(int loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public int getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(int lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetUserInfo{" +
                "nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tel='" + tel + '\'' +
                ", regTime=" + regTime +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", age=" + age +
                ", card='" + card + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
