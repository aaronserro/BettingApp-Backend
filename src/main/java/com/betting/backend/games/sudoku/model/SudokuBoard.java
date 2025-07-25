package com.betting.backend.games.sudoku.model;

public class SudokuBoard {
    /**
     * This is a very simple class that initializes the board with 0's as well as a fixed board that will track whether the number is
     * created by the computer or not, along with the various getters and setters that go along with it
     *
     *
     */
    private int [][] board;
    private boolean [][]fixedBoard;
    public SudokuBoard(){
        board = new int[9][9];
        fixedBoard = new boolean[9][9];
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                board[i][j] = 0;
                fixedBoard[i][j] = false;
            }
        }
    }
    public int getNum(int row, int col){
        return board[row][col];
    }
    public void setFixed(int row,int col){
        fixedBoard[row][col] = true;
    }
    public boolean isFixed(int row, int col){
        return fixedBoard[row][col];


    }
    public void setNum(int row, int col, int num){
        board[row][col]=num;
    }
    public boolean isEmpty(int row, int col){
        if(board[row][col] == 0){
            return true;

        }
        return false;

    }
    public int[][] getBoard(){
        return board;
    }

}
