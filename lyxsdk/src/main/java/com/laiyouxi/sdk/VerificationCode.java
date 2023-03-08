package com.laiyouxi.sdk;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author: wxianing
 * @CreateDate: 2019/10/8 10:35
 */
public class VerificationCode extends RelativeLayout {

    private Context context;
    private TextView tv_code1;
    private TextView tv_code2;
    private TextView tv_code3;
    private TextView tv_code4;
    private TextView tv_code5;
    private TextView tv_code6;

    private EditText et_code;
    private List<String> codes = new ArrayList<>();
    private InputMethodManager imm;

    public VerificationCode(Context context) {
        super(context);
        this.context = context;
        loadView();
    }

    public VerificationCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadView();
    }

    private void loadView() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.verification_code, this);
        initView(view);
        initEvent();

        showSoftInput();
    }

    private void initView(View view) {
        tv_code1 = (TextView) view.findViewById(R.id.tv_code1);
        tv_code2 = (TextView) view.findViewById(R.id.tv_code2);
        tv_code3 = (TextView) view.findViewById(R.id.tv_code3);
        tv_code4 = (TextView) view.findViewById(R.id.tv_code4);
        tv_code5 = (TextView) view.findViewById(R.id.tv_code5);
        tv_code6 = (TextView) view.findViewById(R.id.tv_code6);
        et_code = (EditText) view.findViewById(R.id.et_code);
    }

    private void initEvent() {
        //验证码输入
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    et_code.setText("");
                    if (codes.size() < 6) {
                        codes.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        et_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        String code5 = "";
        String code6 = "";
        if (codes.size() >= 1) {
            code1 = codes.get(0);
        }
        if (codes.size() >= 2) {
            code2 = codes.get(1);
        }
        if (codes.size() >= 3) {
            code3 = codes.get(2);
        }
        if (codes.size() >= 4) {
            code4 = codes.get(3);
        }
        if (codes.size() >= 5) {
            code5 = codes.get(4);
        }
        if (codes.size() >= 6) {
            code6 = codes.get(5);
        }
        tv_code1.setText(code1);
        tv_code2.setText(code2);
        tv_code3.setText(code3);
        tv_code4.setText(code4);
        tv_code5.setText(code5);
        tv_code6.setText(code6);

        setColor();//设置高亮颜色
        callBack();//回调
    }

    /**
     * 设置高亮颜色
     */
    private void setColor() {

//        tv_code1.setBackgroundResource(R.drawable.input_gray_bg);
//        tv_code2.setBackgroundResource(R.drawable.input_gray_bg);
//        tv_code3.setBackgroundResource(R.drawable.input_gray_bg);
//        tv_code4.setBackgroundResource(R.drawable.input_gray_bg);
//        tv_code5.setBackgroundResource(R.drawable.input_gray_bg);
//        tv_code6.setBackgroundResource(R.drawable.input_gray_bg);

        if (codes.size() == 0) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code3.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code4.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code5.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code6.setBackgroundResource(R.drawable.input_gray_bg);
        }
        if (codes.size() == 1) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code3.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code4.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code5.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code6.setBackgroundResource(R.drawable.input_gray_bg);
        }
        if (codes.size() == 2) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code3.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code4.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code5.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code6.setBackgroundResource(R.drawable.input_gray_bg);
        }
        if (codes.size() == 3) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code3.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code4.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code5.setBackgroundResource(R.drawable.input_gray_bg);
            tv_code6.setBackgroundResource(R.drawable.input_gray_bg);
        }
        if (codes.size() == 4) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code3.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code4.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code5.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code6.setBackgroundResource(R.drawable.input_gray_bg);
        }
        if (codes.size() >= 5) {
            tv_code1.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code2.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code3.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code4.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code5.setBackgroundResource(R.drawable.input_blue_bg);
            tv_code6.setBackgroundResource(R.drawable.input_blue_bg);
        }
    }

    /**
     * 回调
     */
    private void callBack() {
        if (onInputListener == null) {
            return;
        }
        if (codes.size() == 6) {
            onInputListener.onFinish(getPhoneCode());
        }
    }

    //定义回调
    public interface OnInputListener {
        //输入完成监听
        void onFinish(String code);
    }

    private OnInputListener onInputListener;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && et_code != null) {
            et_code.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(et_code, 0);
                }
            }, 200);
        }
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }
}
