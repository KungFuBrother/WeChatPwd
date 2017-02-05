package com.smartown.wechatpwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Author:<a href='https://github.com/kungfubrother'>https://github.com/kungfubrother</a>
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_wechat).setOnClickListener(this);
        findViewById(R.id.main_sms).setOnClickListener(this);
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
        }
    }
}
