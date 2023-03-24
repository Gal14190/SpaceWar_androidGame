package com.example.spacewar;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameFieldActivity extends AppCompatActivity {
    private final Handler mHideHandler = new Handler(Looper.myLooper());
    private View mControlsView;

    private ImageView neon;
    private ImageButton rightBtn, leftBtn;

    private LinearLayout componentLayout;
    private LinearLayout obstacleLayout;


    private ImageView[] hearts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamefield_activity);

        initViews();    // init all views ids
        GameFieldModel.lives = 3;   // reset lives

        // Neon Animation rotation
        NeonAnimation neonAnimation = new NeonAnimation(neon);
        neonAnimation.start();

        // init spaceship component and move buttons
        ComponentManage components = new ComponentManage(this, componentLayout, GameFieldModel.COMPONENT_SRC);
        components.setupComponents(); // setup components views
        components.setupEvents(rightBtn, leftBtn);

        // init obstacles
        ObstaclesManage obstacles = new ObstaclesManage(this, obstacleLayout, GameFieldModel.OBSTACLE_SRC, hearts);
        obstacles.setupObstacles(); // setup obstacles views
        obstacles.start(); // start the game thread
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullScreen();
    }

    private void initViews() {
        neon = (ImageView) findViewById(R.id.neon);

        componentLayout = (LinearLayout) findViewById(R.id.component_layout);
        obstacleLayout = (LinearLayout) findViewById(R.id.obstacles_container);

        rightBtn = (ImageButton) findViewById(R.id.button_move_right);
        leftBtn = (ImageButton) findViewById(R.id.button_move_left);

        hearts = new ImageView[3];
        hearts[0] = (ImageView) findViewById(R.id.heart_0);
        hearts[1] = (ImageView) findViewById(R.id.heart_1);
        hearts[2] = (ImageView) findViewById(R.id.heart_2);
    }

    private void fullScreen() {
        mControlsView = findViewById(R.id.neon);
        // Hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Delayed removal of status and navigation bar
        if (Build.VERSION.SDK_INT >= 30) {
            mControlsView.getWindowInsetsController().hide(
                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mControlsView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}