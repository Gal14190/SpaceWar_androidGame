package com.example.spacewar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class TopActivity extends AppCompatActivity {
    private ScoreTableFragment scoreTableFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_activity);

        // back to main page button click event
        ((ImageButton) findViewById(R.id.backBtn)).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        scoreTableFragment = new ScoreTableFragment();
        mapFragment = new MapFragment();

        CallbackPosition callbackPosition = (v1, v2) -> mapFragment.zoom(v1, v2);
        scoreTableFragment.setCallbackPosition(callbackPosition);

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
