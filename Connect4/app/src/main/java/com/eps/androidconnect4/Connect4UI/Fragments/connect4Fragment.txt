package com.eps.androidconnect4.UIgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.eps.androidconnect4.MainActivity;
import com.eps.androidconnect4.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class connect4Fragment extends Fragment implements View.OnClickListener {
    public static Activity a;

    public connect4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Connect4");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_connect4, container, false);

        GridView gridview = v.findViewById(R.id.gridview);
        gridview.setBackgroundColor(Color.BLUE);

        ImageAdapter imageAdapter = new ImageAdapter(getContext());
        int size= MainActivity.b.getInt("MIDA_GRAELLA"); //got from data Tranfer

        gridview.setNumColumns(size);
        for (int i = 0; i < size*size ; i++) {
            imageAdapter.add(R.drawable.fitxa_buida);
        }
        imageAdapter.Arrayize();
        gridview.setAdapter(imageAdapter);

        Button b = v.findViewById(R.id.button2);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"commdasfdfaaafadfadfencing",Toast.LENGTH_LONG).show();
    }
}
