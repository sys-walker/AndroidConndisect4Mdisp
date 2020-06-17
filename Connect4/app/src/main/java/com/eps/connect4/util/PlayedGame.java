package com.eps.connect4.util;

import com.eps.connect4.R;

public class PlayedGame
{
    public String player_alias;
    public int icon_id_drawable;
    private int board_size ;
    private String temps_total = "No temps";
    private boolean time_control;
    private String date;
    private int result_int; //status



    public PlayedGame(String player_name, int board_size, boolean time_control, String date, int result_int){
        this.player_alias = player_name;  // get from shared preferences
        this.board_size = board_size; // get from shared preferences
        this.time_control = time_control; // get from shared preferences
        this.date = date;  // get from result game and log
        this.result_int = result_int; //get from result game and log
        this.drawable_icon(this.result_int);

    }

    private void drawable_icon(int result_int) {
        switch (this.result_int){
            case constants.WIN_RESULT:
                this.icon_id_drawable = R.drawable.victoria;
                break;
            case constants.LOSE_RESULT:
                this.icon_id_drawable = R.drawable.derrota;
                break;
            case constants.TIMEOUT_RESULT:
                this.icon_id_drawable = R.drawable.tiempoagotado;
                break;
            case constants.DRAW_RESULT:
                this.icon_id_drawable = R.drawable.empate;
                break;
        }
    }

    public static int getResultInt(Status st) {
        if (st != null) {
            switch (st){
                case PLAYER1_WINS:
                    return 0;
                case PLAYER2_WINS:
                    return 1;
                case DRAW:
                    return 2;
                 case TIMEOUT:
                    return 3;
            }
        }
        return -1;
    }

    public String getResultGameString(){
        switch (this.result_int){
            case constants.WIN_RESULT:
                return "Has guanyat!!";
            case constants.LOSE_RESULT:
                return "Has perdut :c";
            case constants.TIMEOUT_RESULT:
                return "S'ha acabat el temps!";
            case constants.DRAW_RESULT:
                return "Empat!";
        }
        return "?";
    } //Per els exmples
    public static String getResultGameString(int result_int){
        switch (result_int){
            case constants.WIN_RESULT:
                return "Has guanyat!!";
            case constants.LOSE_RESULT:
                return "Has perdut :c";
            case constants.TIMEOUT_RESULT:
                return "S'ha acabat el temps!";
            case constants.DRAW_RESULT:
                return "Empat!";
        }
        return "?";
    } //Per els exmples
    public int getResult_int() {
        return result_int;
    }


    public String getPlayer_alias() {
        return player_alias;
    }

    public int getBoard_size() {
        return board_size;
    }

    public boolean getTime_control() {
        return time_control;
    }

    public String getDate() {
        return (date.equals(""))? "No hay fecha":date;
    }

    public static String getResultGameString(Status st){
        switch (st){
            case PLAYER1_WINS:
                return "Has guanyat!!";
            case PLAYER2_WINS:
                return "Has perdut :c";
            case TIMEOUT:
                return "S'ha acabat el temps!";
            case DRAW:
                return "Empat!";
        }
        return "?";
    }
}