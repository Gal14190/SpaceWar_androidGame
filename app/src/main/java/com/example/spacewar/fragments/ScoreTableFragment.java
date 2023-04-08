package com.example.spacewar.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.spacewar.R;
import com.example.spacewar.models.TopGame;
import com.example.spacewar.manage.CallbackPosition;
import com.example.spacewar.manage.DataManage;

import java.util.ArrayList;

public class ScoreTableFragment extends Fragment {
    private CallbackPosition callbackPosition;

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

        ListView listView = (ListView) view.findViewById(R.id.socre_list);
        listView.setAdapter(null);

        // get data to display
        DataManage dataManage = new DataManage(getActivity().getBaseContext());
        ArrayList<TopGame> data = dataManage.getTopGame();

        // set data adapter for list view
        ItemsAdapter adapter = new ItemsAdapter(view.getContext(), callbackPosition, data);
        listView.setAdapter(adapter);

        return view;
    }
}

class ItemsAdapter extends ArrayAdapter<TopGame> {
    Context context;
    ArrayList<TopGame> data;
    CallbackPosition callbackPosition;

    public ItemsAdapter(Context context, CallbackPosition callbackPosition, ArrayList<TopGame> data) {
        super(context, R.layout.score_item, data);
        this.context = context;
        this.data = data;
        this.callbackPosition = callbackPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.score_item, null);
        }

        // get the current data
        TopGame contact = data.get(position);

        // view score and dataTime of a game
        ((TextView) view.findViewById(R.id.point)).setText(Integer.toString(contact.getScore()));
        ((TextView) view.findViewById(R.id.datetime)).setText(contact.getTime());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set mark position
                callbackPosition.position(contact.getLat(), contact.getLon());
            }
        });
        return view;
    }
}
