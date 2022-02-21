package com.colin.view;

import com.colin.bean.LoginBean;

/**
 * Date:2022-02-16
 * Time:17:18
 * author:colin
 */
public interface IUserLoginView {
    void showLogin(LoginBean loginBean,String captcha);
    String getUserName();
    String getPassword();
    String getCaptcha();

}
