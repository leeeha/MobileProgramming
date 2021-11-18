package com.mobile.basicexample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RelativeLayout baseLayout;
    EditText editText;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("옵션 메뉴에 따라 이미지 바꾸기");

        baseLayout = findViewById(R.id.baseLayout);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // 인플레이터로 menu1.xml 파일을 메모리에 객체화 시킨다.
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.rotateMenu:
                String angle = editText.getText().toString();
                imageView.setRotation(Integer.parseInt(angle));
                return true;
            case R.id.item1:
                imageView.setImageResource(R.drawable.api70);
                return true;
            case R.id.item2:
                imageView.setImageResource(R.drawable.api80);
                return true;
            case R.id.item3:
                imageView.setImageResource(R.drawable.api90);
                return true;
        }

        return false;
    }
}

