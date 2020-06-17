package com.eps.connect4.Connect4Logical;

import android.os.Parcel;
import android.os.Parcelable;

import com.eps.connect4.util.Connect4StopWatch;
import com.eps.connect4.util.Status;
import com.eps.connect4.util.constants;

import java.util.ArrayList;
import java.util.List;

public class Board implements Parcelable {
    private int size;
    public int[][] cells;
    public List<Integer> gridviewCells;
    public List<Integer> gravityLayer = new ArrayList<>();
    public Status status = Status.PLAYER1_PLAYS;
    public int userCount=0;
    public int opponentCount=0;
    public long elapsed_time;
    public boolean timeout = false;
    public Connect4StopWatch stopwatch;

    public int getTime() {
        return (int) stopwatch.getTime();
    }




    public Board(int size,boolean time_ctl, int time) {
        this.size = size;
        this.cells = new int[size][size];
        this.gridviewCells = new ArrayList<>();
        this.syncGridview_and_cells(time_ctl,time);

    }

    public void toggleTurn() {
        this.status =  (status.equals(Status.PLAYER1_PLAYS)) ? Status.PLAYER2_PLAYS : Status.PLAYER1_PLAYS;
    }

    private void syncGridview_and_cells(boolean countdown, int time) {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.cells[x][y] = 0;
                this.gridviewCells.add(0);
            }
        }
        getGravityLayer();
        if (countdown) {
            stopwatch = new Connect4StopWatch(this,time);
            stopwatch.start();
        } else {
            this.elapsed_time = System.currentTimeMillis() /1000;
        }
    }

    public void getGravityLayer() {
        //Acess  Matrix[N][N]       --->       Matrix[row][colum]
        //Access Array[N*N]   -- as Matrix --> Array [#Files*row+colum]

        /*

        [ ][ ][ ][ ]
        [ ][*][ ][*]
        [*][2][*][1]  ---> [*][*][*][*] gravity Layer
        [1][2][1][1]


        */

        gravityLayer.clear();
        for (int column = 0; column < size; column++) {
            int row = lastEmptyColumnCell(column);
            int pos_adapter = (size * row)+column;
            gravityLayer.add(pos_adapter);
        }
       // System.out.println("Adapter  "+Arrays.toString(gravityLayer.toArray()));
    }

    public int lastEmptyColumnCell(int column){
        int it;
        for (it = 0; it < size ; it++) {
            //System.out.println("position("+(it)+","+(column)+")"+cells[it][column]);

            if (cells[it][column]!= 0){
               // System.out.println("returned("+(it-1)+","+(column)+")"+cells[it][column]);
                return (it - 1);
            }
        }
        return (it == size&& cells[it-1][column]== 0 ) ? (it - 1):-1;


    }
    public boolean isFullColumn(int y) {
        //si esta plena la primera sempre hi haura una fitxa
        //es te en compte que no hi han espais buis
        //     SI      IMPOSSIBLE
        //   colum 1     colum 1
        //      1          1
        //      1          0
        //      2          1
        //      2          1
        //      2          1

        return cells[0][y] !=0;
    }
    public boolean hasValidMoves(){
        return false;
    }


    public boolean isFinisished(int position) {
        int draw_check = size*size -(userCount+opponentCount);
        if(draw_check == 0){
            status= Status.DRAW;
            return true;
        }
        if(maxConnected(position)){
            if (status.equals(Status.PLAYER2_PLAYS)){
                status= Status.PLAYER1_WINS;
            }else{
                status = Status.PLAYER2_WINS;
            }
        }
        return maxConnected(position);
    }

    private boolean maxConnected(int position) {
        int a = connectedVertical(position);
        int b = connectedHoritzontal(position);
        int c = connectedMainDiagonal(position);
        int d = connectedContraDiagonal(position);
        return a == constants.TO_WIN ||b == constants.TO_WIN ||c == constants.TO_WIN ||d == constants.TO_WIN  ;
    }
    private int connectedVertical (int position){
        int connected = 1;
        int x = position/size;
        int y = position%size;
        for (int i = 0; i<constants.TO_WIN; i++){
            if ((x != size-1)){
                if (cells[x][y] == cells[x+1][y]){
                    connected ++;
                    x++;
                }
            }
        }
        return connected;
    }
    private int connectedHoritzontal (int position) {
        int connected = 1;
        int x = position / size;
        for (int y = 0; y < size - 1; y++) {
            if (cells[x][y] == cells[x][y + 1] && cells[x][y + 1] != 0) {
                connected++;
            }else {
                if (connected ==  constants.TO_WIN){
                    return connected;
                }
                connected=1;
            }
        }
        return connected;
    }
    private int connectedMainDiagonal (int position){
        int connected = 1;
        int x = position/size;
        int y = position%size;
        if((position - ((size+1)*y)<0)) {
            position = position - ((size + 1) * x);
            x = position / size;
            y = position % size;
        }else{
            position = position - ((size + 1) * y);
            x = position / size;
            y = position % size;
        }

        for (int i=x, j=y; i<size-1 && j<size-1; i++, j++){
            if (cells[i][j] == cells[i+1][j+1] && cells[i+1][j+1] != 0) {
                connected++;
            }else {
                if (connected ==  constants.TO_WIN){
                    return connected;
                }
                connected=1;
            }
        }
        return connected;
    }
    private int connectedContraDiagonal (int position){
        int connected = 1;
        int x = position/size;
        int y = position%size;
        if((position + ((size-1)*y)>size*size-1)) {
            position = position +(size-1)*((size-x-1));
            x = position / size;
            y = position % size;
        }else{
            if(x!=4) {
                position = position + ((size - 1) * y);
            }
            x = position / size;
            y = position % size;
        }

        for (int i=x, j=y; i>0 && j<size-1; i--, j++) {
            if (y != size - 1) {
                if (cells[i][j] == cells[i - 1][j + 1] && cells[i - 1][j + 1] != 0) {
                    connected++;
                }else {
                    if (connected ==  constants.TO_WIN){
                        return connected;
                    }
                    connected=1;
                }
            }
        }
        return connected;
    }
    public void occupyCell(int position) {
        if (this.status.equals(Status.PLAYER1_PLAYS)) {
            gridviewCells.set(position,1);
            userCount = userCount+1;
            cells[position / size][position % size] = 1;//PLAYER
        } else {
            gridviewCells.set(position,2);
            opponentCount = opponentCount+1;
            cells[position / size][position % size] = 2;
        }
    }






    //Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(cells);
        parcel.writeInt(size);
        parcel.writeValue(status);
       parcel.writeList(gridviewCells);
    }
    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };
    public Board(Parcel in) {
        size = in.readInt();
        status = (Status) in.readValue(Status.class.getClassLoader());
    }










}
