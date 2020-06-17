package com.eps.connect4.Connect4Logical;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.eps.connect4.UserInterface.MailSender;
import com.eps.connect4.R;
import com.eps.connect4.util.PreferenciasJuego;
import com.eps.connect4.util.Status;
import com.eps.connect4.util.constants;
import com.eps.connect4.UserInterface.Fragments.Connect4GameFragment.*;


public class ImageAdapter extends BaseAdapter {

    public Activity mContext;
    public Board board;
    public boolean time_ctrl;
    public TextView time_view;
    public TextView status_view;
    public int SIZE = PreferenciasJuego.prefs_connect4_size;
    public String alias;
    public Connect4Listener listener;

    public ImageAdapter(Activity c, Board board, TextView turn, TextView time,
                        Connect4Listener listener) {
        this.mContext = c;
        this.board = board;
        this.alias = PreferenciasJuego.prefs_user_game;
        this.time_ctrl = PreferenciasJuego.prefs_time_ctrl_bool;
        this.status_view = turn;
        this.time_view = time;
        this.listener = listener;
    }

    public void updateTime() {
        if (time_ctrl) {
            //countdown
            time_view.setText(String.valueOf(board.getTime() / 1000));
            time_view.setTextColor(Color.BLACK);
        } else {
            //stowatch
            time_view.setText(String.valueOf((System.currentTimeMillis() / 1000) -
                    board.elapsed_time));
            time_view.setTextColor(Color.BLACK);
        }

    }
    public void updateView() {
       if (board.status.equals(Status.PLAYER1_PLAYS)) {
            this.status_view.setText(board.status.name());
            this.status_view.setTextColor(Color.BLACK);
            this.status_view.setBackgroundColor(Color.YELLOW);
       } else {
           this.status_view.setText(board.status.name());
           this.status_view.setTextColor(Color.WHITE);
           this.status_view.setBackgroundColor(Color.RED);
       }
       updateTime();
    }


    public void startMailSender() {
        int time_lapse;
        if (time_ctrl) {
            time_lapse = (board.timeout) ? 0: PreferenciasJuego.prefs_connect4_countdown-(board.getTime() / 1000);

        } else {
            time_lapse = (int) ((System.currentTimeMillis() / 1000) - board.elapsed_time);
        }


        Intent intent = new Intent(mContext, MailSender.class);
        intent.putExtra(constants.TIME_LAPSE, time_lapse);
        System.out.println(time_lapse);
        intent.putExtra(constants.GAME_STATUS, board.status);
        mContext.startActivity(intent);
        mContext.finish();
        //LogCreator.deleteLog();
    }

    @Override
    public int getCount() {
        return SIZE * SIZE;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button token = (convertView != null) ? (Button) convertView :new Button(mContext);
        token.setBackgroundResource(setTokenDrawable(position));
        token.setOnClickListener(new Game(position, mContext, this,board));
        token.setId(position);
        return token;
    }

    public int setTokenDrawable(int position) {
        if (board.gridviewCells.get(position)==1){
            //System.out.println("POSAR FITXA JUGADOR 1");
            return R.drawable.fitxa_vermell;
        }else if (board.gridviewCells.get(position)==2){
           // System.out.println("POSAR FITXA JUGADOR 2");
            return R.drawable.fitxa_groga;
        }else{
            //System.out.println("POSAR FITXA BUIDA");
            return R.drawable.fitxa_buida;
        }

    }



}
    

