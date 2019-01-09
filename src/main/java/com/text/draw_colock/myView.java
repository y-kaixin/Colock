package com.text.draw_colock;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.logging.Handler;

public class myView extends View{
    private Calendar calendar;

    public myView(Context context) {
        super(context);
        calendar = Calendar.getInstance();
    }

    public myView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        //画笔的颜色
        paint.setColor(Color.BLACK);
        //画笔的宽度
        paint.setStrokeWidth(5);
        //设置样式  不填充
        paint.setStyle(Paint.Style.STROKE);
        //抗锯齿
        paint.setAntiAlias(true);
        //取宽高的最小值
        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width, height);
        int radius = min/2-100;
        canvas.drawCircle(min/2,min/2,radius,paint);
        paint.setStrokeWidth(5);
        //绘制钟表的中心  只填充不描边
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(min/2,min/2,5,paint);

        //标刻度  0
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);

        int x = min/2;
        int y = min/2-radius;

        for(int i = 1;i<13 ; i++){
            canvas.save();
            canvas.rotate(i*30,min/2,min/2);
            canvas.drawText(i+"",x-20,y+80,paint);
            canvas.drawLine(x,y,x,y+40,paint);
            canvas.restore();
        }
        //绘制刻度
        paint.setStrokeWidth(2);
        for (int i = 0;i<60;i++){
            canvas.save();
            canvas.rotate(i*6,min/2,min/2);
            canvas.drawLine(x,y,x,y+20,paint);
            canvas.restore();
        }

        //得到当前时间
        calendar = Calendar.getInstance();

        int hours = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        //时针
        canvas.save();
        Float hoursDegree = (hours * 60 + minute) / 12f / 60 * 360;
        canvas.rotate(hoursDegree,min/2,min/2);
        canvas.drawLine(min/2,min / 2 - 160, min / 2, min / 2 + 60,paint);
        canvas.restore();

        //分针
        canvas.save();
        Float minutesDegree = minute / 60f * 360;
        canvas.rotate(minutesDegree,min/2,min/2);
        canvas.drawLine(min / 2, min / 2 -240, min / 2, min / 2 + 40,paint);
        canvas.restore();

        //秒针
        canvas.save();
        Float secondDegree = second/60f*360;
        canvas.rotate(secondDegree,min/2,min/2);
        canvas.drawLine(min / 2, min / 2 - 320, min / 2, min / 2 + 80,paint);
        canvas.restore();

        invalidate();
    }
}
