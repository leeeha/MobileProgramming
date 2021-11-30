package com.mobile.basicexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageButton ibZoomIn, ibZoomOut, ibRotate, ibBright, ibDark, ibGray;
    MyGraphicView graphicView;
    static float scaleX = 1, scaleY = 1;
    static float angle = 0;
    static float color = 1;
    static float satur = 1; // 채도 saturation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");

        LinearLayout pictureLayout = findViewById(R.id.pictureLayout);
        graphicView = new MyGraphicView(this);
        pictureLayout.addView(graphicView);

        clickIcons();

    }

    private void clickIcons(){
        ibZoomIn = findViewById(R.id.ibZoomIn);
        ibZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX += 0.2f;
                scaleY += 0.2f;
                graphicView.invalidate(); // onDraw 함수 다시 호출
            }
        });

        ibZoomOut = findViewById(R.id.ibZoomOut);
        ibZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                graphicView.invalidate();
            }
        });

        ibRotate = findViewById(R.id.ibRotate);
        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle -= 20; // 반시계 방향으로 회전
                graphicView.invalidate();
            }
        });

        ibBright = findViewById(R.id.ibBright);
        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color += 0.2f;
                graphicView.invalidate();
            }
        });

        ibDark = findViewById(R.id.ibDark);
        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color -= 0.2f;
                graphicView.invalidate();
            }
        });

        ibGray = findViewById(R.id.ibGray);
        ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satur = (satur == 0) ? 1 : 0; // 채도 반전
                graphicView.invalidate();
            }
        });
    }

    private static class MyGraphicView extends View {

        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY); // 크기 설정
            canvas.rotate(angle, cenX, cenY); // 각도 설정

            Paint paint = new Paint();

            // 컬러 매트릭스로 이미지의 밝기, 채도 설정
            float[] array = {
                    color, 0, 0, 0, 0, // red vector
                    0, color, 0, 0, 0, // green vector
                    0, 0, color, 0, 0, // blue vector
                    0, 0, 0, 1, 0, // alpha vector
            };
            ColorMatrix cm = new ColorMatrix(array);
            if(satur == 0){
                cm.setSaturation(satur); // 채도 설정
            }
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.renoir01);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();
        }
    }
}

