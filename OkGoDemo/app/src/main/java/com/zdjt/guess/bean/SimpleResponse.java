package com.zdjt.guess.bean;

import java.io.Serializable;

/**
 * Created by adminn on 2017/7/4.
 */

public class SimpleResponse implements Serializable {
    public int status;
    public String message;

    public UserResponse toUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.status = status;
        userResponse.message = message;
        return userResponse;
    }
}
