package com.eps.androidconnect4.Connect4UI.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.eps.androidconnect4.R;
import com.eps.androidconnect4.util.constants;

public class FragmentDetalle extends Fragment {
	public View v ;

	public FragmentDetalle(){

	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		v =inflater.inflate(R.layout.fragment_detalle, container, false);
		return v;
	}


	//DetalleActivity(by invocation) --->set FragmentDetalleLayout


	public void mostrarDetalle(String resultGameString, String player_alias, int board_size, boolean time_control, String date) {
		TextView txtDetalle = (TextView)v.findViewById(R.id.DetailResult);
		txtDetalle.setText(resultGameString);
		TextView t = (TextView)v.findViewById(R.id.DetailUser);
		t.setText("Jugador: "+ player_alias); //portable a diferentes idiomas

		TextView g = (TextView)v.findViewById(R.id.DetailBoard);
		g.setText("Mida taula: "+ board_size);

		TextView h = v.findViewById(R.id.DetailTimeCtrl);
		h.setText("Control de temps: "+ time_control);

		TextView he = v.findViewById(R.id.DetailDate);
		he.setText("data: "+ date);

	}
	public void mostrarIconoDetalle(int result_int) {
		ImageView imageView = v.findViewById(R.id.imageView);
		switch (result_int){
			case constants.WIN_RESULT:
				imageView.setImageResource(R.drawable.icon_winner);
				break;

			case constants.LOSE_RESULT:
				imageView.setImageResource(R.drawable.icon_loser);
				break;
			case constants.DRAW_RESULT:
				imageView.setImageResource(R.drawable.icon);
				break;
		}



	}


}
