package com.mobile.basicexample;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SD 카드에서 파일 읽기");

        Button btnRead = findViewById(R.id.btnRead);
        final EditText edtSD = findViewById(R.id.edtSD);

        // 외부 저장소에 쓰기 권한 부여
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE }, MODE_PRIVATE);

        // 버튼을 클릭하면 sd_test.txt 파일 읽어서 에디트텍스트에 보여주기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inFs = new FileInputStream("/sdcard/Ringtones/sd_test.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edtSD.setText(new String(txt));
                    inFs.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}

