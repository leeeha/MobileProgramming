package com.mobile.basicexample;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    MyPictureView myPicture;
    int curNum;
    TextView textView;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        myPicture = findViewById(R.id.myPictureView1);
        textView = findViewById(R.id.textView);

        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures").listFiles();

        imageFname = imageFiles[0].toString();
        myPicture.imagePath = imageFname;

        // 이전 그림 보여주기
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum <= 0){
                    textView.setText((curNum + 1) +" / " + imageFiles.length);

                    Toast.makeText(getApplicationContext(),
                            "첫번째 그림입니다", Toast.LENGTH_SHORT).show();
                }else{
                    curNum--;
                    textView.setText((curNum + 1) + " / " + imageFiles.length);

                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath = imageFname;
                    myPicture.invalidate(); // 화면 갱신
                }
            }
        });

        // 다음 그림 보여주기
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum >= imageFiles.length - 1){
                    textView.setText((curNum + 1) + " / " + imageFiles.length);

                    Toast.makeText(getApplicationContext(), "마지막 그림입니다",
                            Toast.LENGTH_SHORT).show();
                }else{
                    curNum++;
                    textView.setText((curNum + 1) + " / " + imageFiles.length);

                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath = imageFname;
                    myPicture.invalidate();
                }
            }
        });

    }

}

