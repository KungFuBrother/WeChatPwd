package com.smartown.wechatpwd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;

import com.smartown.codeView.CodeView;
import com.smartown.codeView.KeyboardView;

/**
 * Author:Smartown
 * Date:2017/1/7 20:47
 * Description:
 */
public class CodeActivity extends FragmentActivity {

    public final static int REQUEST_CODE = 0x10;
    public final static int RESULT_CODE = 0x11;

    public static void inputSmsCode(Activity activity, int length) {
        input(activity, CodeView.SHOW_TYPE_WORD, length);
    }

    public static void inputPassword(Activity activity, int length) {
        input(activity, CodeView.SHOW_TYPE_PASSWORD, length);
    }

    public static void input(Activity activity, int showType, int length) {
        Intent intent = new Intent(activity, CodeActivity.class);
        intent.putExtra("showType", showType);
        intent.putExtra("length", length);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static String getResult(Intent intent) {
        String result = "";
        if (intent != null) {
            result = intent.getStringExtra("result");
        }
        return TextUtils.isEmpty(result) ? "" : result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        final KeyboardView keyboardView = (KeyboardView) findViewById(R.id.password_input);
        CodeView codeView = (CodeView) findViewById(R.id.password_view);
        codeView.setShowType(getIntent().getIntExtra("showType", CodeView.SHOW_TYPE_WORD));
        codeView.setLength(getIntent().getIntExtra("length", 6));
        keyboardView.setCodeView(codeView);
        codeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardView.show();
            }
        });
        codeView.setListener(new CodeView.Listener() {
            @Override
            public void onValueChanged(String value) {

            }

            @Override
            public void onComplete(String value) {
                Intent intent = new Intent();
                intent.putExtra("result", value);
                setResult(RESULT_CODE, intent);
                finish();
            }
        });
    }

}
