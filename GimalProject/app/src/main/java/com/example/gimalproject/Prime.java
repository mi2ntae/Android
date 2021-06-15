package com.example.gimalproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import androidx.annotation.Nullable;

public class Prime extends Activity {
    private TabHost tabHost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

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
    }
}
