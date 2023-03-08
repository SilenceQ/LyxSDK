package com.laiyouxi.sdk;

public class ModifyNicknameAvatarInfo {
    private String platId = "";
    private String nick = "1";
    private String avatar = "";

    public ModifyNicknameAvatarInfo() {}

    public String getPlatId() {
        return this.platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

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

    @Override
    public String toString() {
        return "ModifyNicknameAvatarInfo{" +
                "platId='" + platId + '\'' +
                ", nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
