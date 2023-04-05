package com.example.spacewar.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spacewar.R;

public class MainActivity extends AppCompatActivity {
    private Button[] levelButtons;
    private Button[] modeButtons;
    private Button startGameButton;
    private Button scoreTableButton;

    private final int STYLE_COLOR_SELECT = Color.rgb(0, 123, 255);
    private final int STYLE_COLOR_NOT_SELECT = Color.rgb(108, 117, 125);

    private boolean levelSelect = false;
    private boolean modeSelect = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initViews(); // add views

        setEventListener(); // set click events

    }

    private void initViews() {
        levelButtons = new Button[2];
        // slow level button view (0)
        levelButtons[0] = (Button) findViewById(R.id.slowLevelBtn);
        levelButtons[0].setBackgroundColor(STYLE_COLOR_SELECT);
        // fast level button view (1)
        levelButtons[1] = (Button) findViewById(R.id.fastLevelBtn);
        levelButtons[1].setBackgroundColor(STYLE_COLOR_NOT_SELECT);

        modeButtons = new Button[2];
        // arrows button mode view (0)
        modeButtons[0] = (Button) findViewById(R.id.arrowModeBtn);
        modeButtons[0].setBackgroundColor(STYLE_COLOR_SELECT);
        // sensor mode view (1)
        modeButtons[1] = (Button) findViewById(R.id.sensorModeBtn);
        modeButtons[1].setBackgroundColor(STYLE_COLOR_NOT_SELECT);

        // start game button view
        startGameButton = (Button) findViewById(R.id.startGameBtn);
        // score table button view
        scoreTableButton = (Button) findViewById(R.id.scoreTableBtn);
    }

    private void setEventListener() {
        // level slow click event
        levelButtons[0].setOnClickListener(view -> {
            levelButtons[0].setBackgroundColor(STYLE_COLOR_SELECT);
            levelButtons[1].setBackgroundColor(STYLE_COLOR_NOT_SELECT);
            levelSelect = false;
        });

        // level fast click event
        levelButtons[1].setOnClickListener(view -> {
            levelButtons[1].setBackgroundColor(STYLE_COLOR_SELECT);
            levelButtons[0].setBackgroundColor(STYLE_COLOR_NOT_SELECT);
            levelSelect = true;
        });

        // mode arrows buttons click event
        modeButtons[0].setOnClickListener(view -> {
            modeButtons[0].setBackgroundColor(STYLE_COLOR_SELECT);
            modeButtons[1].setBackgroundColor(STYLE_COLOR_NOT_SELECT);
            modeSelect = false;
        });

        // mode sensor click event
        modeButtons[1].setOnClickListener(view -> {
            modeButtons[1].setBackgroundColor(STYLE_COLOR_SELECT);
            modeButtons[0].setBackgroundColor(STYLE_COLOR_NOT_SELECT);
            modeSelect = true;
        });

        // start game button click event
        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameFieldActivity.class);
            intent.putExtra("level", levelSelect);
            intent.putExtra("mode", modeSelect);
            startActivity(intent);
            finish();
        });

        // show score table click event
        scoreTableButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, TopActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
