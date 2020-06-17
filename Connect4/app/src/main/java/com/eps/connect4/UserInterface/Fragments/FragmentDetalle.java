package com.eps.connect4.UserInterface.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.eps.connect4.R;
import com.eps.connect4.util.PlayedGame;

public class FragmentDetalle extends Fragment {
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalle, container, false);

        return view;
    }

    public void mostrarDetalle(String alias,String date, int size, boolean timecontrol,int resultat,int icon) {
        System.out.println(alias+"< "+date+" <"+size+"< "+timecontrol+" <"+resultat);

        //USER
        TextView player = view.findViewById(R.id.db_alias);
        player.setText(alias);

        //DATE
        TextView date_view =
                view.findViewById(R.id.db_date);
        date_view.setText(date);

        //SIZE
        TextView detalleSize =
                view.findViewById(R.id.db_size);
        detalleSize.setText(String.valueOf(size));

        //TIME CONTROL
        TextView detalleTime =
                view.findViewById(R.id.db_time_ctrl);
        detalleTime.setText(String.valueOf(timecontrol));


        //RESULT
        TextView detalleResult =
                view.findViewById(R.id.db_result);
        detalleResult.setText(PlayedGame.getResultGameString(resultat));

        //ICON
        ImageView detalleicon =
                view.findViewById(R.id.icon_result);
        detalleicon.setImageResource(icon);




    }

}