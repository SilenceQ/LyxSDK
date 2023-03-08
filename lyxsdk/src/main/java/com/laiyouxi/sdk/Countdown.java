package com.laiyouxi.sdk;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Countdown extends RelativeLayout {
    private Context context;
    private TextView tv_countdown;
    private boolean runningDownTimer;     //判断是否在倒计时

    public Countdown (Context context) {
        super(context);
        this.context = context;
        loadView();
    }

    public Countdown (Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadView();
        downTimer.start();
    }

    private void loadView() {
        View view = LayoutInflater.from(context).inflate(R.layout.countdown_time, this);
        tv_countdown = (TextView) view.findViewById(R.id.tv_countdown);
        tv_countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (runningDownTimer) {
                    return;
                }
                downTimer.start();
                onFinshListener.onFinish();
            }
        });
    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            tv_countdown.setText((l / 1000) + "s");
            tv_countdown.setBackgroundResource(R.drawable.layout_send_message_button);
            tv_countdown.setTextColor(Color.parseColor("#ffffff"));
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            tv_countdown.setText("重新发送");
            tv_countdown.setBackgroundResource(R.drawable.layout_qq_shape);
            tv_countdown.setTextColor(Color.parseColor("#ffffff"));
        }

    };



    //定义回调
    public interface OnFinshListener {
        //输入完成监听
        void onFinish();
    }

    private OnFinshListener onFinshListener;

    public void setOnFinshListener(OnFinshListener onFinshListener) {
        this.onFinshListener = onFinshListener;
    }
}
