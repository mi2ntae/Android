package com.example.gimalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Prime extends Activity {
    private View.OnClickListener onClickListener;

    private TextView label;
    private TabHost tabHost;
    private Button btn_mypage, btn_youself;

    private Account account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        this.onClickListener = new OnClickListener();

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        this.label = findViewById(R.id.prime_text_label);
        this.label.setText("환영합니다 "+account.getName()+"고객님");

        this.tabHost = findViewById(R.id.tabhost);
        this.tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1") ;
        tab1.setContent(R.id.tab1) ;
        tab1.setIndicator("진료 예약하기") ;
        tabHost.addTab(tab1) ;

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2") ;
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("진료 확인") ;
        tabHost.addTab(tab2) ;

        this.btn_mypage = findViewById(R.id.prime_btn_mypage);
        this.btn_mypage.setOnClickListener(this.onClickListener);
        this.btn_youself = findViewById(R.id.prime_btn_yourself);
        this.btn_youself.setOnClickListener(this.onClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            this.account = (Account) data.getSerializableExtra("account");
            this.label.setText("환영합니다 "+account.getName()+"고객님");
        } else if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "로그아웃합니다", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_mypage){
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("account", account);
                startActivityForResult(intent, 0);
            } else if (view == btn_youself){

            }
        }
    }
}
