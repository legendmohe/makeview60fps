package com.rosberry.view60fps.model;

import com.rosberry.view60fps.IScene;

/**
 * Created by dmitry on 29.01.2018.
 */

public class StressSceneModel implements IScene{

    private SceneFrame frame = new SceneFrame();

    public StressSceneModel(int screenWidth) {

        for (int i = 0; i < 10; i++) {
            TimerShape shape = new TimerShape();
            shape.setPadding(-500 + i * 100);
            frame.shapes.add(shape);
        }

    }

    public void changeValue(long value) {
        for (Shape shape : frame.shapes) {
            ((TimerShape) shape).setValue(String.valueOf(value));
        }
    }

    public void addSceneFrame(SceneFrame sceneFrame) {
        sceneFrame.shapes.clear();
        sceneFrame.shapes.addAll(frame.shapes);
    }
}
