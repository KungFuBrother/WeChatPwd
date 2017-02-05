package com.smartown.wechatpwd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.smartown.codeView.CodeView;
import com.smartown.codeView.KeyboardView;

/**
 * Author:<a href='https://github.com/kungfubrother'>https://github.com/kungfubrother</a>
 * Date:2017/1/7 20:47
 * Description:
 */
public class CodeActivity extends Activity {

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
        activity.startActivity(intent);
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
                // TODO: 2017/2/5  内容发生变化
            }

            @Override
            public void onComplete(String value) {
                // TODO: 2017/2/5 输入完成 
            }
        });
    }

}
