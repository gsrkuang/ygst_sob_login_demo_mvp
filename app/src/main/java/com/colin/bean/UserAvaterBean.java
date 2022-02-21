package com.colin.bean;

/**
 * Date:2022-02-21
 * Time:19:45
 * author:colin
 * 用于登陆界面，填写手机号码的时候自动显示用户头像
 *
 *{
 *     "success": true,
 *     "code": 10000,
 *     "message": "获取头像成功",
 *     "data": "https://images.sunofbeaches.com/content/2021_12_02/915973725776510976.png"
 * }
 */
public class UserAvaterBean {
    boolean success;
    int code;
    String message;
    String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserAvaterBean{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
