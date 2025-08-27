package com.betting.backend.games.battleship.ai;

import java.lang.annotation.Target;
import java.util.*;
import com.betting.backend.games.battleship.logic.*;;

public class MediumAI implements AiPlayer{
    Random rand = new Random();
    private int test;
    private ShotResult Shot;
    private Coordinate previousShot;
    private Coordinate nextShot;
    private List<Coordinate> allcoordinates;
    private ShotType shotType;
    private TargetDirection direction;
    public MediumAI(int boardsize){
        allcoordinates = new ArrayList<>();
        for (int i = 0;i<boardsize;i++){
            for (int j = 0;j<boardsize;j++){
                Coordinate coor = new Coordinate(i, j);
                allcoordinates.add(coor);
            }
        }
        shotType = ShotType.HUNT;
        direction = TargetDirection.UP;

    }
    public void setLastShotResult(ShotResult result){
        this.Shot = result;
    }
    public TargetDirection changeDir(TargetDirection dir){
        TargetDirection returndir = TargetDirection.UP;
        switch (dir){
            case UP:
            returndir = TargetDirection.DOWN;
            break;
            case DOWN:
            returndir = TargetDirection.LEFT;
            break;
            case LEFT:
            returndir = TargetDirection.RIGHT;
            break;
            case RIGHT:
            returndir = TargetDirection.UP;
            break;


        }
        return returndir;

    }


    @Override
    public Coordinate getNextMove(Set<Coordinate> alreadyFired){
        if(Shot == ShotResult.HIT){shotType = ShotType.TARGET;}
        if(Shot == ShotResult.MISS && shotType !=ShotType.HUNT){
            direction = changeDir(direction);

        }
        else if (Shot == ShotResult.SUNK){
            shotType = ShotType.HUNT;
        }
        if (shotType == ShotType.HUNT){
            Coordinate fired = HuntMove();
            previousShot = fired;
            return fired;
        }
        else{


            for (int i = 0; i < 4; i++) {
                Coordinate fired = TargetMove(previousShot, direction);
                if (checkbounderies(fired)&& allcoordinates.contains(fired)) {
                    allcoordinates.remove(fired);
                    nextShot = fired;
                    return fired;
                }
                direction = changeDir(direction);
            }
    shotType = ShotType.HUNT; // fallback if all directions failed
    return HuntMove();


        }


    }
    public Coordinate HuntMove(){
        if(allcoordinates.isEmpty()){
            return null;
        }
        int position = rand.nextInt(allcoordinates.size());
        Coordinate fire_on = allcoordinates.get(position);
        allcoordinates.remove(position);
        return fire_on;

    }
    public boolean checkbounderies(Coordinate c){
        return (c.getrow()>=0 && c.getrow()<10&&c.getcol()>=0&&c.getcol()<10);

    }
    public Coordinate TargetMove(Coordinate c,TargetDirection dir){
        if(allcoordinates.isEmpty()){
            return null;
        }
        Coordinate coor;
        switch (dir){
            case UP:
                coor = new Coordinate(c.getrow()+1,c.getcol());
                break;

            case DOWN:
                coor = new Coordinate(c.getrow()-1,c.getcol());
                break;

            case LEFT:
                coor = new Coordinate(c.getrow(),c.getcol()-1);
                break;

            case RIGHT:
                coor = new Coordinate(c.getrow(),c.getcol()+1);
                break;

            default:
                coor = c;
                break;

        }
        if(!checkbounderies(coor)){
            dir = changeDir(dir);
            return TargetMove(c, dir);
        }
        allcoordinates.remove(coor);

        return coor;




    }


}
