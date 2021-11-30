package com.mobile.basicexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("비트맵 다루기");
    }


    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // /res/drawable 폴더에서 이미지 파일 가져오는 경우
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.renoir01);
            // sd 카드에서 이미지 파일 가져오는 경우
            //Bitmap picture = BitmapFactory.decodeFile("파일경로 및 파일");

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            // 화면 중앙에 이미지 배치
            //canvas.drawBitmap(picture, picX, picY, null);

            canvas.rotate(45, cenX, cenY); // 회전
            canvas.drawBitmap(picture, picX, picY, null);

            canvas.translate(-150, 200); // 좌표 이동
            canvas.drawBitmap(picture, picX, picY, null);

            canvas.scale(2f, 2f, cenX, cenY); // 크기 확대, 축소
            canvas.drawBitmap(picture, picX, picY, null);

            canvas.skew(0.3f, 0.3f); // 기울기
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }
}

