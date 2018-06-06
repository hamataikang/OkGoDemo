package com.zdjt.guess;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lzy.okgo.model.Response;
import com.zdjt.guess.bean.UserResponse;
import com.zdjt.guess.service.JsonCallback;
import com.zdjt.guess.service.UserService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private String url = "http://www.dudula.cn/mobapp/guname/gudainame.html";
    private WebView myWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.webView);
        loadUrl();

    }

    @Override
    protected void onResume() {
        super.onResume();
        UserService.getUserInfo(this, myCallback);      //接口请求获取用户信息
    }



    JsonCallback myCallback = new JsonCallback() {
        @Override
        public void onSuccess(Response response) {
            UserResponse userResponse = (UserResponse) response.body();
            Log.e("00000000",userResponse.code + "---" + userResponse.data);

            //TODO  通过userResponse中的status判断，如果成功处理数据，如果失败提示错误信息
        }
    };

    private void loadUrl() {
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setUseWideViewPort(false);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);

        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = myWebView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(myWebView.getSettings(), true);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);//
        webSettings.setAllowFileAccess(true);


        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();// 接受所有网站的证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url!=""){
                    view.loadUrl(url);   //在当前的webview中跳转到新的url
                    System.out.println("url:"+url);
                }
                return true;
            }
        });

        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        myWebView.loadUrl(url);
    }


}
