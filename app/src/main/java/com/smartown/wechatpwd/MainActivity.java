package com.smartown.wechatpwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.smartown.passwordview.PasswordKeyboardView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView editText;
    private PasswordKeyboardView keyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.main_pwd_edit);
        keyboardView = (PasswordKeyboardView) findViewById(R.id.main_pwd_input);
        editText.setOnClickListener(this);
        findViewById(R.id.main_wechat).setOnClickListener(this);
        keyboardView.setListener(new PasswordKeyboardView.Listener() {
            @Override
            public void onInput(String s) {
                editText.append(s);
            }

            @Override
            public void onDelete() {
                int length = editText.length();
                if (length > 0) {
                    editText.getEditableText().delete(length - 1, length);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            if (data != null) {
                final String pwd = data.getStringExtra("password");
                editText.setText(pwd);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_wechat:
                PasswordActivity.input(this, 1, 2);
                break;
            case R.id.main_pwd_edit:
                keyboardView.show();
                break;
        }
    }
}
