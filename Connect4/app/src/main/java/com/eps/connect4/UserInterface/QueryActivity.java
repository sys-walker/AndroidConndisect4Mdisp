package com.eps.connect4.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eps.connect4.MainActivity;
import com.eps.connect4.util.PlayedGame;
import com.eps.connect4.R;
import com.eps.connect4.UserInterface.Fragments.FragmentDetalle;
import com.eps.connect4.UserInterface.Fragments.FragmentListado;
import com.eps.connect4.util.constants;

public class QueryActivity extends AppCompatActivity implements FragmentListado.PlayedGameListener, View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        setTitle(R.string.title_screen_query);


        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);
        FragmentListado frgListado
                =(FragmentListado)getSupportFragmentManager()
                .findFragmentById(R.id.FrgListado);

        assert frgListado != null;
        frgListado.setPlayedGameListener(this);

    }



    @Override
    public void onPlayedGameSeleccionado(PlayedGame c) {
        FragmentDetalle fgdet = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());

        if (hayDetalle) {
            fgdet.mostrarDetalle(c.getPlayer_alias(),c.getDate(),c.getBoard_size(),c.getTime_control(),c.getResult_int(),c.icon_id_drawable);
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(constants.C4_USER,c.getPlayer_alias());
            i.putExtra(constants.C4_DATE, c.getDate());
            i.putExtra(constants.C4_SIZE, c.getBoard_size());
            i.putExtra(constants.C4_TIME_CTRL, c.getTime_control());
            i.putExtra(constants.C4_RESULT, c.getResult_int());
            i.putExtra(constants.C4_RESULT_ICON, c.icon_id_drawable);

            startActivity(i);
        }

    }


    @Override
    public void onClick(View v) {

        switch ( v.getId()){
            case R.id.button:
                Intent in= new Intent(this, MainActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
