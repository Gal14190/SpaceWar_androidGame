package com.example.spacewar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ScoreTableFragment extends Fragment {
    private CallbackPosition callbackPosition;
    private ArrayList<TopGameView> topGameView;

    public ScoreTableFragment() {}

    public void setCallbackPosition(CallbackPosition _callbackPosition) {
        this.callbackPosition = _callbackPosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score_table_fragment, container, false);

        // set rows of data
        TableLayout scoreTableView = view.findViewById(R.id.topTableView);
        DataManage dataManage = new DataManage(getActivity().getBaseContext());
        ArrayList<TopGame> topGames = dataManage.getTopGame();

        topGameView = new ArrayList<>();
        int i = 0;
        for(TopGame topGame : topGames) {
            TableRow row = setTableRow(view); // new row
            setContext(topGame, row, i++);    // set data
            scoreTableView.addView(row);    //add row to table
        }

        return view;
    }

    private TopGame findById(int id) {
        for(TopGameView t : topGameView) {
            if(t.id == id)
                return t.topGame;
        }

        return null;
    }

    private TableRow setTableRow(View view) {
        TableRow row = new TableRow(getContext()); // set new row

        // display icon fireball first
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.fireball);
        imageView.setLayoutParams(view.findViewById(R.id.exampleImage).getLayoutParams());
        row.addView(imageView);

        return row;
    }

    private void setContext(TopGame topGame, TableRow dest, int i) {
        // display score
        TextView scoreView = new TextView(getContext());
        scoreView.setText(Integer.toString(topGame.getScore()));
        scoreView.setTransitionName(Integer.toString(i));
        scoreView.setTextColor(Color.BLACK);
        scoreView.setTextSize(20);
        scoreView.setGravity(Gravity.CENTER);

        // click event on score to mark the location when the game locate over the google map
        topGameView.add(new TopGameView(topGame, i));
        scoreView.setOnClickListener(v ->{
            int id = (int)Integer.parseInt(v.getTransitionName());
            TopGame _topGame = findById(id);
            callbackPosition.position(_topGame.getLat(), _topGame.getLon());
        });

        dest.addView(scoreView);

        // display date and time
        TextView timeView = new TextView(getContext());
        timeView.setText(topGame.getTime());
        timeView.setTextSize(14);
        dest.addView(timeView);
    }

}

class TopGameView {
    public TopGame topGame;
    public int id;

    public TopGameView(TopGame topGame, int id) {
        this.topGame = topGame;
        this.id = id;
    }
}