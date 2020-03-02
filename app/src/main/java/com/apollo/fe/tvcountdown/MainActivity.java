package com.apollo.fe.tvcountdown;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 公司前台电视上使用的上市时间倒计时小程序
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.d_tv1)
    ImageView dTv1;
    @BindView(R.id.d_tv2)
    ImageView dTv2;
    @BindView(R.id.d_tv3)
    ImageView dTv3;
    @BindView(R.id.days_ll)
    LinearLayout daysLl;
    @BindView(R.id.tv1)
    ImageView tv1;
    @BindView(R.id.tv2)
    ImageView tv2;
    @BindView(R.id.tv3)
    ImageView tv3;
    @BindView(R.id.tv4)
    ImageView tv4;
    @BindView(R.id.tv5)
    ImageView tv5;
    @BindView(R.id.tv6)
    ImageView tv6;
    @BindView(R.id.git_iv)
    ImageView gitIv;
    @BindView(R.id.video_view)
    VideoView videoView;

    private Unbinder unbinder;

    private long hour = 0;
    private long minute = 0;
    private long second = 0;

    private long testHour = 0;
    private long testMinute = 0;
    private long testSecond = 0;

    private long endTime;
    private long startTime;
    private long midTime;
    private String days = "";
    private String testDay = "";
    private long time1, time2;//测试程序运行时间
    private List<Drawable> drawablesList = new ArrayList<>();
    private List<Drawable> smallImgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initList();
        days = getDays();
        initDate();
        startCountDown();
//        Glide.with(MainActivity.this).load(R.drawable.count_down).diskCacheStrategy(DiskCacheStrategy.NONE).into(gitIv);

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/video"));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
    }

    /**
     * 初始化 大小 数字list
     */
    private void initList() {
        drawablesList.add(getDrawable(R.mipmap.big_0));
        drawablesList.add(getDrawable(R.mipmap.big_1));
        drawablesList.add(getDrawable(R.mipmap.big_2));
        drawablesList.add(getDrawable(R.mipmap.big_3));
        drawablesList.add(getDrawable(R.mipmap.big_4));
        drawablesList.add(getDrawable(R.mipmap.big_5));
        drawablesList.add(getDrawable(R.mipmap.big_6));
        drawablesList.add(getDrawable(R.mipmap.big_7));
        drawablesList.add(getDrawable(R.mipmap.big_8));
        drawablesList.add(getDrawable(R.mipmap.big_9));

        smallImgList.add(getDrawable(R.mipmap.small_0));
        smallImgList.add(getDrawable(R.mipmap.small_1));
        smallImgList.add(getDrawable(R.mipmap.small_2));
        smallImgList.add(getDrawable(R.mipmap.small_3));
        smallImgList.add(getDrawable(R.mipmap.small_4));
        smallImgList.add(getDrawable(R.mipmap.small_5));
        smallImgList.add(getDrawable(R.mipmap.small_6));
        smallImgList.add(getDrawable(R.mipmap.small_7));
        smallImgList.add(getDrawable(R.mipmap.small_8));
        smallImgList.add(getDrawable(R.mipmap.small_9));
    }

    /**
     * 开始倒计时
     */
    private void startCountDown() {
        new Timer().schedule(new TimerTask() {
            public void run() {

                time1();
            }
        }, 0, 1000);
    }

    /**
     * 初始化当天时间还剩余的秒数
     */
    private void initDate() {
        startTime = System.currentTimeMillis();    //当前时间毫秒数
        long zeroT = startTime / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
        endTime = zeroT + 24 * 60 * 60 * 1000 - 1;  //今天23点59分59秒的毫秒数

        midTime = (endTime - startTime) / 1000 + 4;//TODO 不知道为什么 倒计时时间老是 快走了  4s左右

//        time1 = System.currentTimeMillis();
//        System.out.println("开始时间:" + time1);
    }

