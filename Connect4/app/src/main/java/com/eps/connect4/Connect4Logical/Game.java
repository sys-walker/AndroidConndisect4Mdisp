package com.eps.connect4.Connect4Logical;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.eps.connect4.util.PreferenciasJuego;
import com.eps.connect4.util.Status;

public class Game implements View.OnClickListener {
    public ImageAdapter imageAdapter;
    public int position;
    public Context context;
    public  int SIZE =PreferenciasJuego.prefs_connect4_size;
    public Board board;

    public Game(int position, Context context, ImageAdapter imageAdapter,Board board) {
        this.position = position;
        this.context = context;
        this.imageAdapter=imageAdapter;
        this.board =board;

    }

    public void onClick(View v) {
        int x = position / PreferenciasJuego.prefs_connect4_size;
        int y = position % PreferenciasJuego.prefs_connect4_size;

        //Acess  Matrix[N][N]       --->       Matrix[x][y]
        //Access Array[N*N]   -- as Matrix --> Array [#Files*x+y]

        boolean outBoard = true;

        if (board.gravityLayer.contains(position)) {
            outBoard=false;
        }
        else{
            //busca la ultima cela buida donanda una posicio
            int new_x = board.lastEmptyColumnCell(y);
            //Mira que la columna no estigui plena
            if (new_x !=-1 && !board.isFullColumn(y)){
                //reocnstruir position per trobar la ultima cela buida
                position = (PreferenciasJuego.prefs_connect4_size * new_x)+y;
                outBoard =false;
            }else {
                Toast.makeText(context, "The Column is Full", Toast.LENGTH_SHORT).show();
            }
        }

        if (!outBoard){
            doTheMovement(position);
            if (isFinished()){ imageAdapter.startMailSender();}
            else {
                position = playOpponent();
                doTheMovement(position);
                if (isFinished()) {
                    imageAdapter.startMailSender();
                }
            }
        }
    }
    public void doTheMovement(int position) {
        board.occupyCell(position);
        board.toggleTurn();
        board.getGravityLayer();
        update();
        imageAdapter.listener.onItemSelected(position, board);
    }
    public void update() {
        imageAdapter.updateView();
        imageAdapter.notifyDataSetChanged();

    }


    public int playOpponent() {
        int randomInt = SIZE*SIZE*SIZE;

        while (!board.gravityLayer.contains(randomInt)) {
            randomInt = (int) (Math.random() * (SIZE * SIZE));
        }
        return randomInt;
    }

    public boolean isFinished() {
        if (board.isFinisished(position)) {
            return true;
        } else {
            if(board.timeout){
                board.status  = Status.TIMEOUT;
            }
            return board.timeout;
        }
    }


}

