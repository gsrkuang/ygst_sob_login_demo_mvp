package com.colin.biz;

import com.colin.bean.LoginBean;

/**
 * Date:2022-02-17
 * Time:18:23
 * author:colin
 */
interface OnLoginListener {
    void loginSuccess(LoginBean loginBean);

    void loginFailed();
}
