package com.atguigu.demo.utils;

/**
 * @author bling
 * @create 2022-09-14 11:40
 */
public class WebUtils {
    //判断字符串是否为null或“”
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
