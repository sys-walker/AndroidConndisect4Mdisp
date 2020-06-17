package com.eps.connect4.util;

import android.os.CountDownTimer;

import com.eps.connect4.Connect4Logical.Board;


public class Connect4StopWatch extends CountDownTimer {

    private long timeToFinish;
    private Board board;

    public Connect4StopWatch(Board board, int time) {
        super(time*1000, 1000);
        this.board = board;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeToFinish = millisUntilFinished;
    }

    @Override
    public void onFinish() {
        board.timeout = true;
    }

    public long getTime(){
        return timeToFinish;
    }
}
