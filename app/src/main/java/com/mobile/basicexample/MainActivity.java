package com.mobile.basicexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button button1, button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("컨텍스트 메뉴");

        baseLayout = findViewById(R.id.baseLayout);
        button1 = findViewById(R.id.button1);
        registerForContextMenu(button1);
        button2 = findViewById(R.id.button2);
        registerForContextMenu(button2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //MenuInflater mInflater = getMenuInflater();

        // 버튼1을 길게 누르면 컨텍스트 메뉴가 뜰 수 있도록
        if(v == button1){
            menu.setHeaderTitle("배경색 변경");

            //mInflater.inflate(R.menu.menu1, menu);
            menu.add(0, 1, 0, "배경색 (빨강)");
            menu.add(0, 2, 0, "배경색 (초록)");
            menu.add(0, 3, 0, "배경색 (파랑)");
        }

        // 버튼2를 길게 누르면 컨텍스트 메뉴가 뜰 수 있도록
        if(v == button2){
            menu.setHeaderTitle("버튼 변경");

            //mInflater.inflate(R.menu.menu2, menu);
            menu.add(0, 4, 0, "버튼 45도 회전");
            menu.add(0, 5, 0, "버튼 2배 확대");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
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
                button2.setRotation(45);
                return true;
            //case R.id.subSize:
            case 5:
                button2.setScaleX(2);
                return true;
        }

        return false;
    }
}

