package com.example.gimalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener onClickListener;
    private Button btn_signin, btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.onClickListener = new OnClickListener();

        this.btn_signin = findViewById(R.id.home_btn_signin);
        this.btn_signin.setOnClickListener(this.onClickListener);
        this.btn_signup = findViewById(R.id.home_btn_signup);
        this.btn_signup.setOnClickListener(this.onClickListener);
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view == btn_signin) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            } else if (view == btn_signup) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        }
    }
}