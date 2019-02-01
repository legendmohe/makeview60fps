package com.rosberry.view60fps.model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by dmitry on 25.01.2018.
 */

class FillShape extends Shape {

    FillShape(int color) {
        this.color = color;
    }

    @Override
    void draw(int width, int height, Canvas canvas, Paint defaultPaint) {
        defaultPaint.setColor(color);
        canvas.drawPaint(defaultPaint);
    }
}
