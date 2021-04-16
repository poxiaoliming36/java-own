package com.lq.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description 配置文件属性管理
 * @Date 2021/4/15 22:39
 * @Author lq
 */

public class PropertyMgr {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            System.out.println("配置文件加载出错");
            e.printStackTrace();
        }
    }

    public static String getKey(String key){
        if (key == null){
            return null;
        }
        return properties.getProperty(key);
    }
}
