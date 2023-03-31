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

        initViews();

        levelButtons[0].setOnClickListener(view -> {
            levelButtons[0].setBackgroundColor(colorStyle_select);
            levelButtons[1].setBackgroundColor(colorStyle_not_select);
            levelSelect = false;
        });

        levelButtons[1].setOnClickListener(view -> {
            levelButtons[1].setBackgroundColor(colorStyle_select);
            levelButtons[0].setBackgroundColor(colorStyle_not_select);
            levelSelect = true;
        });

        modeButtons[0].setOnClickListener(view -> {
            modeButtons[0].setBackgroundColor(colorStyle_select);
            modeButtons[1].setBackgroundColor(colorStyle_not_select);
            modeSelect = false;
        });

        modeButtons[1].setOnClickListener(view -> {
            modeButtons[1].setBackgroundColor(colorStyle_select);
            modeButtons[0].setBackgroundColor(colorStyle_not_select);
            modeSelect = true;
        });

        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameFieldActivity.class);
            intent.putExtra("level", levelSelect);
            intent.putExtra("mode", modeSelect);
            startActivity(intent);
            finish();
        });

    }

    private void initViews() {
        levelButtons = new Button[2];
        levelButtons[0] = (Button) findViewById(R.id.slowLevelBtn);
        levelButtons[0].setBackgroundColor(colorStyle_select);
        levelButtons[1] = (Button) findViewById(R.id.fastLevelBtn);
        levelButtons[1].setBackgroundColor(colorStyle_not_select);

        modeButtons = new Button[2];
        modeButtons[0] = (Button) findViewById(R.id.arrowModeBtn);
        modeButtons[0].setBackgroundColor(colorStyle_select);
        modeButtons[1] = (Button) findViewById(R.id.sensorModeBtn);
        modeButtons[1].setBackgroundColor(colorStyle_not_select);

        startGameButton = (Button) findViewById(R.id.startGameBtn);
        scoreTableButton = (Button) findViewById(R.id.scoreTableBtn);
    }
}
