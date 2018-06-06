package com.zdjt.guess.service;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zdjt.guess.bean.LoginModel;
import com.zdjt.guess.bean.UserResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户个人信息模块接口方法
 */

public class UserService extends BaseService {

    /**
     * 获取用户信息
     * @param context
     * @param myCallback
     */
    public static void getUserInfo(Context context, final JsonCallback myCallback){
        OkGo.<UserResponse>get(BaseService.BASE_URL)
                .tag(context)
                .params("appid","001")      //（传递参数方法一）
                .execute(new JsonCallback<UserResponse>(context) {
                    @Override
                    public void onSuccess(Response<UserResponse> response) {
                        myCallback.onSuccess(response);
                    }
                });
    }

    /**
     * 封装用户登录上传的信息json
     * @param phone
     * @param password
     * @return
     */
    public static JSONObject getLoginInfoJson(String phone, String password){
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    /**
     * 登录
     * @param jsonObject
     * @param context
     * @param myCallback
     */
    public static void login(JSONObject jsonObject, Context context, final JsonCallback myCallback){
        OkGo.<UserResponse<LoginModel>>post(BaseService.LOGIN_URL)
                .tag(context)
                .params("jsonData",jsonObject.toString())       //（传递参数方法二）
                .execute(new JsonCallback<UserResponse<LoginModel>>(context) {
                    @Override
                    public void onSuccess(Response<UserResponse<LoginModel>> response) {
                        myCallback.onSuccess(response);
                    }
                });

    }

}
