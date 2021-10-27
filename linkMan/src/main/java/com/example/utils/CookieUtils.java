package com.example.utils;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-20 17:47:58
 */

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie createAndSetCookie(String name, String value, int time, String path){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        cookie.setPath(path);
        return cookie;
    }

    public static String getCookieValue(Cookie[] cookies, String name){
        String value = null;
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)){
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }
}
