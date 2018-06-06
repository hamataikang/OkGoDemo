package com.zdjt.guess.bean;

import java.io.Serializable;

/**
 * 登录接口返回信息model
 */

public class LoginModel implements Serializable {
    public String phone;        //手机号
    public String token;

    public LoginModel() {
    }

    public LoginModel(String phone, String token) {
        this.phone = phone;
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
