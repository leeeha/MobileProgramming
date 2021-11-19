package com.mobile.basicexample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("파일 입출력");

        Button btnRead, btnWrite;
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);

        // 파일에 쓰기
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput("file.txt",
                            Context.MODE_APPEND);

                    // file.txt 파일에 문자열 작성 후 저장
                    String str = "쿡북 안드로이드";
                    outFs.write(str.getBytes(StandardCharsets.UTF_8));
                    outFs.close();

                    Toast.makeText(getApplicationContext(), "file.txt가 생성됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // 파일 읽기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte[] txt = new byte[30];

                    // byte 타입으로 파일 내용 읽어오기
                    inFs.read(txt);

                    // byte 타입의 인자를 받아서 String 객체 생성
                    String str = new String(txt);

                    // 파일에서 읽어온 문자열을 토스트 메시지에 띄우기
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                    inFs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "읽을 파일이 없음", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

