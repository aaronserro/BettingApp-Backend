package com.betting.backend.games.battleship.ai;

import com.betting.backend.games.battleship.logic.*;
import java.util.*;

public abstract class BaseAI implements AiPlayer {
    protected int[][] board = new int[10][10];
    protected List<Ship> ships = new ArrayList<>();
/*
    @Override
    public void setupShips() {
        // Example logic (you'll implement this properly)
        placeShip(3);
        placeShip(4);
        placeShip(5);
    }

    protected void placeShip(int length) {
        Random rand = new Random();
        boolean placed = false;

        while (!placed) {
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);
            boolean horizontal = rand.nextBoolean();

            List<Coordinate> coords = new ArrayList<>();
            boolean fits = true;

            for (int i = 0; i < length; i++) {
                int r = row + (horizontal ? 0 : i);
                int c = col + (horizontal ? i : 0);
                if (r >= 10 || c >= 10 || board[r][c] == 1) {
                    fits = false;
                    break;
                }
                coords.add(new Coordinate(r, c));
            }

            if (fits) {
                for (Coordinate coord : coords) {
                    board[coord.getrow()][coord.getcol()] = 1;
                }
                ships.add(new Ship(length, coords));
                placed = true;
            }
        }
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }
        */
}
