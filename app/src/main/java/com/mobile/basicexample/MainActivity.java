package com.mobile.basicexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 사용하기");

        // 데이터를 리스트뷰에 보여주려면 어댑터가 필요함.
        // 데이터가 배열이므로 ArrayAdapter 사용
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listView = findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), strText, Toast.LENGTH_SHORT).show();
            }
        });
    }

}