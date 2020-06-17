package com.eps.connect4.UserInterface.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.eps.connect4.R;


public class LogFragment extends Fragment {

    String text;
    public View view;

    @Override
    public void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsatanceState){
       view =  inflater.inflate(R.layout.fragment_log, container, false);
        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }


    public void mostrarLogs(String log){
        text = log;

        TextView log_text =view.findViewById(R.id.text_log_field);
        log_text.setText(log);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("log", text);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mostrarLogs(savedInstanceState.getString("log"));
    }


}
