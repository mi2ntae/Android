package com.example.gimalproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SignUp extends Activity {
    private View.OnClickListener onClickListener;

    private Button btn_check, btn_back;
    private EditText et_id, et_pwd, et_name, et_phone, et_numid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.onClickListener = new OnClickListener();

        this.btn_check = findViewById(R.id.signup_btn_check);
        this.btn_check.setOnClickListener(this.onClickListener);
        this.btn_back = findViewById(R.id.signup_btn_back);
        this.btn_back.setOnClickListener(this.onClickListener);

        this.et_id = findViewById(R.id.signup_et_id);
        this.et_pwd = findViewById(R.id.signup_et_pwd);
        this.et_name = findViewById(R.id.signup_et_name);
        this.et_phone = findViewById(R.id.signup_et_phone);
        this.et_numid = findViewById(R.id.signup_et_numid);
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_check){
                finish();
            } else if (view == btn_back){
                finish();
            }
        }
    }
}
