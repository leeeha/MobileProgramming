package com.mobile.basicexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
    1. 버튼을 클릭하면, 메인 화면에서 입력한 텍스트가 대화상자에 뜨도록!
    2. 대화 상자에 확인 버튼을 클릭하면, 대화상자에서 입력한 텍스트가 메인 화면에 뜨도록!
    3. 취소를 클릭하면 토스트 메시지가 메인 화면의 임의적인 위치에 뜨도록!
 */

public class MainActivity extends AppCompatActivity {
    EditText mainEdtName, mainEdtEmail;
    Button button1;
    EditText dlgEdtName, dlgEdtEmail;
    TextView toastText1;
    View dialogView, toastView; // XML 인플레이트의 결과를 저장하는 변수

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력 (수정)");

        mainEdtName = findViewById(R.id.mainEdtName);
        mainEdtEmail = findViewById(R.id.mainEdtEmail);
        button1 = findViewById(R.id.button1);

        // 버튼을 클릭하면, 메인 화면에서 입력한 텍스트가 대화상자에 뜨도록
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog1.xml 파일을 dialogView 변수에 인플레이트 (메모리에 객체화) 시킨다.
                dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);

                // 메인 액티비티에 대화상자 빌더 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                // 대화상자의 제목, 아이콘, 뷰 설정
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_launcher_foreground);
                dlg.setView(dialogView);

                // 대화상자의 EditText 객체 생성
                dlgEdtName = dialogView.findViewById(R.id.dlgEdt1);
                dlgEdtEmail = dialogView.findViewById(R.id.dlgEdt2);

                // 메인 화면에서 입력한 텍스트가 대화상자에 뜨도록
                dlgEdtName.setText(mainEdtName.getText().toString());
                dlgEdtEmail.setText(mainEdtEmail.getText().toString());

                // 확인 버튼을 누르면, 대화상자에서 입력한 텍스트가 메인 화면에 뜨도록
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainEdtName.setText(dlgEdtName.getText().toString());
                        mainEdtEmail.setText(dlgEdtEmail.getText().toString());
                    }
                });

                // 취소 버튼을 누르면, 토스트 메시지가 임의적인 위치에 뜨도록
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);

                        // toast1.xml 파일을 toastView 변수에 인플레이트 (메모리에 객체화) 시킨다.
                        toastView = View.inflate(MainActivity.this, R.layout.toast1, null);

                        // 토스트 메시지의 텍스트 뷰 변경하기
                        toastText1 = toastView.findViewById(R.id.toastText1);
                        toastText1.setText("취소했습니다");

                        // 인플레이트 시킨 toast1.xml로 뷰 설정
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        // 임의적인 위치에 토스트 메시지 띄우기
                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset = (int) (Math.random() * display.getWidth());
                        int yOffset = (int) (Math.random() * display.getHeight());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);

                        toast.show();
                    }
                });

                // 다이얼로그 띄우기
                dlg.show();
            }
        });
    }
}

