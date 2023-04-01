package com.example.spacewar;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class DataManage {
    private final int MAX_TOP_SIZE = 10;
    private SharedPreferences sharedPreferences;

    public DataManage(Context _context) {
        sharedPreferences = _context.getSharedPreferences("score", Context.MODE_PRIVATE);
    }

    public ArrayList<TopGame> getTopGame() {
        ArrayList<TopGame> topGamesList = new ArrayList<>();

        String dataJson = sharedPreferences.getString("jsonData", null);
        if(dataJson != null) {
            Gson gson = new Gson();
            Type topGameType = new TypeToken<ArrayList<TopGame>>() {}.getType();
            topGamesList = gson.fromJson(dataJson, topGameType);
        }
        return topGamesList;
    }

    public void setTopGame(ArrayList<TopGame> topGamesList) {
        this.sort(topGamesList);

        Gson gson = new Gson();
        String json = gson.toJson(topGamesList);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jsonData", json).apply();
    }

    private void sort(ArrayList<TopGame> topGamesList) {
        Collections.sort(topGamesList, (t1, t2) -> t2.getScore() - t1.getScore());

        if(topGamesList.size() > MAX_TOP_SIZE) {
            for(int i = 10; i < topGamesList.size(); i++) {
                topGamesList.remove(i);
            }
        }
    }
}

