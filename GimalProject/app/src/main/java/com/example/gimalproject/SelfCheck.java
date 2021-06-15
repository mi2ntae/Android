package com.example.gimalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SelfCheck extends Activity {
    private View.OnClickListener onClickListener;

    private RadioGroup rGroup1, rGroup2, rGroup3;
    private Button btn_save, btn_back;
    private RadioButton rbtn_1, rbtn_2, rbtn_3, rbtn_4, rbtn_5, rbtn_6;

    private Account account;
    private String[] abc = {"0", "0", "0"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfcheck);

        Intent intent = getIntent();
        this.account = (Account) intent.getSerializableExtra("account");

        this.onClickListener = new OnClickListener();

        try {
            File file = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"self.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String a = bufferedReader.readLine();
            abc = a.split(" ");
        } catch (IOException e){
            Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();
        }
        this.rGroup1 = findViewById(R.id.selfcheck_rgroup1);
        this.rGroup2 = findViewById(R.id.selfcheck_rgroup2);
        this.rGroup3 = findViewById(R.id.selfcheck_rgroup3);
        this.btn_save = findViewById(R.id.selfcheck_btn_save);
        this.btn_save.setOnClickListener(this.onClickListener);
        this.btn_back = findViewById(R.id.selfcheck_btn_back);
        this.btn_back.setOnClickListener(this.onClickListener);
        this.rbtn_1 = findViewById(R.id.selfcheck_rbtn_1);
        this.rbtn_2 = findViewById(R.id.selfcheck_rbtn_2);
        this.rbtn_3 = findViewById(R.id.selfcheck_rbtn_3);
        this.rbtn_4 = findViewById(R.id.selfcheck_rbtn_4);
        this.rbtn_5 = findViewById(R.id.selfcheck_rbtn_5);
        this.rbtn_6 = findViewById(R.id.selfcheck_rbtn_6);

        if (abc[0].equals("0")) {
            this.rGroup1.check(rbtn_2.getId());
        } else {
            this.rGroup1.check(rbtn_1.getId());        }
        if (abc[1].equals("0")) {
            this.rGroup2.check(rbtn_4.getId());
        } else {
            this.rGroup2.check(rbtn_3.getId());        }
        if (abc[2].equals("0")) {
            this.rGroup3.check(rbtn_6.getId());
        } else {
            this.rGroup3.check(rbtn_5.getId());
        }
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_back){
                finish();
            } else if (view == btn_save){

            }
        }
    }
}
