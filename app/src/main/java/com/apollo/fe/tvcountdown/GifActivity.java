package com.apollo.fe.tvcountdown;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.apollo.fe.tvcountdown.bgabanner.BGABanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 公司前台电视上使用的上市时间倒计时小程序
 */
public class GifActivity extends AppCompatActivity {

    private ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        image = findViewById(R.id.image);
        String  url = "file:///android_asset/four.gif";
        Glide.with(this)
                .asGif()
                .load(url)
//				.error(R.mipmap.img_default)//异常时候显示的图片
//				.placeholder(R.mipmap.img_default)//加载成功前显示的图片
//				.fallback(R.mipmap.img_default)//url为空的时候,显示的图片
//				.skipMemoryCache(SKIP_MEMORY_CACHE)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);

    }

}
