package com.eps.connect4.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class PreferenciasJuego {

    public static String prefs_user_game;
    public static int prefs_connect4_size;
    public static boolean prefs_time_ctrl_bool;
    public static int prefs_connect4_countdown;

    public static void obtenerPreferencias(SharedPreferences preferences, Context context){

        //Nom d'usuari
        prefs_user_game = (preferences.getString(constants.PREFS_ALIAS, "Player 1").equals(""))? "Player 1":preferences.getString(constants.PREFS_ALIAS, "Player 1");
        //Mida Graellla
        switch (preferences.getString(constants.PREFS_SIZE,"7 x 7")){
            case "7 x 7":
                prefs_connect4_size=7;
                break;
            case "6 x 6":
                prefs_connect4_size =6;
                break;
            case "5 x 5":
                prefs_connect4_size =5;
                break;
            default:
                prefs_connect4_size = 7;
                break;
        }
        //Control de temps
        prefs_time_ctrl_bool = preferences.getBoolean(constants.PREFS_TIME_CTRL,false);



        //temps de joc
        switch (preferences.getString(constants.PREFS_TIMER,"*")){
            case "1 min":
                prefs_connect4_countdown=60;
                break;
            case "30 sec":
                prefs_connect4_countdown =30;
                break;
            case "20 sec":
                prefs_connect4_countdown =20;
                break;
            default:
                prefs_connect4_countdown = 120;
                break;
        }

        String s1 = "Alias      = " +prefs_user_game+"\nMida       = "+prefs_connect4_size+"\nCtrl temps = "+prefs_time_ctrl_bool+"\nTemps = "+prefs_connect4_countdown;
        Toast.makeText(context, s1,Toast.LENGTH_LONG).show();

    }

}
