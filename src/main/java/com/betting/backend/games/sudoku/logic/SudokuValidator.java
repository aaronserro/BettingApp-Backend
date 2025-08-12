package com.betting.backend.games.sudoku.logic;

import com.betting.backend.games.sudoku.model.SudokuBoard;
/**
 * This class contains all of the helped methods for the Solver and Generator.
 * It makes sure that the placements of numbers are valid within the range of 1-9 and at the end checks if the
 * puzzle is complete
 *
 */

public final class SudokuValidator {
    private SudokuValidator() {}

    public static boolean isInRange(int n) { return n >= 1 && n <= 9; }//Checks if a number n is within the range of 1-9

    public static boolean isPlacementValid(SudokuBoard board, int row, int col, int val) {
        /**
         * This method goes through the board and makes sure that the number in the row/col is correct for the row,
         * col and box
         *
         */
        if (!isInRange(val)) return false;
        // row
        for (int c = 0; c < 9; c++) if (c != col && board.getNum(row, c) == val) return false;
        // col
        for (int r = 0; r < 9; r++) if (r != row && board.getNum(r, col) == val) return false;
        // box
        int br = (row / 3) * 3, bc = (col / 3) * 3;
        for (int r = br; r < br + 3; r++)
            for (int c = bc; c < bc + 3; c++)
                if ((r != row || c != col) && board.getNum(r, c) == val) return false;
        return true;
    }

    /** Full board validity (zeros allowed, checks only filled cells) */
    public static boolean isBoardValid(SudokuBoard board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = board.getNum(r, c);
                if (val == 0) continue;
                if (!isPlacementValid(board, r, c, val)) return false;
            }
        }
        return true;
    }
    //checks if the board is correct and complete
    public static boolean isComplete(SudokuBoard board) {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (board.getNum(r, c) == 0) return false;
        return isBoardValid(board);
    }
}
