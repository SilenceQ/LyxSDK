package com.antiaddiction.sdk.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private static String dripZero(String str){
        if(str != null && str.length() > 1){
            if(str.startsWith("0")){
                return str.substring(1);
            }else{
                return str;
            }
        }else{
            return str;
        }
    }
}
