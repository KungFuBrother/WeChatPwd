package com.smartown.wechatpwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.smartown.codeView.KeyboardView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView editText;
    private KeyboardView keyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.main_edit);
        keyboardView = (KeyboardView) findViewById(R.id.main_keyboard);
        editText.setOnClickListener(this);
        findViewById(R.id.main_wechat).setOnClickListener(this);
        findViewById(R.id.main_sms).setOnClickListener(this);
        findViewById(R.id.main_id_card).setOnClickListener(this);
        keyboardView.setListener(new KeyboardView.Listener() {
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
        editText.setText(CodeActivity.getResult(data));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_wechat:
                CodeActivity.inputPassword(this, 6);
                break;
            case R.id.main_sms:
                CodeActivity.inputSmsCode(this, 6);
                break;
            case R.id.main_id_card:
                CodeActivity.inputSmsCode(this, 18);
                break;
            case R.id.main_edit:
                keyboardView.show();
                break;
        }
    }
}
