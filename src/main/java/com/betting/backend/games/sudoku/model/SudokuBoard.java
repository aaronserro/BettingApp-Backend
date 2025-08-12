package com.betting.backend.games.sudoku.model;

public class SudokuBoard {
    /**
     * This is a very simple class that initializes the board with 0's as well as a fixed board that will track whether the number is
     * created by the computer or not, along with the various getters and setters that go along with it
     *
     *
     */
    private int [][] board;//
    private boolean [][]fixedBoard;
public SudokuBoard(){
        /**
         * This class initalized the integer board as well as boolean board
         * both are set to 0 and False respectively
         *
         */
        board = new int[9][9];
        fixedBoard = new boolean[9][9];
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                board[i][j] = 0;
                fixedBoard[i][j] = false;
            }
        }
    }
    //getter for returning a number from a cell
public int getNum(int row, int col){
        return board[row][col];
    }
    //Setter for setting a certain cell to true
public void setFixed(int row,int col){
        fixedBoard[row][col] = true;
    }
    //getter to see whether a certain row is fixed with a number
public boolean isFixed(int row, int col){
        return fixedBoard[row][col];


    }
    //Setter for a number ina. cell
public void setNum(int row, int col, int num){
        board[row][col]=num;
    }
    //Getter to see whether a certain cel isl is empty
public boolean isEmpty(int row, int col){
        if(board[row][col] == 0){
            return true;

        }
        return false;

    }
    //getter to return the integer board
public int[][] getBoard(){
        return board;
    }//getter to return the fixed board
public boolean [][]getFixedBoard(){
        return fixedBoard;
    }//setting the board with a new 2D integer Array
public void setBoard(int[][] newBoard) {
    for (int i = 0; i < 9; i++) {
        System.arraycopy(newBoard[i], 0, board[i], 0, 9);
    }
}
//setting the board with a new 2D boolean array
public void setFixedBoard(boolean[][] newFixedBoard) {
    for (int i = 0; i < 9; i++) {
        System.arraycopy(newFixedBoard[i], 0, fixedBoard[i], 0, 9);
    }
}
//Copy method
public SudokuBoard copy() {
    SudokuBoard copy = new SudokuBoard();
    copy.setBoard(this.getBoard());
    copy.setFixedBoard(this.fixedBoard);
    return copy;
}
//Overriding the ToString method
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int[] row : board) {
        for (int num : row) sb.append(num).append(' ');
        sb.append('\n');
    }
    return sb.toString();
}




}
