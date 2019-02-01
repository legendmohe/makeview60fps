package com.rosberry.view60fps.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by hexinyu on 2019/2/1.
 */
public class TextShape extends Shape {

    private String mText;

    private Rect mBounds = new Rect();

    private Paint mPaint = new Paint();

    private int mPadding;

    public TextShape() {
        mPaint.setStrokeWidth(8);
        mPaint.setTextSize(80);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
    }

    public int getPadding() {
        return mPadding;
    }

    public void setPadding(int padding) {
        mPadding = padding;
    }

    public String getTextPreDraw() {
        return mText;
    }

    public String getText() {
        return mText;
    }

    public void setValue(String text) {
        mText = text;
    }

    @Override
    void draw(int width, int height, Canvas canvas, Paint defaultPaint) {
        String text = getTextPreDraw();
        if (text != null) {
            mPaint.getTextBounds(text, 0, text.length(), mBounds);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            float baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top + mPadding;
            canvas.drawText(text, width / 2f, baseline, mPaint);
        }
    }
}
