package com.example.a60171622_;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

public class Prime extends TabActivity {
    private TabHost tabHost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        this.tabHost = getTabHost();

        TabHost.TabSpec tabSpecFirst = this.tabHost.newTabSpec("진료 예약하기").setIndicator("진료 예약하기");
        tabSpecFirst.setContent(R.id.prime_calender);
        this.tabHost.addTab(tabSpecFirst);

        TabHost.TabSpec tabSpecSecond = this.tabHost.newTabSpec("진료").setIndicator("다른거");
        tabSpecSecond.setContent(R.id.signup_et_numid);
        this.tabHost.addTab(tabSpecSecond);

    }
}