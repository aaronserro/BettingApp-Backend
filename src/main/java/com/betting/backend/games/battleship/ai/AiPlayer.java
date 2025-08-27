package com.betting.backend.games.battleship.ai;

import java.util.List;
import java.util.Set;

import com.betting.backend.games.battleship.logic.*;
public interface AiPlayer {
    Coordinate getNextMove(Set<Coordinate> alreadyFired);
     //void setupShips(); // ← New method for ship placement
    //List<Ship> getShips(); // ← Optional: to retrieve ships after placement


}


