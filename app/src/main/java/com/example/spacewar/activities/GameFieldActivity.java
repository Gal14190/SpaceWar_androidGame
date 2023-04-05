package com.example.spacewar.activities;

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
import android.widget.TextView;

import com.example.spacewar.models.GameFieldModel;
import com.example.spacewar.R;
import com.example.spacewar.effects.NeonAnimation;
import com.example.spacewar.manage.ComponentManage;
import com.example.spacewar.manage.ObstaclesManage;

public class GameFieldActivity extends AppCompatActivity {
    private final Handler mHideHandler = new Handler(Looper.myLooper());
    private View mControlsView;

    private ImageView neon;
    private ImageButton rightBtn, leftBtn;

    private LinearLayout componentLayout;
    private LinearLayout obstacleLayout;

    private TextView scoreTextView;
    private ImageView[] hearts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamefield_activity);

        initViews();    // init all views ids
        GameFieldModel.lives = 3;   // reset lives

        // set speed level
        if(getIntent().getBooleanExtra("level", false))
            GameFieldModel.cycle_delay = GameFieldModel.CYCLE_DELAY_FAST;
        else
            GameFieldModel.cycle_delay = GameFieldModel.CYCLE_DELAY_SLOW;

        // get game mode (buttons or sensors)
        GameFieldModel.eMode mode = getIntent().getBooleanExtra("mode", false)? GameFieldModel.eMode.SENSORS
                                                                                                :GameFieldModel.eMode.ARROWS;

        // Neon Animation rotation
        NeonAnimation neonAnimation = new NeonAnimation(neon);
        neonAnimation.start();

        // init spaceship component and move buttons
        ComponentManage components = new ComponentManage(this, componentLayout, GameFieldModel.COMPONENT_SRC);
        components.setupComponents(); // setup components views
        components.setupEvents(rightBtn, leftBtn, mode);

        // init obstacles
        ObstaclesManage obstacles = new ObstaclesManage(this
                , obstacleLayout
                , GameFieldModel.OBSTACLE_SRC
                , GameFieldModel.COIN_SRC
                , hearts
                ,scoreTextView);
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

        scoreTextView = (TextView) findViewById(R.id.scoreCounter);
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