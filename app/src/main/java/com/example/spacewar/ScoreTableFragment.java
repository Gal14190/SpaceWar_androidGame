package com.example.spacewar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreTableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScoreTableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreTableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreTableFragment newInstance(String param1, String param2) {
        ScoreTableFragment fragment = new ScoreTableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.score_table_fragment, container, false);

        TableLayout scoreTableView = view.findViewById(R.id.topTableView);
        DataManage dataManage = new DataManage(getActivity().getBaseContext());
        ArrayList<TopGame> topGames = dataManage.getTopGame();

        for(TopGame topGame : topGames) {
            TableRow row = new TableRow(getContext());

            TextView scoreView = new TextView(getContext());
            scoreView.setText(Integer.toString(topGame.getScore()));
            scoreView.setTextColor(Color.BLACK);
            scoreView.setTextSize(30);
            scoreView.setGravity(Gravity.CENTER);
            row.addView(scoreView);

            scoreTableView.addView(row);
        }

        return view;
    }

}