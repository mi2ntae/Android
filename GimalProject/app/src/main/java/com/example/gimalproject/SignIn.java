package com.example.gimalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SignIn extends Activity {
    private View.OnClickListener onClickListener;

    private Button btn_check, btn_back;
    private EditText et_id, et_pwd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        this.onClickListener = new OnClickListener();

        this.btn_check = findViewById(R.id.signin_btn_check);
        this.btn_check.setOnClickListener(this.onClickListener);
        this.btn_back = findViewById(R.id.signin_btn_back);
        this.btn_back.setOnClickListener(this.onClickListener);
        this.et_id = findViewById(R.id.signin_et_id);
        this.et_pwd = findViewById(R.id.signin_et_pwd);
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_check){
                Intent intent = new Intent(getApplicationContext(), Prime.class);
                startActivity(intent);
            } else if (view == btn_back){
                finish();
            }
        }
    }
}
