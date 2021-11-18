package com.mobile.basicexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button button1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("옵션 메뉴로 배경색 바꾸기");

        baseLayout = findViewById(R.id.baseLayout);
        button1 = findViewById(R.id.button1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // 방법1: 인플레이터를 통해 menu1.xml 파일을 메모리에 객체화 시킨다.
        //MenuInflater mInflater = getMenuInflater();
        //mInflater.inflate(R.menu.menu1, menu);

        // 방법2: 직접 Menu.add() 메소드로 메뉴 항목 추가하기
        menu.add(0, 1, 0, "배경색 (빨강)");
        menu.add(0, 2, 0, "배경색 (초록)");
        menu.add(0, 3, 0, "배경색 (파랑)");

        SubMenu sMenu = menu.addSubMenu("버튼 변경 >> ");
        sMenu.add(0, 4, 0, "버튼 45도 회전");
        sMenu.add(0, 5, 0, "버튼 2배 확대");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            //case R.id.itemRed:
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            //case R.id.itemGreen:
            case 2:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            //case R.id.itemBlue:
            case 3:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            //case R.id.subRotate:
            case 4:
                button1.setRotation(45); // 45도 회전
                return true;
            //case R.id.subSize:
            case 5:
                button1.setScaleX(2); // 가로로 2배 확대
                return true;
        }

        return false;
    }
}

