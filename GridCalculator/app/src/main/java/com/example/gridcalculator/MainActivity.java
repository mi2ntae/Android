package com.example.gridcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Integer[] btnIds= {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private Button[] btns = new Button[10];
    private Button btnPlus, btnMinus, btnMultiply, btnDivide;
    private EditText num1Text, num2Text;
    private TextView resultText;

    private String num1, num2;
    private int result;

    private OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.onClickListener = new OnClickListener();

        for (int i = 0; i < btnIds.length; i++) {
            final String index = Integer.toString(i);

            btns[i] = findViewById(btnIds[i]);
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (num1Text.isFocused()) {
                        num1 = num1Text.getText().toString()+index;
                        num1Text.setText(num1);
                    } else if (num2Text.isFocused()) {
                        num2 = num2Text.getText().toString()+index;
                        num2Text.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this.onClickListener);
        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this.onClickListener);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(this.onClickListener);
        btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(this.onClickListener);

        num1Text = findViewById(R.id.num1);
        num2Text = findViewById(R.id.num2);

        resultText = findViewById(R.id.result);
    }
    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            num1 = num1Text.getText().toString();
            num2 = num2Text.getText().toString();
            if (view.getId() == btnPlus.getId()) {
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
            } else if (view.getId() == btnMinus.getId()) {
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
            } else if (view.getId() == btnMultiply.getId()) {
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
            } else if (view.getId() == btnDivide.getId()) {
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
            }
            resultText.setText("계산결과 : "+Integer.toString(result));
        }
    }
}
