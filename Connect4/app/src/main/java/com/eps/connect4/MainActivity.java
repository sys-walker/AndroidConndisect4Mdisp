package com.eps.connect4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.eps.connect4.UserInterface.AjustesActivity;
import com.eps.connect4.UserInterface.HelpActivity;
import com.eps.connect4.UserInterface.QueryActivity;
import com.eps.connect4.util.Connect4_Logger;
import com.eps.connect4.Connect4Logical.Connect4HostActivity;
import com.eps.connect4.R;
import com.eps.connect4.util.PreferenciasJuego;
import com.eps.connect4.util.UsuariosSQLiteHelper;
import com.eps.connect4.util.constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     public UsuariosSQLiteHelper usdbh;
     public Connect4_Logger log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        usdbh = new UsuariosSQLiteHelper(this);
        UsuariosSQLiteHelper.saveInstanceHelper(usdbh);

        log = new Connect4_Logger();
        Connect4_Logger.saveInstance(log);



        PreferenceManager.setDefaultValues(getBaseContext(),R.xml.preferencias,false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,this);


        Button btn_help = findViewById(R.id.button_help);
        Button btn_preamble = findViewById(R.id.goPreamble);
        Button btn_exit = findViewById(R.id.button_exit);
        Button btn_stats_results = findViewById(R.id.goBDD);

        btn_help.setOnClickListener( this);
        btn_preamble.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_stats_results.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        Intent in;
        switch ( v.getId()){
            case R.id.button_help:
                in= new Intent(this, HelpActivity.class);
                startActivity(in);
                finish();

                break;
            case R.id.goPreamble:
                in = new Intent(this, Connect4HostActivity.class);
                in.putExtra(constants.PREFS_ALIAS,PreferenciasJuego.prefs_user_game);
                in.putExtra(constants.PREFS_SIZE,PreferenciasJuego.prefs_connect4_size);
                in.putExtra(constants.PREFS_TIME_CTRL,PreferenciasJuego.prefs_time_ctrl_bool);
                startActivity(in);
                finish();
                break;
            case R.id.goBDD:
                //createDB(); crea exemles
                in = new Intent(this, QueryActivity.class);
                startActivity(in);
                finish();
                break;
            case R.id.button_exit:
                finish();
                break;
        }
    }

  /*  private void createDB() {
        Cursor data = usdbh.getListContents();
        if (data.getCount()==0){usdbh.create_samples();}
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent in = new Intent(this, AjustesActivity.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
