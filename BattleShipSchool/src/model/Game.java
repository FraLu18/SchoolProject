package model;

import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luc
 */
public class Game {
    
    private Board boardPlayer;
    private Board boardBot;
    private boolean turn; //true: Mensch am Zug, false: Computer am Zug
    
    public Game(){
        
    }
    
    public void switchTurn(){
        if(turn == true){
            turn = false;
        }
        else{
            turn = true;
        }
    }
    
    public boolean isPossibleToSetShip(int col, int row, String direction, int lengthOfShip, int heigthOfShip){
        /*
            Length and Height of all Ship in horizontal position: 
        
            1: AircraftCarrier, Length: 4, Height: 2
            2: Battleship, Length: 4, Height: 1
            3: Submarine, Length: 3, Height: 2
            4: Cruiser, Length: 3, Height: 1
            5: Destroyer, Length: 2, Height: 1
        */
        boolean isPossible = false;
        boolean stop = false;
        if(col >= 0 &&  row >= 0 && col+lengthOfShip < 10) {
            for (int checkCol = 0; checkCol < lengthOfShip; checkCol++) {
                for (int checkRow = 0; checkRow < heigthOfShip; checkRow++) {
                    if(boardPlayer.getConditionOfField(col+checkCol, row+checkRow)==1){    
                        if(stop == false){
                            isPossible = true;
                        }
                    }
                    else{
                        isPossible = false;
                        stop = true;
                    }
                }
            }
        }
        return isPossible;
    }
    
    public void setShip(int col, int row, String direction, int lengthOfShip, int heigthOfShip){
        if(isPossibleToSetShip(col,row,direction,lengthOfShip,heigthOfShip)==true){
            boardPlayer.setShip(col, row);
        }
        else{
            System.out.println("WARNING: INVALID INPUT PLS SET YOUR SHIP ON ANOTHER PLACE !");
        }
    }
    
    public void gameOver(){
        
    }
    
    public void restart(){
        
    }
    
}
