package com.example.spacewar.manage;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.spacewar.models.GameFieldModel;

public class ComponentManage extends GameManage{
    private Context context;
    private LinearLayout layout;
    private int drawableSrc;

    private TiltManage tiltManage;

    private ComponentView[] componentView;

    public ComponentManage(Context _context, LinearLayout _layout, int _drawableSrc) {
        super(_context);

        this.drawableSrc = _drawableSrc;
        this.layout = _layout;
        this.context = _context;

        componentView = new ComponentView[GameFieldModel.COLUMN_SIZE];
        for(int i = 0; i < componentView.length; i++)
            componentView[i] = new ComponentView();

    }

    public void setupComponents() {
        // create component view
        for(int i = 0; i < componentView.length; i++) {
            ImageView component = setComponentView();
            layout.addView(component);
            componentView[i].view = component;
        }

        // set center position component and display it
        resetState();
        GameFieldModel.componentPosition = componentView.length / 2;
        componentView[GameFieldModel.componentPosition].state = true;
        review();
    }

    private ImageView setComponentView() {
        // create a view component
        ImageView view = new ImageView(context);
        view.setImageResource(drawableSrc);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, convertPixelsToDp(GameFieldModel.COMPONENT_SIZE));
        params.gravity = Gravity.CENTER;
        params.weight = 1;
        view.setLayoutParams(params);

        return view;
    }

    private void resetState() {
        // reset component state
        for(int i = 0; i < componentView.length; i++)
            componentView[i].state = false;
    }

    public void setupEvents(View rightBtn, View leftBtn, GameFieldModel.eMode mode) {
        switch (mode){
            case SENSORS:
                // hide buttons
                rightBtn.setVisibility(View.INVISIBLE);
                leftBtn.setVisibility(View.INVISIBLE);

                // create sensor event listener
                tiltManage = new TiltManage(context);
                tiltManage.setCallbackComponent(new CallbackComponent() {
                    @Override
                    public void moveRight() {
                        ComponentManage.this.moveRight();
                    }

                    @Override
                    public void moveLeft() {
                        ComponentManage.this.moveLeft();
                    }

                    @Override
                    public void speedFaster() {
                        ComponentManage.this.speedFaster();
                    }

                    @Override
                    public void speedSlower() {
                        ComponentManage.this.speedSlower();
                    }
                });
                break;
            case ARROWS:
                // show buttons
                rightBtn.setVisibility(View.VISIBLE);
                leftBtn.setVisibility(View.VISIBLE);

                // create onClick listener for the right button controller
                rightBtn.setOnClickListener(view -> moveRight());

                // create onClick listener for the left button controller
                leftBtn.setOnClickListener(view -> moveLeft());

                break;
        }
    }

    private void moveRight() {
        // check if the new component position is valid and update the view
        int newPos = GameFieldModel.componentPosition + 1;
        if(newPos < componentView.length) {
            GameFieldModel.componentPosition = newPos;

            resetState();
            componentView[GameFieldModel.componentPosition].state = true;
            review();
        }
    }

    private void moveLeft() {
            // check if the new component position is valid and update the view
            int newPos = GameFieldModel.componentPosition - 1;
            if(newPos >= 0) {
                GameFieldModel.componentPosition = newPos;

                resetState();
                componentView[GameFieldModel.componentPosition].state = true;
                review();
            }
    }

    private void speedFaster() {
        if(GameFieldModel.cycle_delay - 50 > 50)
            GameFieldModel.cycle_delay -= 50;
    }
    private void speedSlower() {
        if(GameFieldModel.cycle_delay + 50 < 1000)
            GameFieldModel.cycle_delay += 50;
    }

    @Override
    protected void review() {
        for(int i = 0; i < componentView.length; i++) {
            componentView[i].view.setVisibility(componentView[i].state? View.VISIBLE : View.INVISIBLE);
        }
    }
}

class ComponentView {
    public ImageView view;
    public boolean state;
}
