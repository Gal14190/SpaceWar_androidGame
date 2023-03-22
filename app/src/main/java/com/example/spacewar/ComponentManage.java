package com.example.spacewar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;

public class ComponentManage extends GameManage{
    public enum Direction { RIGHT, LEFT };

    private Context context;
    private LinearLayout layout;
    private int drawableSrc;

    private ImageView[] componentsImages;
    private boolean[] componentsState;

    ComponentManage(Context _context, LinearLayout _layout, int _drawableSrc) {
        super(_context);

        this.drawableSrc = _drawableSrc;
        this.layout = _layout;
        this.context = _context;

        componentsImages = new ImageView[GameFieldModel.COLUMN_SIZE];
        componentsState = new boolean[GameFieldModel.COLUMN_SIZE];
    }

    public void setupComponents() {
        for(int i = 0; i < componentsImages.length; i++) {
            ImageView component = new ImageView(context);
            component.setImageResource(drawableSrc);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(convertPixelsToDp(450), ViewGroup.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            component.setLayoutParams(params);

            layout.addView(component);
            componentsImages[i] = component;
        }

        resetState();
        GameFieldModel.componentPosition = componentsState.length / 2;
        componentsState[GameFieldModel.componentPosition] = true;
        review();
    }

    private void resetState() {
        Arrays.fill(componentsState, false);
    }

    public void setupEvents(View rightBtn, View leftBtn) {
        rightBtn.setOnClickListener(view -> {
            int newPos = GameFieldModel.componentPosition + 1;
            if(newPos < componentsState.length) {
                GameFieldModel.componentPosition = newPos;

                resetState();
                componentsState[GameFieldModel.componentPosition] = true;
                review();
            }
        });

        leftBtn.setOnClickListener(view -> {
            int newPos = GameFieldModel.componentPosition - 1;
            if(newPos >= 0) {
                GameFieldModel.componentPosition = newPos;

                resetState();
                componentsState[GameFieldModel.componentPosition] = true;
                review();
            }
        });
    }

    @Override
    protected void review() {
        for(int i = 0; i < componentsState.length; i++) {
            if (componentsState[i]) {
                componentsImages[i].setVisibility(View.VISIBLE);
            } else {
                componentsImages[i].setVisibility(View.INVISIBLE);
            }
        }
    }
}
