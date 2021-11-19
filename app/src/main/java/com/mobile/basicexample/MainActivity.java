package com.mobile.basicexample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = findViewById(R.id.datePicker1);
        edtDiary = findViewById(R.id.edtDiary);
        btnWrite = findViewById(R.id.btnWrite);

        // Calendar 클래스를 이용해 현재 날짜(연, 월, 일)로 DatePicker 초기화
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                // DatePicker에서 선택한 날짜에 따라 파일명을 "연_월_일.txt"로 설정
                fileName = year + "_" + (monthOfYear + 1) + "_"  + dayOfMonth + ".txt";

                // 해당하는 파일을 찾아서 EditText에 보여주기
                String str = readDiary(fileName);
                edtDiary.setText(str);

                btnWrite.setEnabled(true);
            }
        });

        // 버튼을 클릭하면, EditText에 입력한 내용을 파일에 저장
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes(StandardCharsets.UTF_8));
                    outFs.close();

                    Toast.makeText(getApplicationContext(), fileName + "이 저장됨",
                            Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private String readDiary(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;

        try{
            // 파일을 읽어서 byte 타입의 배열에 저장하기
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();

            // byte 타입의 배열로 String 객체 생성 후 공백 제거
            diaryStr = (new String(txt)).trim();

            btnWrite.setText("수정하기");

        } catch (IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }

        return diaryStr;
    }

}

