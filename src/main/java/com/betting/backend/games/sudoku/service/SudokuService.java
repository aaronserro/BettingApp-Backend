package com.betting.backend.games.sudoku.service;

import com.betting.backend.games.sudoku.logic.SudokuGenerator;
import com.betting.backend.games.sudoku.logic.SudokuSolver;
import com.betting.backend.games.sudoku.logic.SudokuValidator;
import com.betting.backend.games.sudoku.model.SudokuBoard;

public class SudokuService {
    private final SudokuGenerator generator = new SudokuGenerator();
    private final SudokuSolver solver = new SudokuSolver();

    public SudokuBoard newPuzzle(int holes) {
        return generator.generate(holes);
    }

    public boolean validateBoard(SudokuBoard board) {
        return SudokuValidator.isBoardValid(board);
    }

    public boolean solve(SudokuBoard board) {
        return solver.solve(board);
    }

    public boolean isComplete(SudokuBoard board) {
        return SudokuValidator.isComplete(board);
    }

    public boolean tryMove(SudokuBoard board, int row, int col, int val) {
        if (board.isFixed(row, col)) return false;
        if (val == 0) { board.setNum(row, col, 0); return true; }
        if (!SudokuValidator.isPlacementValid(board, row, col, val)) return false;
        board.setNum(row, col, val);
        return true;
    }
}
