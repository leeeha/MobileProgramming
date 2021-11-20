package com.mobile.basicexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");

        final int voteCount[] = new int[5];
        for (int i = 0; i < 5; i++) {
            voteCount[i] = 0;
        }

        ImageView image[] = new ImageView[5];
        Integer imageId[] = { R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5 };
        final String imgName[] = {
                "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르앙", "잠 자는 소녀"
        };

        // 이미지 클릭할 때마다 투표 수 토스트 메시지로 보여주기
        for (int i = 0; i < imageId.length; i++) {
            final int index;
            index = i;
            image[index] = findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(),
                            imgName[index] + ": 총 " + voteCount[index] + "표",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnFinish = findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);

                startActivity(intent);
            }
        });

    }

}

