package com.rosberry.view60fps.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by dmitry on 25.01.2018.
 */

public class RectShape extends Shape {

    private final int strokeWidth;
    final Rect rect;

    RectShape(Rect rect, int strokeWidth) {
        this.rect = rect;
        this.strokeWidth = strokeWidth;
        this.color = Color.BLACK;

    }

    @Override
    void draw(int width, int height, Canvas canvas, Paint defaultPaint) {
        defaultPaint.setColor(color);
        defaultPaint.setStrokeWidth(strokeWidth);

        canvas.drawRect(rect, defaultPaint);
    }
}
