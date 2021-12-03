package com.mobile.basicexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {
    // LayoutInflater 서비스 사용을 위한 Context 참조
    private Context context;

    public CustomPagerAdapter(Context c){
        context = c;
    }

    @NonNull
    @Override // 화면에 표시할 페이지뷰 생성
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View pageView = null;

        if(context != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            pageView = inflater.inflate(R.layout.page, container, false);

            // 리사이클러뷰를 메인 액티비티 말고, 뷰페이저 안에서 띄우자!!!
            // 페이지뷰 하나 생성할 때마다 새로운 리사이클러뷰가 뜨도록
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(String.format("TEXT %d", i));
            }

            // 리사이클러뷰의 레이아웃 매니저 지정
            RecyclerView recyclerView = pageView.findViewById(R.id.recyclerview_main_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

            // 리사이클러뷰의 어댑터 지정
            RecyclerAdapter adapter = new RecyclerAdapter(list);
            recyclerView.setAdapter(adapter);

        }

        // 뷰페이저인 container에 리사이클러뷰를 담고 있는 pageView 추가하기
        container.addView(pageView);

        return pageView;
    }

    // 뷰페이저에서 삭제
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 5; // 전체 페이지수는 10개로 고정
    }

    @Override // 페이지뷰가 특정 키 객체(key object)와 연관되는지
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    // 이너 클래스로 리사이클러뷰 어댑터 정의...
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>{
        private ArrayList<String> data;

        // 리사이클러뷰의 아이템 하나를 저장하는 뷰홀더 클래스
        public class CustomViewHolder extends RecyclerView.ViewHolder{
            TextView textView;

            CustomViewHolder(View itemView){
                super(itemView);

                // 리사이클러뷰 안에 있는 항목들 참조
                textView = itemView.findViewById(R.id.textView);
            }
        }

        public RecyclerAdapter(ArrayList<String> list){
            this.data = list;
        }

        @NonNull
        @Override // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);

            return new CustomViewHolder(view);
        }

        @Override // position에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            String text = data.get(position);
            holder.textView.setText(text);
        }

        @Override // 리사이클러뷰에 담겨 있는 전체 아이템 개수 리턴
        public int getItemCount() {
            return data.size();
        }
    }
}
