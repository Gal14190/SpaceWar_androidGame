package com.example.spacewar.manage;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.example.spacewar.models.GameFieldModel;

import java.util.Random;

public abstract class GameManage extends Thread {
    private Context context;
    private Vibrator vibrator;

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
            sleep(GameFieldModel.cycle_delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message popup
     */
    public void messagePopup(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); // toast massage
    }

    /**
     * Device vibrator
     */
    public void vibrator() {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(500);
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
