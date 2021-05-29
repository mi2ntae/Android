package com.example.fourbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button nateBtn, emergeBtn, galleryBtn, endBtn;
    private OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_name);

        onClickListener = new OnClickListener();

        // Associate View
        nateBtn = (Button)findViewById(R.id.nateBtn);
        emergeBtn = (Button)findViewById(R.id.emergeBtn);
        galleryBtn = (Button)findViewById(R.id.galleryBtn);
        endBtn = (Button)findViewById(R.id.endBtn);

        // Set View Callback
        nateBtn.setOnClickListener(onClickListener);
        emergeBtn.setOnClickListener(onClickListener);
        galleryBtn.setOnClickListener(onClickListener);
        endBtn.setOnClickListener(onClickListener);
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == nateBtn.getId()) {
                Intent nIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(nIntent);
            }
            if (view.getId() == emergeBtn.getId()) {
                Intent eIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(eIntent);
            }
            if (view.getId() == galleryBtn.getId()) {
                Intent gIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(gIntent);
            }
            if (view.getId() == endBtn.getId()) {
                finish();
            }
        }
    }
}