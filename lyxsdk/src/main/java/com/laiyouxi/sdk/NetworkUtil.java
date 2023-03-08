package com.laiyouxi.sdk;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtil {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 字节数组To16进制字符串
     *
     * @param b 字节数组
     * @return 16进制字符串
     */
    private static String convertToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte a : b) {
            sb.append(HEX_DIGITS[(a & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[a & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * 获取字符串的HashCode值
     *
     * @param str String
     * @return HashCode
     */
    public static String getMD5String(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md5.update(str.getBytes());
        return convertToHexString(md5.digest());
    }


    public static String urlEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, Object> post(String url, Map<String, String> map_header, Map<String, String> map_body) {

        OkHttpClient okHttpClient = new OkHttpClient();
        //添加header信息
        Headers.Builder builder_header = new Headers.Builder();
        for (String key : map_header.keySet()) {
            builder_header.add(key, Objects.requireNonNull(map_header.get(key)));
        }
        Headers headers = builder_header.build();
        //添加参数
        FormBody.Builder builder_body = new FormBody.Builder();
        for (String key : map_body.keySet()) {
            builder_body.add(key, Objects.requireNonNull(map_body.get(key)));
        }
        FormBody formBody = builder_body.build();
        Request request = new Request.Builder().url(url).headers(headers).post(formBody).build();
        //准备好请求的call对象
        Call call = okHttpClient.newCall(request);
        //异步请求的方法enqueue
        try {
            Response response = call.execute();
            Gson gson = new Gson();
            Type sendtype = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> map_call = gson.fromJson(response.body().string(), sendtype);
            return map_call;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
