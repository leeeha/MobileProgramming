package com.mobile.basicexample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리사이클러뷰 예제");

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("Text %d", i));
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        RecyclerView recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정
        SimpleTextAdapter adapter = new SimpleTextAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
        private ArrayList<String> mData;

        // 아이템 뷰를 저장하는 뷰홀더 클래스 정의
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView1;

            ViewHolder(View itemView){
                super(itemView);

                // 아이템뷰에 표시될 텍스트뷰에 대한 참조를 가지도록
                textView1 = itemView.findViewById(R.id.text1);
            }
        }

        // 생성자에서 데이터 리스트 객체를 전달받음.
        SimpleTextAdapter(ArrayList<String> list){
            mData = list;
        }

        @Override // 아이템 뷰를 저장하는 뷰홀더 객체를 생성하여 리턴
        public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
            SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view);

            return vh;
        }

        @Override // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
        public void onBindViewHolder(ViewHolder holder, int position) {
            String text = mData.get(position);
            holder.textView1.setText(text);
        }

        @Override // 전체 데이터 개수 리턴
        public int getItemCount() {
            return mData.size();
        }
    }
}

