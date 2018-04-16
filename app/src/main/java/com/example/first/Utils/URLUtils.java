package com.example.first.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 大学生小赵 on 2018/4/15.
 */

public class URLUtils {
    public static String getURLIP(Context context){
        String ip =null;
        try {
            InputStream is = context.getAssets().open("url.properties");
            Properties properties = new Properties();
            properties.load(is);
            ip = properties.getProperty("ip");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
}
