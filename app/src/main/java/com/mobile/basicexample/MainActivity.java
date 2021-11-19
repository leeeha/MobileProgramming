package com.mobile.basicexample;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

/*  Error
    com.google.android.providers.media.module E/MediaProvider:
    Creating a non-default top level directory or deleting an existing one is not allowed!

    https://stackoverflow.com/questions/58379543/cant-create-directory-in-android-10

    에뮬레이터 targeting 버전을 Android 10으로 낮추고
    android:requestLegacyExternalStorage="true" 이걸 manifest 파일에 써주면 디렉토리 생성까지는 된다.
    어쨌든 버전이 바뀌면서 정상적으로 실행되지 않는 코드들이 많아진다는 걸 느꼈다...
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SD 카드에 폴더 및 파일 생성하기");

        // 외부 저장소에 쓰기 권한 부여
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE }, MODE_PRIVATE);

        Button btnMkdir = findViewById(R.id.btnMkdir);
        Button btnRmdir = findViewById(R.id.btnRmdir);

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath +"/mydir");

        // sd 카드에 디렉토리 생성
        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.mkdir();
            }
        });

        // sd 카드에서 디렉토리 삭제
        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.delete();
            }
        });

    }

}

