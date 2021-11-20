package com.mobile.basicexample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GridView로 영화 포스터 보여주기");

        final GridView gv = findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
        // 그리드뷰에 어댑터 설정만 해주면,
        // gAdapter 객체가 생성되면서 getView 멤버함수가 자동 호출되는 거 같다.
    }

    private class MyGridAdapter extends BaseAdapter {
        Context context;

        Integer[] posterID = {
                R.drawable.mov11, R.drawable.mov12, R.drawable.mov13,
                R.drawable.mov14, R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17, R.drawable.mov18, R.drawable.mov19,
                R.drawable.mov20
        };

        String[] movieTitle = {
                "영화 제목1", "영화 제목2", "영화 제목3",
                "영화 제목4", "영화 제목5", "영화 제목6",
                "영화 제목7", "영화 제목8", "영화 제목9",
                "영화 제목10"
        };

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return posterID[i];
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterID[position]);

            final int pos = position;

            // 이미지를 클릭하면 다이얼로그가 뜨도록
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // dialog.xml 파일을 dialogView 변수에 인플레이트 시키기
                    View dialogView = View.inflate(MainActivity.this,
                            R.layout.dialog, null);
                    /* 리스트 뷰 대신 리사이클러 뷰를 사용해야 하는 이유 알아보기!!
                        Unconditional layout inflation from view adapter: Should use View Holder pattern
                        (use recycled view passed into this method as the second parameter) for smoother scrolling
                     */

                    // 다이얼로그 빌더 생성
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                    // dialog.xml에 있던 이미지뷰인 ivPoster 초기화
                    ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);

                    // 다이얼로그 제목, 아이콘, 뷰, 닫기 버튼 설정
                    dlg.setTitle(movieTitle[pos]);
                    dlg.setIcon(R.drawable.ic_launcher_foreground);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
               }
            });

            return imageView;
        }
    }
}