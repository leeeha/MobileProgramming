package com.mobile.basicexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Paint 관련 메소드: https://jwandroid.tistory.com/182
            Paint paint = new Paint();

            // 직선 그리기
            paint.setAntiAlias(true); // Paint의 경계면을 부드럽게 처리할지 설정
            paint.setColor(Color.GREEN);
            canvas.drawLine(10, 10, 300, 10, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5f);
            canvas.drawLine(10, 30, 300, 30, paint);

            // 직사각형 그리기: https://black-jin0427.tistory.com/144
            paint.setColor(Color.RED);
            paint.setStrokeWidth(0f);
            paint.setStyle(Paint.Style.FILL);
            Rect rect1 = new Rect(10, 50, 10 + 100, 50 +100);
            canvas.drawRect(rect1, paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(130, 50, 130 + 100, 50 + 100);
            canvas.drawRect(rect2, paint);

            // 모서리가 둥근 직사각형
            RectF rect3 = new RectF(250, 50, 250 + 100, 50 + 100);
            canvas.drawRoundRect(rect3, 20, 20, paint);

            // 원 그리기, drawArc()로는 부채꼴을 그릴 수 있음.
            canvas.drawCircle(60, 220, 50, paint);

            // 좌표에 따른 경로 그리기
            paint.setStrokeWidth(5f);
            Path path1 = new Path();
            path1.moveTo(10, 290);
            path1.lineTo(10 + 50, 290 + 50);
            path1.lineTo(10 + 100, 290);
            path1.lineTo(10 + 150, 290 + 50);
            path1.lineTo(10 + 200, 290);
            canvas.drawPath(path1, paint);

            // 텍스트 그리기
            paint.setStrokeWidth(0f);
            paint.setTextSize(30f);
            paint.setColor(Color.BLACK);
            canvas.drawText("안드로이드", 10, 390, paint);
        }
    }
}

