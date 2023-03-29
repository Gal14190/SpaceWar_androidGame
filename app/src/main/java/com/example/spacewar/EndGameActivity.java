package com.example.spacewar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_activity);

        // hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // set score
        Intent intentGetScore = getIntent();
        int score = intentGetScore.getIntExtra("scoreCounter", -1);
        ((TextView) findViewById(R.id.scoreCounter)).setText(Integer.toString(score));

        // display the game field on text click event
        ((Button) findViewById(R.id.backBtn)).setOnClickListener(v->{
            Intent intent = new Intent(this, GameFieldActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
