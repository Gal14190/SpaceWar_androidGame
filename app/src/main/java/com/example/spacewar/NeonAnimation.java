package com.example.spacewar;

import android.util.Log;
import android.widget.ImageView;

class NeonAnimation extends Thread {
    ImageView obj;
    NeonAnimation(ImageView _obj) {
        this.obj = _obj;
    }

    public void run() {
        float angle = 0;
        while (true) {
            obj.setRotation(angle);

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                Log.e("Neon Animation", "Error sleep: " + e.getMessage());
            }
            // rotate the object 360 degrees
            angle++;
            if(angle >= 360)
                angle = 0;
        }
    }
}