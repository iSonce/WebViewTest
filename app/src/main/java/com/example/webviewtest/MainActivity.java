package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置与Js交互的权限
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// 设置允许JS弹窗
        //参数1：Java对象名 参数2：Javascript对象名
        //通过addJavascriptInterface() AJavaScriptInterface类对象映射到JS的mjs对象
        mWebView.addJavascriptInterface(new JSKit(),"mjs");
        // 加载JS代码
        mWebView.loadUrl("file:///android_asset/javascript/index.html");
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Handler发送消息
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 调用javascript的callJS()方法
                        mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                            @Override
                            // 处理JS返回的参数
                            public void onReceiveValue(String value) {
                                Log.d("msg", "onReceiveValue: "+ value);
                            }
                        });
                    }
                });
            }
        });
        // 由于JS中需要alert弹窗显示结果，所以要设置setWebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient());
    }
}