package com.rosberry.view60fps.model;

/**
 * Created by hexinyu on 2019/2/1.
 */
public class TimerShape extends TextShape {

    @Override
    public String getTextPreDraw() {
        long ts = System.currentTimeMillis();
        long mm = ts % 1000;
        long s = ts / 1000 % 60;
        long m = ts / 1000 / 60 % 60;
        long h = ts / 1000 / 60 / 60 % 24;
        return String.format("%02d:%02d:%02d.%03d", h, m, s, mm);
    }
}
