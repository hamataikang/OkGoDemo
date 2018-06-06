package com.zdjt.guess.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by adminn on 2017/6/29.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Context context;
    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Context context){
        this.context = context;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null){
            data = gson.fromJson(jsonReader, type);
        }else if (clazz != null){
            data = gson.fromJson(jsonReader, clazz);
        }else{
            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            data = gson.fromJson(jsonReader,type);
        }
        return data;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
//        request.headers("token", UserApplication.token)
//                .headers("version", String.valueOf(StringUtils.getVersionNum(context)))
//                .headers("fromType","android");
    }


    @Override
    public void onFinish() {
        super.onFinish();
//        if (context != null && DialogUtils.loadingDialog != null){
//            DialogUtils.closeDialog();
//        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
//        if (context != null){
//            ToastUtil.showToastShort(context, "网络连接失败，请重试");
////            StringUtils.showToast(context, "网络连接失败，请重试");
//        }
    }
}
