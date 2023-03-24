package com.example.spacewar;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

public abstract class GameManage extends Thread {
    private Context context;

    public GameManage(Context _context) {
        this.context = _context;
    }

    /**
     * Convert px to dp using screen density
     * @param px
     * @return
     */
    protected int convertPixelsToDp(float px) {
        return (int) px / ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Randomize number for set obstacle position
     * @param a
     * @param b
     * @return
     */
    protected int rand(int a, int b) {
        Random random = new Random();
        return random.nextInt(b + 1) + a;
    }

    /**
     * Delay thread for cycle rate
     */
    protected void delay() {
        try {
            sleep(GameFieldModel.CYCLE_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Using run thread method
     */
    @Override
    public void run() {
        super.run();
    }

    /**
     * Refresh view
     */
    protected abstract void review();
}
