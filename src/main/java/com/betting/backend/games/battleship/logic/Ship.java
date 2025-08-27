package com.betting.backend.games.battleship.logic;

import java.util.List;

public class Ship {
    private int length;

    private List<Coordinate> coordinates;
    private List<Coordinate> hits;

    public Ship(int length, List<Coordinate> coordinates){
        this.length=length;
        this.coordinates=coordinates;

    }
    public int getlength(){return length;}
    //public int gethits(){return hits;}
    public List<Coordinate> getCoordinate(){
        return coordinates;
    }
    public List<Coordinate> getHits(){
        return hits;
    }
    public boolean is_sunk(){
        return hits.size() == coordinates.size();



    }
    public void registerHit(Coordinate c) {
        if (coordinates.contains(c) && !hits.contains(c)) {
            hits.add(c);
        }
    }


}
