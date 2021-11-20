package com.mobile.basicexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표 결과");

        // 인텐트를 통해 메인 액티비티로부터 이미지 이름과 투표 결과 받아오기
        Intent intent = getIntent();
        String[] imageName = intent.getStringArrayExtra("ImageName");
        int[] voteResult = intent.getIntArrayExtra("VoteCount");

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[voteResult.length];

        Integer tvID[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5 };
        Integer rbarID[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5 };

        // XML 레이아웃에 선언된 뷰와 자바 코드 내의 뷰 객체 연결하기,,, 매우 번거롭다.
        // 아이디를 잘못 입력하면 NPE 발생할 수도 있다.
        // 이런 번거로움을 줄이려면 코틀린을 써야 한다! (apply plugin: 'kotlin-android-extensions')
        for (int i = 0; i < voteResult.length; i++) {
            tv[i] = findViewById(tvID[i]);
            rbar[i] = findViewById(rbarID[i]);
        }

        // 메인 액티비티에서 넘겨 받은 정보로 텍스트 뷰와 레이팅바 초기화
        for (int i = 0; i < voteResult.length; i++) {
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float)voteResult[i]);
        }

        // 메인 화면으로 돌아가기
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
