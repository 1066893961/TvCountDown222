package com.apollo.fe.tvcountdown;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.apollo.fe.tvcountdown.bgabanner.BGABanner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 公司前台电视上使用的上市时间倒计时小程序
 */
public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                PersonWebViewActivity.startActivity(getApplicationContext());
                break;
            case R.id.btn2:
               startActivity(new Intent(this, MainActivity.class));
                break;
           case R.id.btn3:
               startActivity(new Intent(this, GifActivity.class));
                break;
        }
    }
}
