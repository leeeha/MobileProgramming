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

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            Paint paint = new Paint();

            // 블러링 효과
            // BlurMaskFilter(반지름, 스타일)
            // 블러 종류: NORMAL, INNER, OUTER, SOLID
            BlurMaskFilter bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
            paint.setMaskFilter(bMask);

            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();
        }
    }
}

