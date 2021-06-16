package com.example.gimalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Prime extends Activity {
    private View.OnClickListener onClickListener;

    private TextView label;
    private TabHost tabHost;
    private Button btn_mypage, btn_youself;
    private CalendarView calendarView;
    private LinearLayout tab22;
    private View v;

    private Account account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        this.onClickListener = new OnClickListener();

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        this.tab22 = findViewById(R.id.tab2);

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

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equals("tab2")){
                    tab22.removeAllViewsInLayout();
                    tab22.setOrientation(LinearLayout.VERTICAL);
                    ArrayList<String> inner = new ArrayList<String>();
                    try {
                        File infile = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
                        FileReader inreader = new FileReader(infile);
                        BufferedReader bf = new BufferedReader(inreader);
                        while (true) {
                            String k = bf.readLine();
                            if (k == null){
                                break;
                            }
                            inner.add(k);
                        }
                        bf.close();
                    }catch (IOException e){
                        Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();
                    }
                    for (String date : inner){
                        TextView text = new TextView(getApplicationContext());
                        text.setText(date);
                        text.setTextSize(20);
                        text.setTextColor(Color.BLACK);
                        LinearLayout.LayoutParams a = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        a.gravity = Gravity.CENTER;
                        text.setLayoutParams(a);
                        registerForContextMenu(text);
                        tab22.addView(text);
                    }
                }
            }
        });

        this.btn_mypage = findViewById(R.id.prime_btn_mypage);
        this.btn_mypage.setOnClickListener(this.onClickListener);
        this.btn_youself = findViewById(R.id.prime_btn_yourself);
        this.btn_youself.setOnClickListener(this.onClickListener);

        this.calendarView = findViewById(R.id.prime_calendar);
        this.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Prime.this);
                dlg.setMessage("해당 날짜로 예약하시겠습니까?");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            File infile = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
                            FileReader inreader = new FileReader(infile);
                            BufferedReader bf = new BufferedReader(inreader);
                            String inner = "";
                            while (true) {
                                String k = bf.readLine();
                                if (k == null){
                                    break;
                                }
                                inner += k+"\n";
                            }
                            bf.close();

                            File file = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
                            FileWriter fileWriter = new FileWriter(file);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.append(Integer.toString(y)+"-"+Integer.toString(m+1)+"-"+Integer.toString(d)+"\n"+inner);
                            bufferedWriter.close();
                            Toast.makeText(getApplicationContext(), Integer.toString(y)+"-"+Integer.toString(m+1)+"-"+Integer.toString(d)+"로 예약하였습니다", Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.show();
            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        this.v = v;
        inflater.inflate(R.menu.menu_delete, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        this.tab22.removeView(v);
        try {
            File infile = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
            FileReader inreader = new FileReader(infile);
            BufferedReader bf = new BufferedReader(inreader);
            String inner = "";
            while (true) {
                String k = bf.readLine();
                if (k == null){
                    break;
                }
                if (!k.equals(((TextView)v).getText().toString())) {
                    inner += k;
                }
            }
            bf.close();

            File file = new File(getFilesDir().getAbsolutePath()+"/"+account.getId()+"resfile.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(inner);
            bufferedWriter.close();
        }catch (IOException e){
            Toast.makeText(getApplicationContext(), "파일을 못 찾겠어", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_mypage){
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                intent.putExtra("account", account);
                startActivityForResult(intent, 0);
            } else if (view == btn_youself){
                Intent intent = new Intent(getApplicationContext(), SelfCheck.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        }
    }
}
