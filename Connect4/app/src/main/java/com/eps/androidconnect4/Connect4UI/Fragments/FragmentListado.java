package com.eps.androidconnect4.Connect4UI.Fragments;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eps.androidconnect4.R;
import com.eps.androidconnect4.util.GameResult;
import com.eps.androidconnect4.util.UsuariosSQLiteHelper;
import com.eps.androidconnect4.util.constants;

import java.util.ArrayList;

//import android.app.ListFragment;

public class FragmentListado extends Fragment {
	
	private GameResult[] datos;
	
	private ListView lstListado;
	
	private GameResultListener listener;

	private View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.fragment_listado, container, false);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		UsuariosSQLiteHelper myDB = new UsuariosSQLiteHelper(getContext(), "DBUsuarios");
		Cursor data = myDB.getListContents();

		datos = getAllData(data);

		GameResultAdapter thelist = new GameResultAdapter(this,datos);


		lstListado = v.findViewById(R.id.LstListado);
		
		lstListado.setAdapter(thelist);

		//Listener por click ---->FragmentDetalle
		lstListado.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) { if (listener!=null) {
					listener.onGameResultSelected(
							(GameResult)lstListado.getAdapter().getItem(pos));
				} }
			
		});
	}

	private GameResult[] getAllData(Cursor data) {
		ArrayList<GameResult> datos_raw = new ArrayList<>();
		if (data.getCount()!=0){
			while (data.moveToNext()){ //data.getString(1),data.getString(2),data.getInt(3)
				GameResult c = new GameResult(data.getString(1),data.getInt(2),(data.getInt(3)==0),data.getString(4),data.getInt(5));
				datos_raw.add(c);
			}
		}

		datos =  datos_raw.toArray(new GameResult[0]);
		return datos;
	}


	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		try {
			listener = (GameResultListener) context;
		}
		catch (ClassCastException e) {
			throw new ClassCastException(context.toString() + " must implement onGameResultSelected");
		}
	}

			
	class GameResultAdapter extends ArrayAdapter<GameResult> {
    	
    	Activity context;
    	
    	GameResultAdapter(FragmentListado fragmentListado, GameResult[] datos) {
    		super(fragmentListado.getActivity(), R.layout.listitem_game_result, datos);
    		this.context = fragmentListado.getActivity();
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View layout_item = inflater.inflate(R.layout.listitem_game_result, null);



			//Mostra el alias
			TextView alias = (TextView)layout_item.findViewById(R.id.list_item_alias);
			alias.setText(datos[position].getPlayer_alias());
			//Mostra la data
			TextView date = (TextView)layout_item.findViewById(R.id.list_item_date);
			date.setText(datos[position].getDate());
			//Mostra la estat_final
			TextView s = (TextView)layout_item.findViewById(R.id.list_item_stat);
			s.setText(datos[position].getResultGameString());



			ImageView imageView  = layout_item.findViewById(R.id.action_image);
			switch (datos[position].getResult_int()){
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

			
			return(layout_item);
		}
    }
	
	public interface GameResultListener {
		void onGameResultSelected(GameResult c);
	}

	public void onGameResultSelected(GameResultListener listener) {
		this.listener=listener;
	}
}
