package com.eps.connect4.UserInterface.Fragments;


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

import com.eps.connect4.util.PlayedGame;
import com.eps.connect4.R;
import com.eps.connect4.util.UsuariosSQLiteHelper;
import com.eps.connect4.util.constants;

import java.util.ArrayList;

public class FragmentListado extends Fragment {

    public PlayedGame[] datos ;
    private ListView lstListado;

    private PlayedGameListener listener;
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_listado, container, false);
        return view;
    }



    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        UsuariosSQLiteHelper myDB = new UsuariosSQLiteHelper(getContext());
        Cursor data = myDB.getListContents();

        datos = getAllData(data);





        lstListado = (ListView)view.findViewById(R.id.LstListado);



        lstListado.setAdapter(new GameAdapter(this,datos));

        lstListado.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onPlayedGameSeleccionado(
                            (PlayedGame)lstListado.getAdapter().getItem(pos));
                }
            }

        });
    }

    private PlayedGame[] getAllData(Cursor data) {
        ArrayList<PlayedGame> datos_raw = new ArrayList<>();
        if (data.getCount()!=0){
            while (data.moveToNext()){ //data.getString(1),data.getString(2),data.getInt(3)
               PlayedGame c = new PlayedGame(data.getString(1),data.getInt(2),(data.getInt(3)==0),data.getString(4),data.getInt(5));
               datos_raw.add(c);
            }
        }
        datos =  datos_raw.toArray(new PlayedGame[0]);
        return datos;

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PlayedGameListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCorreosListener");
        }
    }


    class GameAdapter extends ArrayAdapter<PlayedGame> {

        Activity context;

        GameAdapter(FragmentListado fragmentListado, PlayedGame[] datos) {
            super(fragmentListado.requireActivity(), R.layout.listitem_entry_bbdd, datos);
            this.context = fragmentListado.getActivity();
        }

        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_entry_bbdd, null);


            TextView lblDe = item.findViewById(R.id.LblDe);
            lblDe.setText(datos[position].getPlayer_alias());

            TextView fecha = item.findViewById(R.id.fecha_hora);
            fecha.setText(datos[position].getDate());

            TextView result_str =item.findViewById(R.id.resultado_juego);
            result_str.setText(datos[position].getResultGameString());



            ImageView imageView=item.findViewById(R.id.icon_result);

            switch (datos[position].getResult_int()){
                case constants.WIN_RESULT:
                    imageView.setImageResource(R.drawable.victoria);
                    break;
                case constants.LOSE_RESULT:
                    imageView.setImageResource(R.drawable.derrota);
                    break;
                case constants.TIMEOUT_RESULT:
                    imageView.setImageResource(R.drawable.tiempoagotado);
                    break;
                case constants.DRAW_RESULT:
                    imageView.setImageResource(R.drawable.empate);
                    break;
            }

            //TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
            //lblAsunto.setText(datos[position].getAsunto());

            return(item);
        }
    }





























    public interface PlayedGameListener {
        void onPlayedGameSeleccionado(PlayedGame c);
    }

    public void setPlayedGameListener(PlayedGameListener listener) {
        this.listener=listener;
    }
}