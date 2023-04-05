package com.example.spacewar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spacewar.fragments.MapFragment;
import com.example.spacewar.R;
import com.example.spacewar.fragments.ScoreTableFragment;

public class TopActivity extends AppCompatActivity {
    private ScoreTableFragment scoreTableFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_activity);

        // set back to main page button click event
        ((ImageButton) findViewById(R.id.backBtn)).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // setup fragments
        mapFragment = new MapFragment();
        scoreTableFragment = new ScoreTableFragment();
        scoreTableFragment.setCallbackPosition((v1, v2) -> mapFragment.zoom(v1, v2)); // use callback for pass location form score to map fragment

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView1, scoreTableFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView2, mapFragment)
                .commit();
    }
}
