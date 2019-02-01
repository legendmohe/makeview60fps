package com.rosberry.view60fps.model;

import com.rosberry.view60fps.IScene;

/**
 * Created by dmitry on 29.01.2018.
 */

public class SimpleSceneModel implements IScene {

    private TimerShape mTimerShape = new TimerShape();

    public void changeValue(long value) {
        mTimerShape.setValue(String.valueOf(value));
    }

    public void addSceneFrame(SceneFrame sceneFrame) {
        sceneFrame.shapes.clear();
        sceneFrame.shapes.add(mTimerShape);
    }
}
