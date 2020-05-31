package com.eps.androidconnect4.gameIntern;

public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Position move(Direction direction) {
        return new Position(this.row + direction.getChangeInRow(), this.column + direction.getChangeInColumn());
    }

    public boolean isEqualTo(Position other) {
        return (other.getColumn() == this.column && other.getRow() == this.row);
    }

    public static int pathLength(Position pos1, Position pos2) {
        // pos1 aligned with pos2 horizontally, vertically or diagonally
        if (pos1.getRow() == pos1.getRow() || pos2.getRow() == pos2.getRow()) {
            return Math.max(Math.abs(pos1.row - pos2.row), Math.abs(pos1.column - pos2.column)) + 1;
        } else {
            return Math.abs(pos1.column - pos2.column) + 1;
        }
    }
}