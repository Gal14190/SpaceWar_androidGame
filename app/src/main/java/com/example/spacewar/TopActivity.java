package com.example.spacewar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class TopActivity extends AppCompatActivity {
    private TableLayout scoreTableView;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView1, ScoreTableFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
        ScoreTableFragment fragment = (ScoreTableFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView1);

    }
}
