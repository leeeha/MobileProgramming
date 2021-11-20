package com.mobile.basicexample;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*  액티비티의 생명 주기

    onCreate() 액티비티 초기화 작업

    onStart() 사용자가 액티비티를 볼 수 있는 상태

    onResume() 액티비티가 실제 사용자와 상호작용이 가능한 포그라운드에 위치한 상태

    [액티비티 실행 중]

    onPause() 사용자와 상호작용이 불가능한, 포커스를 잃은 상태 <-> onResume()

    onStop() 액티비티가 사용자에게 더 이상 보이지 않는 상태 <-> onRestart() -> onStart()
           cf) 새로 호출되는 액티비티의 스타일을 translucent(투명)으로 설정하면,
               기존 액티비티도 눈에 보이기 때문에 onPause()까지만 호출됨.
               하지만, 일반적으로 새로 뜬 화면 때문에 기존 화면이 보이지 않으면
               onPause(), onStop()이 같이 호출됨.

    onDestroy() 액티비티 또는 앱 프로세스 자체가 종료된 상태

    cf) 다이얼로그는 새로운 액티비티를 띄우는 게 아니라, 단지 액티비티의 일부이기 때문에
        생명주기 함수가 호출되지 않는다.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액티비티 생명주기");

        android.util.Log.i("액티비티 테스트", "onCreate()");

        Button btnDial = findViewById(R.id.btnDial);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 암시적 인텐트
                Uri uri = Uri.parse("tel: 010-1234-5678");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        Button btnNewActivity = findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 명시적 인텐트
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        Button btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 액티비티 종료 (onDestroy)
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.i("액티비티 테스트", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("액티비티 테스트", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.i("액티비티 테스트", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("액티비티 테스트", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i("액티비티 테스트", "onDestroy()");
    }
}

/* Logcat 확인 결과

    [메인 액티비티 시작]
    I/액티비티 테스트: onCreate()
    I/액티비티 테스트: onStart()
    I/액티비티 테스트: onResume()

    [메인 액티비티 실행 중]

    [다른 액티비티 요청]
    I/액티비티테스트: onPause() // 투명한 액티비티일 경우 onPause()까지만 호출됨.
    I/액티비티 테스트: onStop()

    [메인 액티비티로 돌아왔을 때]
    I/액티비티 테스트: onStart()
    I/액티비티 테스트: onResume() // 투명한 액티비티일 경우 onResume()만 호출됨.

    [메인 액티비티 종료]
    I/액티비티 테스트: onPause()
    I/액티비티 테스트: onStop()
    I/액티비티 테스트: onDestroy()

 */

