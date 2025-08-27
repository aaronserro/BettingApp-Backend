package com.betting.backend.games.battleship.ai;
import com.betting.backend.games.battleship.logic.Coordinate;
import java.util.*;

public class EasyAI implements AiPlayer{
    Random rand = new Random();
    private List<Coordinate> allcoordinates;
    public EasyAI(int boardsize){
        allcoordinates = new ArrayList<>();
        for (int i = 0;i<boardsize;i++){
            for (int j = 0;j<boardsize;j++){
                Coordinate coor = new Coordinate(i, j);
                allcoordinates.add(coor);
            }
        }
    }
    @Override
    public Coordinate getNextMove(Set<Coordinate> alreadyFired){
        if(allcoordinates.isEmpty()){
            return null;
        }
        int position = rand.nextInt(allcoordinates.size());
        Coordinate fire_on = allcoordinates.get(position);
        allcoordinates.remove(position);
        return fire_on;


    }


}
