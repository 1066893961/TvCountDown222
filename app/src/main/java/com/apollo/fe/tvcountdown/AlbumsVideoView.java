package com.apollo.fe.tvcountdown;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/1/2 10:12
 */
public class AlbumsVideoView extends VideoView {
    public AlbumsVideoView(Context context) {
        super(context);
    }

    public AlbumsVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlbumsVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}

