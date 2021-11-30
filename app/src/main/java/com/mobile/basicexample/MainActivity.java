package com.mobile.basicexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
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
        setTitle("엠보싱 효과");
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

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);

            // EmbossMaskFilter(빛의 xyz 방향, 빛의 밝기, 반사 계수, 블러링 크기);
            EmbossMaskFilter eMask =
                    new EmbossMaskFilter(new float[] {3, 10, 3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, cenY, 150, paint);
        }
    }
}

