package com.laiyouxi.sdk;

public class PublicParameters {
    public static String userurl = "https://g2021-cdn.laiyouxi.com/online/h5/zh-CN/settings/user-agent-zoxun.html";

    public static String privrurl = "https://g2021-cdn.laiyouxi.com/online/h5/zh-CN/settings/private-usage-zoxun.html";

    public static String questionurl = "https://g2021-cdn.laiyouxi.com/online/h5/zh-CN/settings/private-usage-zoxun.html";

    public static String urlPath = "http://apisix.intranet.cn";

    public static String MessageUrl = "/account/v1/get_sms";//发送短信

    public static String loginUrl = "/account/v1/app/tel_login";//手机登录

    public static String realNameUrl = "/account/v1/auth/plat/user/idcard_check";//实名认证

    public static String userStatusUrl = "/account/v1/auth/plat/user/get_card_status";//用户实名状态

    public static String placeOrderUrl = "/pay/v1/auth/pay/add_order";//下单

    public static String wxOpenidUrl = "/pay/v1/auth/pay/get_wechat_openid";//用户微信openid

    public static String passwordUrl = "/account/v1/auth/plat/user/set_pwd";//设置密码

    public static String wxUserInfoUrl = "/account/v1/auth/plat/get_wechat_user_info";//微信个人信息

    public static String bindWechatUrl = "/account/v1/auth/plat/user/bind_wechat";//绑定微信

    public static String bindPhoneUrl = "/account/v1/auth/plat/user/bind_tel";//绑定手机

    public static String userInfoUrl = "/account/v1/auth/plat/user/get_info";//获取用户信息

    public static String nicknameAvatarUrl = "/account/v1/auth/plat/user/change_user_nick";//修改昵称头像

    public static String userAntiUrl = "/anti/report";//防沉迷

    public static String userSdkUrl = "/account/v1/auth/plat/sdk_init";

}
