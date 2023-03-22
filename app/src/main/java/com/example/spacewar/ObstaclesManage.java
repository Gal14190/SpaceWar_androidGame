package com.example.spacewar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ObstaclesManage extends GameManage {
    private boolean flag_play;

    private Context context;
    private Activity activity;
    private LinearLayout layout;
    private int drawableSrc;
    private ImageView[] hearsView;

    private ImageView[][] obstaclesImages;
    private boolean[][] obstaclesState;

    ObstaclesManage(Activity _activity, LinearLayout _layout, int _drawableSrc, ImageView[] _hearsView) {
        super(_activity.getApplicationContext());

        this.drawableSrc = _drawableSrc;
        this.layout = _layout;
        this.context = _activity.getApplicationContext();
        this.activity = _activity;
        this.hearsView = _hearsView;

        obstaclesImages = new ImageView[GameFieldModel.COLUMN_SIZE][GameFieldModel.ROW_SIZE];
        obstaclesState = new boolean[GameFieldModel.COLUMN_SIZE][GameFieldModel.ROW_SIZE];
    }

    public void setupObstacles() {
        for(int i = 0; i < obstaclesImages.length; i++){
            LinearLayout obs_layout = new LinearLayout(context);
            obs_layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            obs_layout.setLayoutParams(params);

            for(int j = 0; j < obstaclesImages[0].length; j++) {
                ImageView obs = new ImageView(context);
                obs.setImageResource(drawableSrc);
                obs.setLayoutParams(new ViewGroup.LayoutParams(convertPixelsToDp(GameFieldModel.OBSTACLE_SIZE), convertPixelsToDp(GameFieldModel.OBSTACLE_SIZE)));

                obs_layout.addView(obs);
                obstaclesImages[i][j] = obs;
            }

            this.layout.addView(obs_layout);
        }

        resetStates();
        review();
    }

    public void run() {
        flag_play = true;
        while (flag_play)
        {
            this.activity.runOnUiThread(()-> {
                this.review();
                this.checkHit();
            });

            try {
                Thread.sleep(GameFieldModel.REFRESH_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            next();
            resetFirstStates();
            if(rand(0, 1) == 0) continue; // rotation without obstacles

            int pos = rand(0, obstaclesState.length - 1);
            obstaclesState[pos][0] = true;
        }
    }

    private void resetFirstStates() {
        for(int i = 0; i < obstaclesState.length; i++)
            obstaclesState[i][0] = false;
    }

    private void resetStates() {
        for(int i = 0; i < obstaclesState.length; i++)
            for(int j = 0; j < obstaclesState[0].length; j++)
                obstaclesState[i][j] = false;
    }

    private void next() {
        for(int i = obstaclesState.length - 1; i >= 0; i--)
            for(int j = obstaclesState[0].length - 1; j > 0; j--)
                obstaclesState[i][j] = obstaclesState[i][j - 1];
    }

    @Override
    protected void review() {
        for(int i = 0; i < obstaclesState.length; i++)
            for(int j = 0; j < obstaclesState[0].length; j++)
            {
                if(obstaclesState[i][j])
                    obstaclesImages[i][j].setVisibility(View.VISIBLE);
                else
                    obstaclesImages[i][j].setVisibility(View.INVISIBLE);
            }
    }

    private void checkHit() {
        if(obstaclesState[GameFieldModel.componentPosition][obstaclesState[0].length - 1]) {
            hearsView[--GameFieldModel.lives].setVisibility(View.INVISIBLE);

            if(GameFieldModel.lives <= 0) {
                flag_play = false;

                Intent intent = new Intent(context, EndGameActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }

        }

    }
}
