package com.mobile.basicexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CustomPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("뷰페이저 기본 사용법");

        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new CustomPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

    }
}

