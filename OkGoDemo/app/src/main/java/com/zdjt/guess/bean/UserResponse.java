package com.zdjt.guess.bean;

import java.io.Serializable;

/**
 * Created by jsb-cpyy on 2017/10/17.
 */

public class UserResponse<T> implements Serializable {
    public int status;      //后台返回状态信息（判断成功与否）
    public String code;     //后台返回状态码（判断各种情况）
    public String message;  //后台返回提示信息
    public T data;      //后台返回数据信息

    public UserResponse() {
    }

    public UserResponse(int status, String message, String code, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
