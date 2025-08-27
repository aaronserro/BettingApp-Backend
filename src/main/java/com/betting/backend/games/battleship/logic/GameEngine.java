package com.betting.backend.games.battleship.logic;
import java.util.*;
public class GameEngine{
    private Board Player;
    private Board Computer;
    private Turn current_turn;
    public void startGame(List<Ship> ships, List<Ship> Compships){
        this.Player = new Board(ships);
        this.Computer = new Board(Compships);
        current_turn =Turn.PLAYER;



    }
    public ShotResult Playermove(Coordinate c){
        if (current_turn != Turn.PLAYER) return ShotResult.INVALID;
        ShotResult result = ShotResult.INVALID;
        result = Computer.fire_at(c);
        return result;



    }
    //public ShotResult ComputerMove(){}//To Be Done using some Logic...IDK yet
    public void changeTurn(){
        if (current_turn == Turn.PLAYER){
            current_turn = Turn.COMPUTER;
        }
        else{
            current_turn = Turn.PLAYER;
        }
    }
    public Turn getTurn(){
        return current_turn;
    }
    public boolean isGameOver() {
        return Player.allShipsSunk() || Computer.allShipsSunk();
    }

    public Turn getWinner() {
        if (Player.allShipsSunk()) return Turn.COMPUTER;
        if (Computer.allShipsSunk()) return Turn.PLAYER;
        return Turn.NONE; // optional if game is still ongoing
    }








}