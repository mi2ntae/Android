package com.example.gimalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyPage extends Activity {
    private View.OnClickListener onClickListener;

    private Button btn_logout, btn_modifyPWd, btn_save, btn_back;
    private EditText et_name, et_phone, et_pwd;

    private Account account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        this.onClickListener = new OnClickListener();

        Intent intent = getIntent();
        this.account = (Account) intent.getSerializableExtra("account");

        this.btn_logout = findViewById(R.id.mypage_btn_logout);
        this.btn_logout.setOnClickListener(this.onClickListener);
        this.btn_modifyPWd = findViewById(R.id.mypage_btn_modifyPwd);
        this.btn_modifyPWd.setOnClickListener(this.onClickListener);
        this.btn_save = findViewById(R.id.mypage_btn_save);
        this.btn_save.setOnClickListener(this.onClickListener);
        this.btn_back = findViewById(R.id.mypage_btn_back);
        this.btn_back.setOnClickListener(this.onClickListener);

        this.et_name = findViewById(R.id.mypage_et_name);
        this.et_name.setText(account.getName());
        this.et_phone = findViewById(R.id.mypage_et_phone);
        this.et_phone.setText(account.getPhone());
        this.et_pwd = findViewById(R.id.mypage_et_pwd);
        this.et_pwd.setText(account.getPwd());
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_logout) {
                finish();
            } else if (view == btn_back) {
                Intent outIntent = new Intent(getApplicationContext(), Prime.class);
                outIntent.putExtra("logout", 0);
                setResult(RESULT_FIRST_USER, outIntent);
                finish();
            } else if (view == btn_save) {
                String fileAccounts = "";
                try{
                    File file = new File(getFilesDir().getAbsolutePath()+"/file.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String accountData = "";
                    while (true) {
                        accountData = bufferedReader.readLine();
                        if (accountData == null){
                            break;
                        }
                        String[] a = accountData.split(" ");
                        if (a[0].equals(account.getId()) && a[1].equals(account.getPwd())) {

                        } else {
                            fileAccounts += accountData;
                        }
                    }
                    account.setName(et_name.getText().toString());
                    account.setPhone(et_phone.getText().toString());
                    account.setPwd(et_pwd.getText().toString());
                    fileAccounts += account.getId()+" "+account.getPwd()+" "+account.getName()+ " "+account.getPhone()+"\n";
                    file.delete();
                    File file2 = new File(getFilesDir().getAbsolutePath()+"/file.txt");
                    FileWriter fileWriter = new FileWriter(file2);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.append(fileAccounts);
                    bufferedWriter.close();
                } catch (IOException e){
                    Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "정보를 수정하였습니다", Toast.LENGTH_SHORT).show();
                Intent outIntent = new Intent(getApplicationContext(), Prime.class);
                outIntent.putExtra("account", account);
                setResult(RESULT_OK, outIntent);
                finish();
            } else if (view == btn_modifyPWd) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MyPage.this);
                dlg.setMessage("비밀번호를 변경하시겠습니까?");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_pwd.setFocusableInTouchMode(true);
                    }
                });

                dlg.show();
            }
        }
    }
}
