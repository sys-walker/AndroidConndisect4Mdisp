package com.eps.connect4.UserInterface.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.eps.connect4.Connect4Logical.Board;
import com.eps.connect4.Connect4Logical.ImageAdapter;
import com.eps.connect4.R;
import com.eps.connect4.util.PreferenciasJuego;
import com.eps.connect4.util.constants;


public class Connect4GameFragment extends Fragment {
    public View view;
    public Board board;
    public Connect4Listener listener;
    private int countDown =PreferenciasJuego.prefs_connect4_countdown;
    private TextView turn;
    private TextView timing;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Connect4Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "No listener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else {
            board = new Board(PreferenciasJuego.prefs_connect4_size,PreferenciasJuego.prefs_time_ctrl_bool,countDown);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_connect4_game, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTextTimer();
        startDisplay();
    }

    private void startDisplay() {
        timing = view.findViewById(R.id.timing);
        turn =  view.findViewById(R.id.turn);
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), board, turn, timing, listener);
        GridView board = view.findViewById(R.id.board);
        board.setBackgroundColor(Color.BLUE);
        board.setAdapter(imageAdapter);
        board.setNumColumns(PreferenciasJuego.prefs_connect4_size);
    }

    private void setTextTimer() {
        TextView timer_name = view.findViewById(R.id.title_clock);

        if ((PreferenciasJuego.prefs_time_ctrl_bool)) {
            timer_name.setText(getResources().getString(R.string.counter_down));
        } else {
            timer_name.setText(getResources().getString(R.string.cronometer));
        }
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        this.countDown = savedInstanceState.getInt(constants.STATE_COUNT);
        this.board = savedInstanceState.getParcelable(constants.STATE_BOARD);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(constants.STATE_BOARD, board);
        outState.putInt(constants.STATE_COUNT, countDown);
    }


    //Per el log i aquestes coses
    public interface Connect4Listener {
        void onItemSelected(Integer position, Board board);
    }

    public void setConnect4Listener(Connect4Listener listener) {
        this.listener = listener;
    }




}
