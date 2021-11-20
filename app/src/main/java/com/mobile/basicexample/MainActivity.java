package com.mobile.basicexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ListView 테스트");

        // movieList는 데이터, ListView는 어댑터 뷰
        final ArrayList<String> movieList = new ArrayList<>();
        ListView list = findViewById(R.id.listView1);

        // 어댑터는 데이터(movieList)를 어댑터 뷰(ListView)에 보여주는 중간 객체
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, movieList);

        // 어댑터 뷰에 어댑터 설정
        list.setAdapter(adapter);

        final EditText edtItem = findViewById(R.id.edtItem);
        Button btnAdd = findViewById(R.id.btnAdd);

        // 항목 추가
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieList.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        // 항목을 길게 누르면 리스트에서 삭제
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                movieList.remove(i);
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }
}