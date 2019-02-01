package com.rosberry.view60fps;

import com.rosberry.view60fps.model.SceneFrame;

/**
 * Created by dmitry on 01.02.2018.
 */

public interface IScene {

    void changeValue(long value);

    void addSceneFrame(SceneFrame sceneFrame);
}