//    private void initDate() {
//        Date currentDate = new Date();
//        Calendar midnight= Calendar.getInstance();
//        midnight.setTime(currentDate);
//        midnight.add(midnight.DAY_OF_MONTH,1);
//        midnight.set(midnight.HOUR_OF_DAY,0);
//        midnight.set(midnight.MINUTE,0);
//        midnight.set(midnight.SECOND,0);
//        midnight.set(midnight.MILLISECOND,0);
//        midTime = (midnight.getTime().getTime()-currentDate.getTime())/1000;
//    }

    /**
     * 给定时长倒计时
     */
    private void time1() {
        if (midTime > 0) {
            midTime--;
            long hh = midTime / 60 / 60 % 60;
            long mm = midTime / 60 % 60;
            long ss = midTime % 60;
//            System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");
            hour = hh;
            minute = mm;
            second = ss;
            updateUi();
        } else {//一天时间倒计时走完之后
            days = String.valueOf(Integer.parseInt(days) - 1);
            initDate();
            startCountDown();
        }
    }


    /**
     * 更新界面
     */
    private void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                if (second == 0) {
                    tv5.setImageDrawable(smallImgList.get(0));
                    tv6.setImageDrawable(smallImgList.get(0));
                } else if (second > 0 && second < 10) {
                    tv5.setImageDrawable(smallImgList.get(0));
                    tv6.setImageDrawable(smallImgList.get((int) second));
                } else if (second >= 10) {
                    String secondStr = String.valueOf(second);
                    tv5.setImageDrawable(smallImgList.get(Integer.parseInt(secondStr.substring(0, 1))));
                    tv6.setImageDrawable(smallImgList.get(Integer.parseInt(secondStr.substring(1, 2))));
                }

                if (testMinute != minute) {
                    if (minute == 0) {
                        tv3.setImageDrawable(smallImgList.get(0));
                        tv4.setImageDrawable(smallImgList.get(0));
                    } else if (minute > 0 && minute < 10) {
                        tv3.setImageDrawable(smallImgList.get(0));
                        tv4.setImageDrawable(smallImgList.get((int) minute));
                    } else if (minute >= 10) {
                        String minuteStr = String.valueOf(minute);
                        tv3.setImageDrawable(smallImgList.get(Integer.parseInt(minuteStr.substring(0, 1))));
                        tv4.setImageDrawable(smallImgList.get(Integer.parseInt(minuteStr.substring(1, 2))));
                    }
                    testMinute = minute;
                }

                if (testHour != hour) {
                    if (hour == 0) {
                        tv1.setImageDrawable(smallImgList.get(0));
                        tv2.setImageDrawable(smallImgList.get(0));
                    } else if (hour > 0 && hour < 10) {
                        tv1.setImageDrawable(smallImgList.get(0));
                        tv2.setImageDrawable(smallImgList.get((int) hour));
                    } else if (hour >= 10) {
                        String hourStr = String.valueOf(hour);
                        tv1.setImageDrawable(smallImgList.get(Integer.parseInt(hourStr.substring(0, 1))));
                        tv2.setImageDrawable(smallImgList.get(Integer.parseInt(hourStr.substring(1, 2))));
                    }
                    testHour = hour;
                }

                if (days != null && !days.equals("") && !testDay.equals(days)) {
                    if (days.length() == 3) {
                        dTv1.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(0, 1))));
                        dTv2.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(1, 2))));
                        dTv3.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(2, 3))));
                    } else if (days.length() == 2) {
                        dTv1.setVisibility(View.INVISIBLE);
                        dTv2.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(0, 1))));
                        dTv3.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(1, 2))));
                    } else if (days.length() == 1) {
                        dTv1.setVisibility(View.INVISIBLE);
                        dTv2.setVisibility(View.INVISIBLE);
                        dTv3.setImageDrawable(drawablesList.get(Integer.parseInt(days.substring(0, 1))));
                    }

                    testDay = days;
                }


//                time2 = System.currentTimeMillis();
//                System.out.println("结束时间" + time2);
//                System.out.println("运行时长" + (time2 - time1));
            }
        });
    }


    /**
     * 获取上市时间 距离现在还有多少天
     *
     * @return
     */
    private String getDays() {
        Date nowDate = new Date(System.currentTimeMillis());//当前时间
        long nowDateLong = nowDate.getTime();
        String endTimeStr = "2020-11-03" + " 00:00:00";
        // String endTimeStr = "2017-12-29"+ " 15:45:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date enddate = null;
        try {
            enddate = simpleDateFormat.parse(endTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long enddateLong = enddate.getTime();//结束时间
        long timeLong = enddateLong - nowDateLong;//剩余时间

        if (timeLong <= 0) {
            return "上市时间到";
        } else {
            if (timeLong < 60 * 1000)
                return "0";
            else if (timeLong < 60 * 60 * 1000) {
                return "0";
            } else if (timeLong < 60 * 60 * 24 * 1000) {
                return "0";
            } else { //(timeLong<60*60*24*1000*7)
                Long timeLongD = timeLong / (1000 * 60 * 60 * 24);
                return timeLongD + "";
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
