package com.example.webviewtest;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JSKit {

    // 定义JS需要调用的方法，被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        Log.d("msg", "JS成功调用了Android的hello方法");
    }
}
