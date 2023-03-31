package com.example.spacewar;

public class TopGame {
    private int score;
    private float lon;
    private float lat;

    public TopGame(int score, float lon, float lat) {
        this.score = score;
        this.lon = lon;
        this.lat = lat;
    }
    public TopGame(){}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
