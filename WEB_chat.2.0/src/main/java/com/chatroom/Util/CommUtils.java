package com.chatroom.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CommUtils {
    private CommUtils(){

    }
    private static final Gson gson=new GsonBuilder().create();
    /**
     *根据指定的文件名加载配置文件
     * @param fileName 配置文件名
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String fileName) {

        Properties properties=new Properties();
        //获取到当前配置文件夹下的文件输入流
        InputStream in=CommUtils.class.getClassLoader().
                getResourceAsStream(fileName);
        //加载配置文件中的所有内容
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    public static String object2Json(Object obj) {
        return gson.toJson(obj);
    }

    public static Object json2Object(String jsonStr,Class objClass) {
        return gson.fromJson(jsonStr,objClass);
    }

    public static boolean strIsNull(String str) {
        //避免空指针异常
        return str == null || str.equals("");
    }
}
