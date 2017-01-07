package com.smartown.wechatpwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.smartown.passwordview.PasswordActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PasswordActivity.input(this, 1, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            if (data != null) {
                final String pwd = data.getStringExtra("password");
                ((TextView) findViewById(R.id.text)).setText("你输入的密码是:" + pwd);
            }
        }
    }
}
