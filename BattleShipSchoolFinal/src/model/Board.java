package model;


import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Submarine;
import java.awt.Color;
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luc
 */
public abstract class Board {
    
    protected int [][] board = new int [10][10];
    protected int numOfShips = 0;
    final int WATER = 0;
    final int SHIP = 1;
    final int WATERHIT = 2;
    final int SHIPHIT = 3;
    
    /*
        0: WASSER
        1: SCHIFF
        2: WASSER ABGESCHOSSEN
        3: SCHIFF ABGESCHOSSEN
        4: AircraftCarrier versunken 
        5: Battleship versunken
        6: Submarine versunken
        7: Cruiser versunken
        8: Destroyer versunken
    */
    
    public Board(){
        initBoard();
    }
    
    public void initBoard(){
        //alle Felder sind WASSER
        for(int col = 0; col < 10; col++) {
            for(int row = 0; row < 10; row++) {
                board[col][row] = WATER;
            }
        }
    }
    
    public void setShip(int col, int row){
        //Teil des Feldes ist von SCHIFF
        //System.out.println(col + " , " +  row);
        board[col][row] = SHIP;
    }
    
    public int getConditionOfField(int col, int row){
        /* Gibt Zustand des Feldes zurück:
            0: WASSER
            1: SCHIFF
            2: WASSER ABGESCHOSSEN
            3: SCHIFF ABGESCHOSSEN
            4: SCHIFF VERSUNKEN
        */
        return board[col][row];
    }
    
    public void shoot(int col, int row){
        //Schiesst das spezifische Feld ab, falls es noch unbeschossen ist. 
        //Wurde das Feld beschossen, wird es in einen Zustand gebracht, wo es nicht erneut beschossen werden kann.
        int condition = board[col][row];
        if(condition < WATERHIT){
            if(condition == WATER){
                board[col][row] = WATERHIT;
                System.out.println("No matches!");
            }
            else{
                System.out.println("Hit!");
                board[col][row] = SHIPHIT;
            }
        }
        else{
            System.out.println("Already shooted!");
        }
    }
    
    public void sunk(int col, int row, String shipType){
        board[col][row] = 4;
        if(shipType.equals("S1")){
            board[col][row] = 4;
        }
        if(shipType.equals("S2")){
            board[col][row] = 5;
        }
        if(shipType.equals("S3")){
            board[col][row] = 6;
        }
        if(shipType.equals("S4")){
            board[col][row] = 7;
        }
        if(shipType.equals("S5")){
            board[col][row] = 8;
        }
    }
    
    public boolean isPossibleToSetShip(int col, int row, int lengthOfShip, int heightOfShip, String direction){
        /*
            Length and Height of all Ship in horizontal position: 
        
            1: AircraftCarrier, Length: 4, Height: 2
            2: Battleship, Length: 4, Height: 1
            3: Submarine, Length: 3, Height: 2
            4: Cruiser, Length: 3, Height: 1
            5: Destroyer, Length: 2, Height: 1
        */
            if(col >= 0 &&  row >= 0 && col+lengthOfShip-1 <= 9 && row+heightOfShip-1<= 9) {
                for (int checkRow = 0; checkRow < heightOfShip; checkRow++) { 
                    for (int checkCol = 0; checkCol < lengthOfShip; checkCol++) {
                        if(getConditionOfField(col+checkCol, row+checkRow)==1){
                            return false;
                        }
                    }
                }
            }
            else{
                return false;
            }
        
        return true;
    }
    
    
    
    public void resetBoard(){
        //entfernt alle Schiffe, alle Felder gelten wieder als unbeschossen.
        initBoard();
    }
    
    
    public abstract void drawBoard(Graphics g, int squareSize);
    

}


