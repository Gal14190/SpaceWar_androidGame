package com.example.spacewar;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

public class DataManage {
    private final int TOP_SIZE = 10;
    private SharedPreferences sharedPreferences;

    public DataManage(Context _context) {
        sharedPreferences = _context.getSharedPreferences("score", Context.MODE_PRIVATE);
    }

    public ArrayList<TopGame> getTopGame() {
        ArrayList<TopGame> topGamesList = new ArrayList<>();

        for(int i = 0; i < TOP_SIZE; i++) {
            TopGame topGame = new TopGame();
            topGame.setScore(this.sharedPreferences.getInt("s_" + i, 0));
            topGame.setLon(this.sharedPreferences.getFloat("lon_" + i, 0.0F));
            topGame.setLat(this.sharedPreferences.getFloat("lat_" + i, 0.0F));
            topGamesList.add(topGame);
        }

        return topGamesList;
    }

    public void setTopGame(ArrayList<TopGame> topGamesList) {
        this.sort(topGamesList);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        for(int i = 0; i < topGamesList.size(); i++) {
            editor.putInt("s_" + i, topGamesList.get(i).getScore());
            editor.putFloat("lon_" + i, topGamesList.get(i).getLon());
            editor.putFloat("lat_" + i, topGamesList.get(i).getLat());
            editor.apply();
        }
    }

    private void sort(ArrayList<TopGame> topGamesList) {
        Collections.sort(topGamesList, (t1, t2) -> t2.getScore() - t1.getScore());

        if(topGamesList.size() > 10) {
            for(int i = 10; i < topGamesList.size(); i++) {
                topGamesList.remove(i);
            }
        }
    }
}

