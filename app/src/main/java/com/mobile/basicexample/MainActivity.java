package com.mobile.basicexample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gallery로 영화 포스터 보여주기");

        Gallery gallery = findViewById(R.id.gallery1);
        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);

    }

    private class MyGalleryAdapter extends BaseAdapter {
        Context context;

        Integer[] posterID = {
                R.drawable.mov11, R.drawable.mov12, R.drawable.mov13,
                R.drawable.mov14, R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17, R.drawable.mov18, R.drawable.mov19,
                R.drawable.mov20
        };

        String[] movieTitle = {
                "여인의 향기", "쥬라기 공원", "포레스트 검프",
                "사랑의 블랙홀", "혹성탈출", "아름다운 비행",
                "내 이름은 칸", "해리포터", "마더",
                "킹콩을 들다"
        };

        public MyGalleryAdapter(Context c){
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

            // ViewGroup.LayoutParams(X) Gallery.LayoutParams (O)
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterID[position]);

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 이미지 초기화
                    ImageView ivPoster = findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);

                    // toast.xml 레이아웃 파일을
                    // 자바 코드 내의 toastView 객체에 인플레이트 시키기
                    Toast toast = new Toast(getApplicationContext());
                    View toastView = View.inflate(getApplicationContext(),
                            R.layout.toast, null);

                    // 인플레이트시킨 toastView의 텍스트 뷰 변경하고 setView로 지정
                    TextView toastText = toastView.findViewById(R.id.movieTitle);
                    toastText.setText(movieTitle[pos]);
                    toast.setView(toastView);
                    toast.show();
                }
            });

            return imageView; // NullPointerException 주의!!!!
        }
    }
}

