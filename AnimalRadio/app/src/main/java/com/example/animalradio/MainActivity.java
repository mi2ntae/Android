package com.example.animalradio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rGroup;
    private Button btnShow;
    private View dialogView;
    private EditText editText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rGroup = findViewById(R.id.rGroup);
        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = getLayoutInflater().inflate(R.layout.dialog, null);
                editText = dialogView.findViewById(R.id.editTextTextPersonName);
                imageView = dialogView.findViewById(R.id.imageView);
                switch (rGroup.getCheckedRadioButtonId()){
                    case R.id.rBtnDog:
                        editText.setText("강아지");
                        imageView.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rBtnCat:
                        editText.setText("고양이");
                        imageView.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rBtnRabbit:
                        editText.setText("토끼");
                        imageView.setImageResource(R.drawable.rabbit);
                        break;
                    case R.id.rBtnHorse:
                        editText.setText("말");
                        imageView.setImageResource(R.drawable.horse);
                        break;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                builder.setNegativeButton("닫기", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}