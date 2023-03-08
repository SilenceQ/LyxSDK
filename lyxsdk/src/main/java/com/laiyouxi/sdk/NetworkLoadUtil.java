package com.laiyouxi.sdk;

import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

public class NetworkLoadUtil {

    private static String token = "";

    //获取短信验证码
    public static void sendMessage(String tel,OnNetworkListener onNetworkListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "");
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("tel="+NetworkUtil.urlEncoded(tel)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("tel", tel);
        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.MessageUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            onNetworkListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))),String.valueOf(map_call.get("message")), (Map<String, Object>) map_call.get("data"));

        } else {
            onNetworkListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //手机号登录
    public static void phoneLoginTelCode(LoginTelCodeInfo loginTelCode, OnNetworkListener onNetworkListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "");
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("appId="+NetworkUtil.urlEncoded(loginTelCode.getAppId())+"&mchId="+NetworkUtil.urlEncoded(loginTelCode.getMchId())+"&tel="+NetworkUtil.urlEncoded(loginTelCode.getTel())+"&telCode="+NetworkUtil.urlEncoded(loginTelCode.getTelCode())).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("tel", loginTelCode.getTel());
        map_body.put("appId", loginTelCode.getAppId());
        map_body.put("mchId", loginTelCode.getMchId());
        map_body.put("telCode", loginTelCode.getTelCode());
        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.loginUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            onNetworkListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))),String.valueOf(map_call.get("message")), (Map<String, Object>) map_call.get("data"));
            Map<String, Object> map_1 = (Map<String, Object>) map_call.get("data");
            token = String.valueOf(map_1.get("token"));
        } else {
            onNetworkListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))),String.valueOf(map_call.get("message")));
        }
    }

    //实名认证
    public static void realNameCertification(RealNameCertificationInfo realNameCertification, OnRealNameListener onRealNameListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("age="+NetworkUtil.urlEncoded(realNameCertification.getAge())+"&card="+NetworkUtil.urlEncoded(realNameCertification.getCard())+"&name="+NetworkUtil.urlEncoded(realNameCertification.getName())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("name", realNameCertification.getName());
        map_body.put("card", realNameCertification.getCard());
        map_body.put("age", realNameCertification.getAge());
        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.realNameUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            onRealNameListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        } else {
            onRealNameListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }


//    platId -> bEtYTTI4NVlLbEQ2Uk5hbkFWM1o=
//    unionId -> MDlWbGQ3WWthREdZNFFCTG4zWno=
//    openId -> TFI2ZEpsT1pNYllHTndLRXh2Ymo=
//    token -> eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoidXNlciIsInBsYXRJZCI6ImJFdFlUVEk0TlZsTGJFUTJVazVoYmtGV00xbz0iLCJ1bmlvbklkIjoiTURsV2JHUTNXV3RoUkVkWk5GRkNURzR6V25vPSIsIm9wZW5JZCI6IlRGSTJaRXBzVDFwTllsbEhUbmRMUlhoMlltbz0iLCJtY2hJZCI6MzMzMywiYXBwSWQiOjQzLCJpYXQiOjE2Nzc3NDE1OTMsImV4cCI6MTY3NzgyNzk5MywiZXh0cmEiOiJbXSJ9.oLn2RApSc0wiJMAHwTkmuck36Gp4qK59XjJ2lvS4PVQ
    //获取用户实名状态
    public static void requireUserStatus(OnUserStatusListener onUserStatusListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.userStatusUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            UserStatusInfo model = new UserStatusInfo();
            model.setAge((int)(Double.parseDouble(String.valueOf(map_user.get("age")))));
            model.setStatus((boolean) map_user.get("status"));
            model.setRequireAge((int)(Double.parseDouble(String.valueOf(map_user.get("requireAge")))));
            onUserStatusListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), model);
        } else {
            onUserStatusListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //用户充值
    public static void payUserOrder(String mchId, String appId, PayUserOrderInfo payUserOrder, OnPayOrderListener onPayOrderListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("alipayPcLinkType="+NetworkUtil.urlEncoded(payUserOrder.getAlipayPcLinkType())+"&appId="+NetworkUtil.urlEncoded(appId)+"&goodsId="+NetworkUtil.urlEncoded(payUserOrder.getGoodsId())+"&goodsNum="+NetworkUtil.urlEncoded(payUserOrder.getGoodsNum())+"&goodsTitle="+NetworkUtil.urlEncoded(payUserOrder.getGoodsTitle())+"&idType="+NetworkUtil.urlEncoded(payUserOrder.getIdType())+"&mchId="+NetworkUtil.urlEncoded(mchId)+"&mchOrderId="+NetworkUtil.urlEncoded(payUserOrder.getMchOrderId())+"&openId="+NetworkUtil.urlEncoded(payUserOrder.getOpenId())+"&orderTime="+NetworkUtil.urlEncoded(payUserOrder.getOrderTime())+"&payMethod="+NetworkUtil.urlEncoded(payUserOrder.getPayMethod())+"&platId="+NetworkUtil.urlEncoded(payUserOrder.getPlatId())+"&returnUrl="+NetworkUtil.urlEncoded(payUserOrder.getReturnUrl())+"&totalPrice="+NetworkUtil.urlEncoded(payUserOrder.getTotalPrice())+"&unionId="+NetworkUtil.urlEncoded(payUserOrder.getUnionId())+"&wxOpenId="+NetworkUtil.urlEncoded(payUserOrder.getWxOpenId())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("payMethod", payUserOrder.getPayMethod());
        map_body.put("mchOrderId", payUserOrder.getMchOrderId());
        map_body.put("goodsId", payUserOrder.getGoodsId());
        map_body.put("goodsNum", payUserOrder.getGoodsNum());
        map_body.put("goodsTitle", payUserOrder.getGoodsTitle());
        map_body.put("totalPrice", payUserOrder.getTotalPrice());
        map_body.put("mchId", mchId);
        map_body.put("appId", appId);
        map_body.put("unionId", payUserOrder.getUnionId());
        map_body.put("openId", payUserOrder.getOpenId());
        map_body.put("platId", payUserOrder.getPlatId());
        map_body.put("idType", payUserOrder.getIdType());
        map_body.put("returnUrl", payUserOrder.getReturnUrl());
        map_body.put("wxOpenId", payUserOrder.getWxOpenId());
        map_body.put("orderTime", payUserOrder.getOrderTime());
        map_body.put("alipayPcLinkType", payUserOrder.getAlipayPcLinkType());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.placeOrderUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            PayOrderResultInfo model = new PayOrderResultInfo();
            model.setPayMethod(String.valueOf(map_user.get("payMethod")));
            model.setPrepayId(String.valueOf(map_user.get("prepayId")));
            model.setUrl(String.valueOf(map_user.get("url")));
            onPayOrderListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), model);
        } else {
            onPayOrderListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //获取用户的微信openid
    public static void getWXOpenid(WechatOpenidInfo wxOpenid, OnGetWXOpenidListener onGetWXOpenidListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("wxAppId="+NetworkUtil.urlEncoded(wxOpenid.getWxAppId())+"&wxCode="+NetworkUtil.urlEncoded(wxOpenid.getWxCode())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("wxCode", wxOpenid.getWxCode());
        map_body.put("wxAppId", wxOpenid.getWxAppId());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.wxOpenidUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            onGetWXOpenidListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), String.valueOf(map_user.get("wxOpenId")));
        } else {
            onGetWXOpenidListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //设置密码
    public static void setPassword(SetPasswordInfo setPasswordInfo, OnSetPasswordListener onSetPasswordListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("password="+NetworkUtil.urlEncoded(setPasswordInfo.getPassword())+"&wxCode="+NetworkUtil.urlEncoded(setPasswordInfo.getTelCode())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("telCode", setPasswordInfo.getTelCode());
        map_body.put("password", setPasswordInfo.getPassword());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.passwordUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {

            onSetPasswordListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        } else {
            onSetPasswordListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //获取用户微信个人信息
    public static void getWXUserInfo(WechatUserInfo wechatUserInfo, OnGetWXUserInfoListener onGetWXUserInfoListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("wxAppId="+NetworkUtil.urlEncoded(wechatUserInfo.getWxAppId())+"&wxAuthCode="+NetworkUtil.urlEncoded(wechatUserInfo.getWxAuthCode())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("wxAuthCode", wechatUserInfo.getWxAuthCode());
        map_body.put("wxAppId", wechatUserInfo.getWxAppId());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.wxUserInfoUrl, map_header, map_body);

        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            onGetWXUserInfoListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), String.valueOf(map_user.get("openid")), String.valueOf(map_user.get("unionid")));
        } else {
            onGetWXUserInfoListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //绑定微信
    public static void bindWechat(BindWechatInfo bindWechatInfo, OnBindWechatListener onBindWechatListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("changeBind="+NetworkUtil.urlEncoded(bindWechatInfo.getChangeBind())+"&telCode="+NetworkUtil.urlEncoded(bindWechatInfo.getTelCode())+"&wxAuthCode="+NetworkUtil.urlEncoded(bindWechatInfo.getWxAuthCode())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("changeBind", bindWechatInfo.getChangeBind());
        map_body.put("telCode", bindWechatInfo.getTelCode());
        map_body.put("wxAuthCode", bindWechatInfo.getWxAuthCode());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.bindWechatUrl, map_header, map_body);

        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            onBindWechatListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        } else {
            onBindWechatListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //绑定手机
    public static void bindPhone(BindPhoneInfo bindPhoneInfo, OnBindPhoneListener onBindPhoneListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("changeBind="+NetworkUtil.urlEncoded(bindPhoneInfo.getChangeBind())+"&oldTel="+NetworkUtil.urlEncoded(bindPhoneInfo.getOldTel())+"&tel="+NetworkUtil.urlEncoded(bindPhoneInfo.getTel())+"&telCode="+NetworkUtil.urlEncoded(bindPhoneInfo.getTelCode())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("changeBind", bindPhoneInfo.getChangeBind());
        map_body.put("telCode", bindPhoneInfo.getTelCode());
        map_body.put("tel", bindPhoneInfo.getTel());
        map_body.put("oldTel", bindPhoneInfo.getOldTel());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.bindPhoneUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            onBindPhoneListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        } else {
            onBindPhoneListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //获取用户信息
    public static void getUserInfo(String platId, OnUserInfoListener onUserInfoListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("platId="+NetworkUtil.urlEncoded(platId)+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("platId", platId);

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.userInfoUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            GetUserInfo userInfo = new GetUserInfo();
            userInfo.setNick(String.valueOf(map_user.get("nick")));
            userInfo.setAvatar(String.valueOf(map_user.get("avatar")));
            userInfo.setTel(String.valueOf(map_user.get("tel")));
            userInfo.setRegTime((int) Double.parseDouble(String.valueOf(map_user.get("regTime"))));
            userInfo.setLoginTime((int) Double.parseDouble(String.valueOf(map_user.get("loginTime"))));
            userInfo.setLoginIp(String.valueOf(map_user.get("loginIp")));
            userInfo.setLastLoginTime((int) Double.parseDouble(String.valueOf(map_user.get("lastLoginTime"))));
            userInfo.setAge((int) Double.parseDouble(String.valueOf(map_user.get("age"))));
            userInfo.setCard(String.valueOf(map_user.get("card")));
            userInfo.setName(String.valueOf(map_user.get("name")));
            onUserInfoListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), userInfo);
        } else {
            onUserInfoListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //修改昵称头像
    public static void modifyNicknameAvatar(ModifyNicknameAvatarInfo modifyNicknameAvatarInfo, OnModifyNicknameAvatarListener onModifyNicknameAvatarListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("avatar="+NetworkUtil.urlEncoded(modifyNicknameAvatarInfo.getAvatar())+"&nick="+NetworkUtil.urlEncoded(modifyNicknameAvatarInfo.getNick())+"&platId="+NetworkUtil.urlEncoded(modifyNicknameAvatarInfo.getPlatId())+"&token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();
        map_body.put("avatar", modifyNicknameAvatarInfo.getAvatar());
        map_body.put("nick", Base64.encodeToString(modifyNicknameAvatarInfo.getNick().getBytes(), Base64.DEFAULT));
        map_body.put("platId", modifyNicknameAvatarInfo.getPlatId());

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.nicknameAvatarUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            onModifyNicknameAvatarListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        } else {
            onModifyNicknameAvatarListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }
    }

    //防沉迷
    public static void getAntiInfo(OnUserAntiListener onUserAntiListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.userAntiUrl, map_header, map_body);

        onUserAntiListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), (int) Double.parseDouble(String.valueOf(map_call.get("code"))));

    }

    //SDK初始化
    public static void initSDKInfo(OnInitSdkListener onInitSdkListener) {
        Map<String, String> map_header = new HashMap<>();
        map_header.put("Authorization", "Bearer "+token);
        map_header.put("Content-Type", "application/x-www-form-urlencoded");
        map_header.put("time", ""+System.currentTimeMillis()/1000);
        map_header.put("sign", NetworkUtil.getMD5String("token="+NetworkUtil.urlEncoded(token)).toUpperCase());
        Map<String, String> map_body = new HashMap<>();

        Map<String, Object> map_call = NetworkUtil.post(PublicParameters.urlPath+PublicParameters.userSdkUrl, map_header, map_body);
        if (((int) Double.parseDouble(String.valueOf(map_call.get("code")))) == 0) {
            Map<String, Object> map_user = (Map<String, Object>) map_call.get("data");
            InitSdkInfo initSdkInfo = new InitSdkInfo();
            initSdkInfo.setOnline_limit((int) Double.parseDouble(String.valueOf(map_user.get("online_limit"))));
            initSdkInfo.setApp_verified((int) Double.parseDouble(String.valueOf(map_user.get("app_verified"))));
            onInitSdkListener.onSuccess((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")), initSdkInfo);
        } else {
            onInitSdkListener.onFail((int) Double.parseDouble(String.valueOf(map_call.get("code"))), String.valueOf(map_call.get("message")));
        }


    }
}
