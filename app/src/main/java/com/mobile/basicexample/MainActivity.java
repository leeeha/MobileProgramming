package com.mobile.basicexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

// 추상클래스 ActionBar의 인터페이스 TabListener에 있는 메소드 오버라이딩하기
// onTabSelected, onTabUnselected, onTabReselected
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar.Tab tabSong, tabArtist, tabAlbum;
    MyTabFragment myFrags[] = new MyTabFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액션바 탭으로 프래그먼트 전환하기");

        // 액션바의 네비게이션 모드를 탭으로 설정
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // 액션바에 3개의 탭 생성
        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this);
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag;

        // 처음 선택된 탭이면 프래그먼트 생성 후,
        // 선택된 탭 이름을 담고 있는 번들 객체를 인자로 전달하기
        if(myFrags[tab.getPosition()] == null){
            myTabFrag = new MyTabFragment();

            Bundle data = new Bundle();
            data.putString("tabName", tab.getText().toString());
            myTabFrag.setArguments(data);

            myFrags[tab.getPosition()] = myTabFrag;
        }
        else{ // 선택된 탭으로 업데이트
            myTabFrag = myFrags[tab.getPosition()];
        }

        // 기존의 프래그먼트를 새로 선택된 프래그먼트로 교체하기
        ft.replace(android.R.id.content, myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyTabFragment extends Fragment {
        String tabName;

        @Override // 프래그먼트 생성 후, 선택된 탭 이름 번들에서 꺼내기
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }

        @Override // 선택된 탭에 따라 레이아웃 색상 다르게 해서 리턴
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // XML 없이 자바 코드에서 리니어 레이아웃 생성
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);

            if(tabName == "음악별")
                baseLayout.setBackgroundColor(Color.RED);
            else if(tabName == "가수별")
                baseLayout.setBackgroundColor(Color.GREEN);
            else if(tabName == "앨범별")
                baseLayout.setBackgroundColor(Color.BLUE);

            return baseLayout;
        }
    }




}

