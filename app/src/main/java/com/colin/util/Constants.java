package com.colin.util;

public class Constants {

    /**
     * 阳光海滩首页
     */
    public static final String SUNNY_BEACH_SITE_BASE_URL = "https://www.sunofbeach.net/";

    /**
     * 阳光海滩 api 接口前缀
     */
    public static final String api_main = "https://api.sunofbeaches.com/";

    /**
     * 根据用户填写的手机号码获取用户头像
     * @param phoneNum
     * @return
     */
    public static String api_login_avater(String phoneNum){
        return api_main + "/uc/user/avatar/{phoneNum}";
    }

    public static final String LOGIN = "uc/user/login/{captcha}";

    public static final String REGIST = "user/reg";
}
