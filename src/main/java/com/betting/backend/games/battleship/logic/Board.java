package com.battleship.logic;
import java.util.*;
public class Board {
    private List<Ship> ships;
    private Set<Coordinate> fired_coor = new HashSet<Coordinate>();
    public Board(List<Ship> ships){
        this.ships = ships;
    }
    public boolean allShipsSunk(){
        for(Ship ship:ships){
            if (!ship.is_sunk()){
                return false;

            }
        }
        return true;
    }
    public Set<Coordinate> Shots_fired(){
        return fired_coor;
    }
    public ShotResult fire_at(Coordinate c){
        if(fired_coor.contains(c)){
            return ShotResult.INVALID;
        }
        ShotResult shot = ShotResult.INVALID;
        for (int i = 0; i<ships.size();i++){
            if (ships.get(i).getCoordinate().contains(c)){
                ships.get(i).registerHit(c);

                shot =  ShotResult.HIT;
                if (ships.get(i).is_sunk()){
                    shot = ShotResult.SUNK;
                }
                break;


            }
            else{

                shot =  ShotResult.MISS;



            }
        }
        fired_coor.add(c);
        return shot;
    }

}
