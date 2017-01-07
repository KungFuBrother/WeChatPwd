package com.smartown.passwordview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Author:Smartown
 * Date:2017/1/7 20:47
 * Description:
 */
public class PasswordActivity extends FragmentActivity {

    public static void input(Activity activity, int requestCode, int resultCode) {
        Intent intent = new Intent(activity, PasswordActivity.class);
        intent.putExtra("resultCode", resultCode);
        activity.startActivityForResult(intent, requestCode);
    }

    private int resultCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultCode = getIntent().getIntExtra("resultCode", 1);
        setContentView(R.layout.activity_password);
        final PasswordKeyboardView keyboardView = (PasswordKeyboardView) findViewById(R.id.password_input);
        PasswordView passwordView = (PasswordView) findViewById(R.id.password_view);
        keyboardView.setPasswordView(passwordView);
        passwordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardView.show();
            }
        });
        passwordView.setListener(new PasswordView.Listener() {
            @Override
            public void onValueChanged(String value) {

            }

            @Override
            public void onComplete(String value) {
                Intent intent = new Intent();
                intent.putExtra("password", value);
                setResult(resultCode, intent);
                finish();
            }
        });
    }

}
