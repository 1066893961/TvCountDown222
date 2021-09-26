package com.apollo.fe.tvcountdown;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by leo.li on 2017/5/10.
 * https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=1&channelName=apollo
 * <p>
 * https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=2&channelName=bill
 * <p>
 * https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=3&channelName=scb
 * <p>
 * https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=4&channelName=book
 */

public class PersonWebViewActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView Webview;
    private Unbinder unbinder;
    private List<String> urlList = new ArrayList<>();
    private int tag = 0;//
    private Handler mHandler = new MyHandler();
    private static final int REQUEST_MSG = 200;

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_MSG:
                    Webview.loadUrl(urlList.get(tag));
                    tag++;
                    if (tag == 3) {
                        tag = 0;
                    }
                    break;
            }
        }
    }

    private void sendMessageDelayed(int what, long delayMillis) {
        if (mHandler != null) {
            mHandler.removeMessages(what);
            mHandler.sendEmptyMessageDelayed(what, delayMillis);
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PersonWebViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        unbinder = ButterKnife.bind(this);

        urlList.add("https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=1&channelName=apollo");
        urlList.add("https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=2&channelName=bill");
        urlList.add("https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=3&channelName=scb");
        urlList.add("https://blockchain-view.huijinchain.com/#/dashboard/platformBlockDt?channelId=4&channelName=book");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebSettings webSettings = Webview.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        Webview.setWebChromeClient(new WebChromeClient());
        Webview.getSettings().setUserAgentString("PC");


        Webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && Webview.canGoBack()) {
                        Webview.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                sendMessageDelayed(REQUEST_MSG,0);
            }

        }, 1000, 5000);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        super.onDestroy();
    }

}
