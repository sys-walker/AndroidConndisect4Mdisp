package com.eps.connect4.util;

import androidx.appcompat.app.AppCompatActivity;


import com.eps.connect4.Connect4Logical.Board;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Connect4_Logger implements Serializable {
    public static Connect4_Logger INSTANCE;
    public AppCompatActivity mContext;

    public Connect4_Logger() {
        this.mContext =null;
    }
    public static void saveInstance(Connect4_Logger logger) {
        INSTANCE = logger;
    }


    public static Connect4_Logger getInstance(AppCompatActivity mContext) {
        INSTANCE.mContext = mContext;
        return INSTANCE;
    }

    public String toString(Board board, int position) {
        String record = "";
        if(board.status.equals(Status.PLAYER2_PLAYS)){
            record += board.status.name() + "\n";
        } else{
            record += board.status.name()  + "\n";
        }


        int x = position/ PreferenciasJuego.prefs_connect4_size;
        int y = position% PreferenciasJuego.prefs_connect4_size;
        if (!board.gravityLayer.contains(position)){
            record += "Position selected ("+x+","+y+") but not found in gravity layer\n";
            int new_x = board.lastEmptyColumnCell(y);
            if (new_x !=-1 && !board.isFullColumn(y)){
                record += "Found free position in "+new_x+"!\n";
                record += "Token will be put at ("+new_x+","+y+")\n";
            }else {
                record += "The column "+y+" is full,No token where put\n";
            }

        }else {
            record += "Position selected ("+x+","+y+"), the token is put\n";
        }

        record+= "Time start: " + (new SimpleDateFormat("hh:mm:ss").format(new Date())) + "seconds;  Time finish: " + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "\n";


        if (PreferenciasJuego.prefs_time_ctrl_bool) {
            record += "Remaining time: " + String.valueOf(board.getTime() / 1000) +" secs. \n";
        }else {
            record += "Elapsed time: " + String.valueOf(board.getTime()/ 1000) +" secs. \n";
        }


        return record;
    }
}
