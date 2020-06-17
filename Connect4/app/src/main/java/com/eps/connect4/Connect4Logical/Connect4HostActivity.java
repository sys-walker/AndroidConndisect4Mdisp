package com.eps.connect4.Connect4Logical;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.eps.connect4.UserInterface.Fragments.Connect4GameFragment;
import com.eps.connect4.util.Connect4_Logger;
import com.eps.connect4.UserInterface.Fragments.LogFragment;
import com.eps.connect4.R;
import com.eps.connect4.util.PreferenciasJuego;


public class Connect4HostActivity extends AppCompatActivity implements Connect4GameFragment.Connect4Listener {
    Connect4_Logger log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_screen_game);

        getPreferencesConnect4();


        setContentView(R.layout.activity_connect4_host);
        Connect4GameFragment gameF = (Connect4GameFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentGameHost);

        gameF.setConnect4Listener(this);
        log = Connect4_Logger.getInstance(this);
    }

    private void getPreferencesConnect4() {
        PreferenceManager.setDefaultValues(getBaseContext(), R.xml.preferencias,false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        PreferenciasJuego.obtenerPreferencias(preferences,getBaseContext());
    }

    @Override
    public void onItemSelected(Integer position, Board board){

        //Per log i aquestes coses
        LogFragment gameLogsFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentLogHost);

        if(gameLogsFragment != null && gameLogsFragment.isInLayout()){
            LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentLogHost);

            logFragment.mostrarLogs(log.toString(board, position));
        }

    }
}
