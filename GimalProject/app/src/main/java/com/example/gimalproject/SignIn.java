package com.example.gimalproject;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SignIn extends Activity {
    private View.OnClickListener onClickListener;

    private Button btn_check, btn_back;
    private EditText et_id, et_pwd;

    private ArrayList<Account> accountList;
    private Account loginAccount;

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

        this.accountList = new ArrayList<Account>();
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_check){
                try{
                    File file = new File(getFilesDir().getAbsolutePath()+"/file.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String accountData = "";

                    while (true){
                        accountData = bufferedReader.readLine();

                        if (accountData == null){
                            break;
                        }
                        String[] a = accountData.split(" ");
                        Account account = new Account();
                        account.setId(a[0]);
                        account.setPwd(a[1]);
                        account.setName(a[2]);
                        account.setPhone(a[3]);
                        accountList.add(account);
                    }
                    boolean flag = false;
                    for (Account account: accountList){
                        if (account.getId().equals(et_id.getText().toString()) && account.getPwd().equals(et_pwd.getText().toString())){
                            loginAccount = account;
                            flag = true;
                        }
                    }
                    if (flag){
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Prime.class);
                        intent.putExtra("account", loginAccount);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();

                }
//                Intent intent = new Intent(getApplicationContext(), Prime.class);
//                startActivity(intent);
            } else if (view == btn_back){
                finish();
            }
        }
    }
}
