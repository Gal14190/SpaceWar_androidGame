package com.example.spacewar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class EndGameActivity extends AppCompatActivity {
    private final int PERMISSIONS_REQUEST_LOCATION = 999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_activity);

        // hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        float latitude = 0.0F;
        float longitude = 0.0F;

        // get location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, location -> {});
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                latitude = (float)location.getLatitude();
                longitude = (float)location.getLongitude();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        }


        // get score
        Intent intentGetScore = getIntent();
        int score = intentGetScore.getIntExtra("scoreCounter", -1);
        ((TextView) findViewById(R.id.scoreCounter)).setText(Integer.toString(score));

        // main page button event
        ((Button) findViewById(R.id.backBtn)).setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // set game data into the device memory
        DataManage dataManage = new DataManage(this);
        ArrayList<TopGame> topGames = dataManage.getTopGame();
        topGames.add(new TopGame(score, longitude, latitude));
        dataManage.setTopGame(topGames);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:

                break;
        }
    }
}
