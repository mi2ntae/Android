package com.example.gimalproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp extends Activity {
    private View.OnClickListener onClickListener;

    private Button btn_check, btn_back;
    private EditText et_id, et_pwd, et_name, et_phone;

    private Account account;
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
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_check) {
                account = new Account();
                account.setId(et_id.getText().toString());
                account.setPwd(et_pwd.getText().toString());
                account.setName(et_name.getText().toString());
                account.setPhone(et_phone.getText().toString());

                if (account.getId().equals("") || account.getPwd().equals("")){
                    if (account.getName().equals("") || account.getPhone().equals("")){
                        Toast.makeText(getApplicationContext(), "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                String saveAccount = account.getId()+" "+account.getPwd()+" "+account.getName()+" "+account.getPhone()+"\n";
                try{
                    File infile = new File(getFilesDir().getAbsolutePath()+"/file.txt");
                    FileReader inreader = new FileReader(infile);
                    BufferedReader bf = new BufferedReader(inreader);
                    String inner = "";
                    while (true) {
                        String k = bf.readLine();
                        if (k == null){
                            break;
                        }
                        inner += k;
                    }
                    bf.close();
                    File file = new File(getFilesDir().getAbsolutePath()+"/file.txt");
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.append(saveAccount+inner);
                    bufferedWriter.close();

                    File file2 = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"self.txt");
                    FileWriter fileWriter2 = new FileWriter(file2);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    bufferedWriter2.append("0 0 0\n");
                    bufferedWriter2.close();

                    File file3 = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
                    FileWriter fileWriter3 = new FileWriter(file3);
                    BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
                    bufferedWriter.close();

                    Toast.makeText(getApplicationContext(), saveAccount+"가입 성공", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "회원가입 파일 경로 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
            } else if (view == btn_back){
                finish();
            }
        }
    }
}
