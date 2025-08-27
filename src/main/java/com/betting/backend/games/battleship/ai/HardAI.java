package com.betting.backend.games.battleship.ai;

import com.betting.backend.games.battleship.logic.*;
import java.util.*;

public class HardAI implements AiPlayer {
    private int test;
    private int[][] grid;
    private int boardSize;
    private Set<Coordinate> hits;
    private Set<Ship> remainingShips;
    private ShotType type;

    public HardAI(int boardSize) {
        this.boardSize = boardSize;
        this.grid = new int[boardSize][boardSize];
        this.hits = new HashSet<>();
        this.remainingShips = new HashSet<>();
        this.type = ShotType.HUNT;
        // For demo: add sample ships (in a real game, this would be dynamic)

    }

    @Override
    public Coordinate getNextMove(Set<Coordinate> alreadyFired) {
        resetGrid();

        for (Ship ship : remainingShips) {
            int length = ship.getlength();
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    List<Coordinate> horizontal = getShipCoordinates(row, col, length, true);
                    if (isValidPlacement(horizontal, alreadyFired)) {
                        incrementGrid(horizontal);
                    }
                    List<Coordinate> vertical = getShipCoordinates(row, col, length, false);
                    if (isValidPlacement(vertical, alreadyFired)) {
                        incrementGrid(vertical);
                    }
                }
            }
        }

        return selectBestMove(alreadyFired);
    }

    private void resetGrid() {
        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(grid[i], 0);
        }
    }

    private List<Coordinate> getShipCoordinates(int row, int col, int length, boolean horizontal) {
        List<Coordinate> coords = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            coords.add(new Coordinate(r, c));
        }
        return coords;
    }

    private boolean isValidPlacement(List<Coordinate> coords, Set<Coordinate> alreadyFired) {
        for (Coordinate c : coords) {
            if (c.getrow() < 0 || c.getrow() >= boardSize || c.getcol() < 0 || c.getcol() >= boardSize) {
                return false;
            }
            if (alreadyFired.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private void incrementGrid(List<Coordinate> coords) {
        for (Coordinate c : coords) {
            grid[c.getrow()][c.getcol()]++;
        }
    }

    private Coordinate selectBestMove(Set<Coordinate> alreadyFired) {
        Coordinate best = null;
        int max = -1;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Coordinate c = new Coordinate(row, col);
                if (!alreadyFired.contains(c) && grid[row][col] > max) {
                    max = grid[row][col];
                    best = c;
                }
            }
        }
        return best;
    }

    public void setLastShotResult(Coordinate coord, ShotResult result) {
        if (result == ShotResult.HIT) {
            hits.add(coord);
            type = ShotType.TARGET;
        } else if (result == ShotResult.SUNK) {
            hits.remove(coord);
            type = ShotType.HUNT;
            // For demo: you might remove a ship based on length, etc.
        }
    }
}