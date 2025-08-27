package com.betting.backend.games.battleship.logic;

public class Coordinate {
    private int row;
    private int col;
    public Coordinate(int row, int col){
        this.row=row;
        this.col=col;

    }
    public int getrow(){
        return row;
    }
    public int getcol(){
        return col;
    }
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
