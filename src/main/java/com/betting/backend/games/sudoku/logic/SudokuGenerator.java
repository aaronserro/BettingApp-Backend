package com.betting.backend.games.sudoku.logic;

import com.betting.backend.games.sudoku.model.SudokuBoard;

import java.util.Random;
/**
 *This class generates a puzzle that the solver will solve - it uses the solver to generate the puzzle
 */
public class SudokuGenerator {
    private final SudokuSolver solver = new SudokuSolver();//creates a solver object
    private final Random rng = new Random();//creates a random object

    /**
     * This is the main functions that returns a Board object that will be used in gameplay
     * @param holesTarget
     * @return
     */
    public SudokuBoard generate(int holesTarget) {

        SudokuBoard solved = new SudokuBoard();//created a new board object
        fillDiagonalBoxesRandom(solved);//fills the diagonal grid with random numbers
        //if the solver cannot solve the board with the random numbers than it throws an exception
        if (!solver.solve(solved)) throw new IllegalStateException("Failed to generate base solution");

        SudokuBoard puzzle = solved.copy();//copy the board into a puzzle that will be modified
        int holes = 0;//counter to see how many holes will be present in the puzzle
        int attempts = 81 * 4; // cap work - the amount of attempts the computer has per cell: 4 attempts
        while (holes < holesTarget && attempts-- > 0) {//loop that continues until there are a signifigant amount of holes covered
            int r = rng.nextInt(9), c = rng.nextInt(9);//generating a random row and column
            if (puzzle.getNum(r, c) == 0) continue;

            int backup = puzzle.getNum(r, c);//backup number
            puzzle.setNum(r, c, 0);//setting the cell to 0

            SudokuBoard copy = puzzle.copy();//creating a copy object
            int solCount = solver.countSolutions(copy, 2);//find solutions for the amount of solutions for the board
            if (solCount == 1) {
                holes++;
            } else {
                puzzle.setNum(r, c, backup); // revert, keep unique
            }
        }
        // mark givens as fixed
        boolean[][] fixed = new boolean[9][9];
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                fixed[r][c] = puzzle.getNum(r, c) != 0;
        puzzle.setFixedBoard(fixed);
        return puzzle;
    }
    /**
     * This function fills the board with random numbers along the main diagonal
     * @param b
     */
    private void fillDiagonalBoxesRandom(SudokuBoard b) {
        for (int box = 0; box < 3; box++) {
            int br = box * 3, bc = box * 3;
            int[] nums = rngShuffle1to9();
            int idx = 0;
            for (int r = br; r < br + 3; r++)
                for (int c = bc; c < bc + 3; c++)
                    b.setNum(r, c, nums[idx++]);
        }
    }
    //creates a random array of numbers that will be used in the array
    private int[] rngShuffle1to9() {
        int[] a = new int[9];
        for (int i = 0; i < 9; i++) a[i] = i + 1;
        for (int i = 8; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
        return a;
    }
}
