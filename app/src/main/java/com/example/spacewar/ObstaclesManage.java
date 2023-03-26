package com.example.spacewar;

import static java.lang.Thread.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ObstaclesManage extends GameManage {
    private boolean flag_play;
    private Vibrator vibrator;

    private Context context;
    private Activity activity;
    private LinearLayout layout;
    private int drawableSrc;
    private ImageView[] hearsView;

    private ObstacleView[][] obstaclesViews;

    ObstaclesManage(Activity _activity, LinearLayout _layout, int _drawableSrc, ImageView[] _hearsView) {
        super(_activity.getApplicationContext());

        this.drawableSrc = _drawableSrc;
        this.layout = _layout;
        this.context = _activity.getApplicationContext();
        this.activity = _activity;
        this.hearsView = _hearsView;

        this.obstaclesViews = new ObstacleView[GameFieldModel.COLUMN_SIZE][GameFieldModel.ROW_SIZE + 1];
        for(int i = 0; i < obstaclesViews.length; i++)
            for(int j = 0; j < obstaclesViews[0].length; j++)
                obstaclesViews[i][j] = new ObstacleView();
    }

    public void setupObstacles() {
        for(int i = 0; i < GameFieldModel.COLUMN_SIZE; i++){
            // set columns layout
            LinearLayout obs_layout = setObstaclesLayout();

            // create and inset obstacles views into the column layout
            for(int j = 0; j < GameFieldModel.ROW_SIZE; j++) {
                ImageView obs = setObstacleView();
                obs_layout.addView(obs);

                obstaclesViews[i][j].view = obs; // keep the obstacle view
            }

            this.layout.addView(obs_layout); // add the layout into the main layout
        }

        resetStates(); // invisible the obstacles
        review(); // display obstacles by it's states
    }

    public void run() {
        // start the game thread
        flag_play = true;
        while (flag_play)
        {
            // update display views
            this.activity.runOnUiThread(()-> {
                this.review();
                this.checkHit();
            });

            nextState();     // update obstacle next states

            // rand a position of obstacle
            int pos = rand(0, GameFieldModel.COLUMN_SIZE + 1);   // 3/5 rate for display obstacles
            // set a state for a valid position
            if(pos < obstaclesViews.length) {
                obstaclesViews[pos][0].state = true;
            }

            delay();    // cycle delay
        }
    }

    private LinearLayout setObstaclesLayout() {
        // create a vertical linearLayout dived equal screen by weight and centralize thar context
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(params);

        return linearLayout;
    }

    private ImageView setObstacleView() {
        // create a imageView for obstacle
        ImageView view = new ImageView(context);
        view.setImageResource(drawableSrc);
        view.setLayoutParams(new ViewGroup.LayoutParams(convertPixelsToDp(GameFieldModel.OBSTACLE_SIZE)
                                                        ,convertPixelsToDp(GameFieldModel.OBSTACLE_SIZE)));

        return view;
    }

    private void resetFirstStates() {
        // reset the first row states (index 0)
        for(int i = 0; i < GameFieldModel.COLUMN_SIZE; i++)
            obstaclesViews[i][0].state = false;
    }

    private void resetStates() {
        // reset the all states
        for(int i = 0; i < GameFieldModel.COLUMN_SIZE; i++)
            for(int j = 0; j < obstaclesViews[0].length; j++)
                obstaclesViews[i][j].state = false;
    }

    private void nextState() {
        // move forward the states, from bottom to up
        for(int i = GameFieldModel.COLUMN_SIZE - 1; i >= 0; i--)
            for(int j = obstaclesViews[0].length - 1; j > 0; j--)
                obstaclesViews[i][j].state = obstaclesViews[i][j - 1].state;

        resetFirstStates(); // reset the first states (index 0) for set a new random state
    }

    @Override
    protected void review() {
        // display the obstacles views by is state
        for(int i = 0; i < GameFieldModel.COLUMN_SIZE; i++)
            for(int j = 0; j < GameFieldModel.ROW_SIZE; j++)
                obstaclesViews[i][j].view.setVisibility(obstaclesViews[i][j].state ? View.VISIBLE : View.INVISIBLE);
    }

    private void checkHit() {
        // check if the current component (spaceship) position is in with obstacle position
        // decrease live counter and invisible heart icon
        if(obstaclesViews[GameFieldModel.componentPosition][GameFieldModel.ROW_SIZE].state) {
            hearsView[--GameFieldModel.lives].setVisibility(View.INVISIBLE);

            Toast.makeText(context, "Hit!", Toast.LENGTH_SHORT).show(); // toast massage

            // vibrate 500ms
            vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                vibrator.vibrate(500);
            }

            // stop the game and start the finish activity if the lives counter is 0
            if(GameFieldModel.lives <= 0) {
                flag_play = false;

                Intent intent = new Intent(context, EndGameActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }

        }

    }
}

class ObstacleView {
    public ImageView view;
    public boolean state;
}