package com.betting.backend.games.sudoku.logic;

import com.betting.backend.games.sudoku.model.SudokuBoard;
    /**
     * This class solves a generic Sudoku Puzzle
     *
     *
     * @param board
     * @return
     */

public class SudokuSolver {

    /**
     *This function is the main method that solves an empty puzzle
     * @param board
     * @return
     */
    public boolean solve(SudokuBoard board) {

        int[] cell = findEmpty(board);//stores an array full of cells that are empty from the board
        //Classic backtracking Algo
        if (cell == null) return true; // solved
        int row = cell[0], col = cell[1];

        for (int val = 1; val <= 9; val++) {
            if (SudokuValidator.isPlacementValid(board, row, col, val)) {
                board.setNum(row, col, val);
                if (solve(board)) return true;
                board.setNum(row, col, 0);
            }
        }
        return false;
    }

    /** counts up to 2 solutions (for uniqueness checks) */
    public int countSolutions(SudokuBoard board, int limit) {
        int[] cell = findEmpty(board);
        if (cell == null) return 1;
        int row = cell[0], col = cell[1];
        int count = 0;
        for (int val = 1; val <= 9 && count < limit; val++) {
            if (SudokuValidator.isPlacementValid(board, row, col, val)) {
                board.setNum(row, col, val);
                count += countSolutions(board, limit - count);
                board.setNum(row, col, 0);
            }
        }
        return count;
    }

    private int[] findEmpty(SudokuBoard b) {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (b.getNum(r, c) == 0) return new int[]{r, c};
        return null;
    }
}
