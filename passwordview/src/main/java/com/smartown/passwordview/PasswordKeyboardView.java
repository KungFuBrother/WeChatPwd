package com.smartown.passwordview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Author:Smartown
 * Date:2017/1/7 21:39
 * Description:
 */
public class PasswordKeyboardView extends FrameLayout implements View.OnClickListener {

    private PasswordView passwordView;

    public PasswordKeyboardView(Context context) {
        super(context);
        init();
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setPasswordView(PasswordView passwordView) {
        this.passwordView = passwordView;
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_pwd_keyboard, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(view);
        view.findViewById(R.id.keyboard_0).setOnClickListener(this);
        view.findViewById(R.id.keyboard_1).setOnClickListener(this);
        view.findViewById(R.id.keyboard_2).setOnClickListener(this);
        view.findViewById(R.id.keyboard_3).setOnClickListener(this);
        view.findViewById(R.id.keyboard_4).setOnClickListener(this);
        view.findViewById(R.id.keyboard_5).setOnClickListener(this);
        view.findViewById(R.id.keyboard_6).setOnClickListener(this);
        view.findViewById(R.id.keyboard_7).setOnClickListener(this);
        view.findViewById(R.id.keyboard_8).setOnClickListener(this);
        view.findViewById(R.id.keyboard_9).setOnClickListener(this);
        view.findViewById(R.id.keyboard_hide).setOnClickListener(this);
        view.findViewById(R.id.keyboard_delete).setOnClickListener(this);
    }

    public void hide() {
        setVisibility(INVISIBLE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (passwordView == null) {
            return;
        }
        final String tag = (String) v.getTag();
        if (tag != null) {
            switch (tag) {
                case "hide":
                    hide();
                    break;
                case "delete":
                    passwordView.delete();
                    break;
                default:
                    passwordView.input(tag);
                    break;
            }
        }
    }
}
