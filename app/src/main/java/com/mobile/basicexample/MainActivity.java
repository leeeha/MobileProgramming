package com.mobile.basicexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액티비티 전환하기");

        RadioButton rdoSecond = findViewById(R.id.rdoSecond);
        RadioButton rdoThird = findViewById(R.id.rdoThird);
        Button btnNewActivity = findViewById(R.id.btnNewActivity);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdoSecond.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                }
                else if(rdoThird.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    startActivity(intent);
                }
                else{ // 아무것도 선택하지 않은 경우
                    Toast.makeText(getApplicationContext(),
                            "액티비티를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

