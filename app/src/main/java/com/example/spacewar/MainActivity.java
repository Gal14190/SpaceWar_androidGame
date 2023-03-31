package com.example.spacewar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button[] levelButtons;
    private Button[] modeButtons;
    private Button startGameButton;
    private Button scoreTableButton;

    private int colorStyle_select = Color.rgb(0, 123, 255);
    private int colorStyle_not_select = Color.rgb(108, 117, 125);

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

        // level slow click event
        levelButtons[0].setOnClickListener(view -> {
            levelButtons[0].setBackgroundColor(colorStyle_select);
            levelButtons[1].setBackgroundColor(colorStyle_not_select);
            levelSelect = false;
        });

        // level fast click event
        levelButtons[1].setOnClickListener(view -> {
            levelButtons[1].setBackgroundColor(colorStyle_select);
            levelButtons[0].setBackgroundColor(colorStyle_not_select);
            levelSelect = true;
        });

        // mode arrows buttons click event
        modeButtons[0].setOnClickListener(view -> {
            modeButtons[0].setBackgroundColor(colorStyle_select);
            modeButtons[1].setBackgroundColor(colorStyle_not_select);
            modeSelect = false;
        });

        // mode sensor click event
        modeButtons[1].setOnClickListener(view -> {
            modeButtons[1].setBackgroundColor(colorStyle_select);
            modeButtons[0].setBackgroundColor(colorStyle_not_select);
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

        });

    }

    private void initViews() {
        levelButtons = new Button[2];
        // slow level button view (0)
        levelButtons[0] = (Button) findViewById(R.id.slowLevelBtn);
        levelButtons[0].setBackgroundColor(colorStyle_select);
        // fast level button view (1)
        levelButtons[1] = (Button) findViewById(R.id.fastLevelBtn);
        levelButtons[1].setBackgroundColor(colorStyle_not_select);

        modeButtons = new Button[2];
        // arrows button mode view (0)
        modeButtons[0] = (Button) findViewById(R.id.arrowModeBtn);
        modeButtons[0].setBackgroundColor(colorStyle_select);
        // sensor mode view (1)
        modeButtons[1] = (Button) findViewById(R.id.sensorModeBtn);
        modeButtons[1].setBackgroundColor(colorStyle_not_select);

        // start game button view
        startGameButton = (Button) findViewById(R.id.startGameBtn);
        // score table button view
        scoreTableButton = (Button) findViewById(R.id.scoreTableBtn);
    }
}
