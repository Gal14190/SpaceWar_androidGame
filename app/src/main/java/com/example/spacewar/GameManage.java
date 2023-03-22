package com.example.spacewar;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

public abstract class GameManage extends Thread {
    private Context context;

    public GameManage(Context _context) {
        this.context = _context;
    }

    protected int convertPixelsToDp(float px){
        return (int) px / ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    protected int rand(int a, int b) {
        Random random = new Random();
        return random.nextInt(b + 1) + a;
    }

    protected boolean isHit(int position) {
        return position == GameFieldModel.componentPosition;
    }

    @Override
    public void run() {
        super.run();
    }

    protected abstract void review();
}
