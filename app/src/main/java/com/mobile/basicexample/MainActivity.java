package com.mobile.basicexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명시적 인텐트");

        final RatingBar rating1, rating2, rating3;
        Button btnInc, btnDec;

        rating1 = findViewById(R.id.ratingBar1);
        rating2 = findViewById(R.id.ratingBar2);
        rating3 = findViewById(R.id.ratingBar3);
        btnInc = findViewById(R.id.btnInc);
        btnDec = findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setRating(rating1.getRating() + rating1.getStepSize()); // 0.5씩
                rating2.setRating(rating2.getRating() + rating2.getStepSize()); // 1씩
                rating3.setRating(rating3.getRating() + rating3.getStepSize()); // 0.5씩
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating1.setRating(rating1.getRating() - rating1.getStepSize());
                rating2.setRating(rating2.getRating() - rating2.getStepSize());
                rating3.setRating(rating3.getRating() - rating3.getStepSize());
            }
        });
    }

}

