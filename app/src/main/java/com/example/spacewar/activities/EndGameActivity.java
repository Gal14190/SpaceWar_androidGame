package com.example.spacewar.activities;

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

import com.example.spacewar.R;
import com.example.spacewar.models.TopGame;
import com.example.spacewar.manage.DataManage;

import java.util.ArrayList;
import java.util.Calendar;

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

        // get location
        Locate locate = getGpsLocation();

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
        topGames.add(new TopGame(score, getDeviceTimeStr(), locate.longitude, locate.latitude));
        dataManage.setTopGame(topGames);
    }

    private Locate getGpsLocation() {
        Locate locate = new Locate();

        // check for GPS permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // get gps location service
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // set gps update location
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, location -> {});
            // get location
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                locate.latitude = (float)location.getLatitude();
                locate.longitude = (float)location.getLongitude();
            }
        } else {
            // handle with non gps permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        }

        return locate;
    }

    private String getDeviceTimeStr() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)
                + " " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
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

class Locate {
    public float longitude;
    public float latitude;
}
