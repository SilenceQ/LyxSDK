package com.antiaddiction.sdk.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antiaddiction.sdk.utils.Res;
import com.antiaddiction.sdk.utils.UnitUtils;
import com.laiyouxi.sdk.LaiyouxiSdk;


public class AntiDialog extends BaseDialog implements View.OnClickListener {

    private Button btn_sure;

    private TextView tv_content;

    private String content;

    private LinearLayout ll_container;

    public AntiDialog(Context context, String content) {
        super(context);
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Res.layout(getContext(), "dialog_anti_tip"));
        setCancelable(false);

        ll_container = (LinearLayout) findViewById("anti_tip_container");
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(UnitUtils.dpToPx(getContext(),12));
        gradientDrawable.setColor(Color.parseColor("#ffffff"));
        ll_container.setBackground(gradientDrawable);

        btn_sure = (Button) findViewById("bt_anti_tip");
        tv_content = (TextView) findViewById("tv_anti_tip");
        tv_content.setText(this.content);
        btn_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if( id == Res.id(getContext(),"bt_anti_tip")){
            dismiss();
            System.exit(0);
        }
    }

    public static void showAntiTip(final String content){
        LaiyouxiSdk.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AntiDialog(LaiyouxiSdk.getActivity(), content).show();
            }
        });
    }
}

