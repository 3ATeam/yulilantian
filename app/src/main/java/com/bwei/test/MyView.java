package com.bwei.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：李亚雷
 * 时间：2017/6/12
 * 类用途：我的自定义view
 * 思路：1.通过构造方法获得自定义属性值
 *      2.通过ondrow方法画出所需view形状
 *      3.通过ontouch方法实现拖动
 */

public class MyView extends View {

    private int ringWinth;
    private int ringColor;
    private int filletWinth;
    private int filletColor;
    private String text = "0";
    private int lastX;
    private int lastY;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyView_Ring_winth:
                    ringWinth = a.getInteger(attr, 50);
                    break;
                case R.styleable.MyView_Ring_color:
                    // 圆环默认颜色设置为灰色
                    ringColor = a.getColor(attr, Color.GRAY);
                    break;

                case R.styleable.MyView_Fillet_winth:
                    filletWinth = a.getInteger(attr, 50);
                    break;
                case R.styleable.MyView_Fillet_color:
                    // 内圆默认颜色设置为绿色
                    filletColor = a.getColor(attr, Color.GREEN);
                    break;


            }

        }
        a.recycle();


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置圆环的画笔
        Paint ringpaint = new Paint();
        ringpaint.setColor(ringColor);
        //设置内圆的画笔
        Paint filletPaint = new Paint();
        filletPaint.setColor(filletColor);
        //设置字体的画笔
        Paint textPaint = new Paint();
        textPaint.setTextSize(50);

        canvas.drawCircle(400, 400, ringWinth, ringpaint);
        canvas.drawCircle(400, 400, filletWinth, filletPaint);


        canvas.drawText(text + "%", 360, 400, textPaint);


    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRingWinth() {
        return ringWinth;
    }

    public void setRingWinth(int ringWinth) {
        this.ringWinth = ringWinth;
    }

    public int getFilletWinth() {
        return filletWinth;
    }

    public void setFilletWinth(int filletWinth) {
        this.filletWinth = filletWinth;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                if (text.equals("100")) {


                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;

                    int left = getLeft() + dx;
                    int top = getTop() + dy;
                    int right = getRight() + dx;
                    int bottom = getBottom() + dy;

                    layout(left, top, right, bottom);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }


}
