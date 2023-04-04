package com.example.spacewar.effects;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffect {
    private SoundPool soundPool;
    private int soundID;

    public SoundEffect(Context context, int resID) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(context, resID, 1);
    }

    public void play() {
        soundPool.play(soundID, 1, 1, 1, 0, 1);
    }

    public void release() {
        soundPool.release();
        soundPool = null;
    }
}