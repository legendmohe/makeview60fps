package com.rosberry.view60fps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.rosberry.view60fps.model.SceneModelComposer;
import com.rosberry.view60fps.model.SimpleSceneModel;
import com.rosberry.view60fps.model.StressSceneModel;
import com.rosberry.view60fps.view.GameSurfaceView;
import com.rosberry.view60fps.view.GameTextureView;
import com.rosberry.view60fps.view.GameView;

public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    ViewGroup continer;
    SceneModelComposer sceneModelComposer;
    IComposer gameView = null;
    private Thread mTimerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continer = findViewById(R.id.viewContainer);
        ((RadioGroup) findViewById(R.id.radio_group_views)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkbox_ha)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkbox_mode)).setOnCheckedChangeListener(this);
        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);

        sceneModelComposer = new SceneModelComposer(getResources().getDisplayMetrics().widthPixels);
        if (((CheckBox) findViewById(R.id.checkbox_mode)).isChecked()) {
            sceneModelComposer.setSceneModel(new StressSceneModel(getResources().getDisplayMetrics().widthPixels));
        } else {
            sceneModelComposer.setSceneModel(new SimpleSceneModel());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        stopTimer();

        switch (checkedId) {
            case R.id.viewCanvas:
                gameView = new GameView(this);
                break;
            case R.id.surfaceViewCanvas:
                gameView = new GameSurfaceView(this);
                break;
            case R.id.textureViewCanvas:
                gameView = new GameTextureView(this);
                break;
        }
        gameView.setComposer(sceneModelComposer);

        continer.removeAllViews();
        continer.addView((View) gameView);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (gameView != null) {
            stopTimer();

            if (buttonView.getId() == R.id.checkbox_ha)
                if (isChecked) {
                    ((View) gameView).setLayerType(View.LAYER_TYPE_HARDWARE, null);
                } else {
                    ((View) gameView).setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
            if (buttonView.getId() == R.id.checkbox_mode)
                if (isChecked) {
                    sceneModelComposer.setSceneModel(new StressSceneModel(getResources().getDisplayMetrics().widthPixels));
                } else {
                    sceneModelComposer.setSceneModel(new SimpleSceneModel());
                }
            ((View) gameView).invalidate();
            sceneModelComposer.invalidate();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button: {
                if (gameView != null) {
                    startTimer();
                }
            }
            break;
            case R.id.stop_button: {
                stopTimer();
            }
            break;
        }
    }

    private void startTimer() {
        stopTimer();
        mTimerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!(mTimerThread != null && mTimerThread.isInterrupted()) && !(isDestroyed() && isFinishing())) {
                    sceneModelComposer.changeModel(System.currentTimeMillis());
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        });
        mTimerThread.start();
    }

    private void stopTimer() {
        if (mTimerThread != null) {
            mTimerThread.interrupt();
            mTimerThread = null;
        }
    }
}
