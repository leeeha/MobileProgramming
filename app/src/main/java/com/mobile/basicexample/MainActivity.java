package com.mobile.basicexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
    '어댑터'는 데이터 소스(배열, 연결리스트, DB 등)를
    어댑터 뷰(ListView, GridView, Gallery, Spinner 등)에 보여주는
    일종의 다리 역할을 하는 객체! (데이터와 어댑터 뷰 사이의 bridge)
    ex) ArrayAdapter, CursorAdapter, SimpleAdapter 등
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ListView 테스트");

        // String 배열은 리스트 뷰에 보여줄 데이터
        final String[] movie = {"히어로즈", "24시", "로스트", "스몰빌", "빅뱅이론",
                "히어로즈", "24시", "로스트", "스몰빌", "빅뱅이론",
                "히어로즈", "24시", "로스트", "스몰빌", "빅뱅이론"};

        // ListView는 어댑터 뷰
        ListView list = findViewById(R.id.listView1);

        // 어댑터는 데이터(String 배열)를 어댑터 뷰(리스트 뷰)에 보여주는 중간 객체
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, movie);

        // 라디오 버튼
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_single_choice, movie);
//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 체크 박스
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
               android.R.layout.simple_list_item_multiple_choice, movie);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // 어댑터 뷰에 어댑터 설정
        list.setAdapter(adapter);

        // 리스트 뷰의 항목을 클릭하면 토스트 메시지 띄우기
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), movie[i],
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}