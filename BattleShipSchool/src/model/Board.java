package model;


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
public class Board {
    
    private int [][] board = new int [10][10];
    
    /*
        0: WASSER
        1: SCHIFF
        2: WASSER ABGESCHOSSEN
        3: SCHIFF ABGESCHOSSEN
    */
    
    public void initBoard(){
        //alle Felder sind WASSER
        for(int col = 0; col < 10; col++) {
            for(int row = 0; row < 10; row++) {
                board[col][row] = 0;
            }
        }
    }
    
    public void setShip(int col, int row){
        //Teil des Feldes ist von SCHIFF
        board[col][row] = 1;
    }
    
    public int getConditionOfField(int col, int row){
        /* Gibt Zustand des Feldes zurück:
            0: WASSER
            1: SCHIFF
            2: WASSER ABGESCHOSSEN
            3: SCHIFF ABGESCHOSSEN
        */
        return board[col][row];
    }
    
    public void shoot(int col, int row){
        //Schiesst das spezifische Feld ab, falls es noch unbeschossen ist. 
        //Wurde das Feld beschossen, wird es in einen Zustand gebracht, wo es nicht erneut beschossen werden kann.
        int condition = board[col][row];
        if(condition < 2){
            if(condition == 0){
                board[col][row] = 2;
            }
            else{
                board[col][row] = 3;
            }
        }
    }
    
    public void resetBoard(){
        //entfernt alle Schiffe, alle Felder gelten wieder als unbeschossen.
        initBoard();
    }
    
    public void draw(Graphics g, int squareSize, boolean player){
        //Malt das Spielbrett, jedes Feld mit der passenden Farbe
        if(player==true){
            for(int col = 0; col < 10; col++) {
                for(int row = 0; row < 10; row++) {
                    if(board[col][row]<3){
                        //wenn das Feld unbeschossen ist oder wenn doch, kein Schif darauf ist
                        g.setColor(Color.blue);
                    }
                    else{
                        //wenn das Feld beschossen wurde ist und ein Schif drauf ist
                        g.setColor(Color.red);
                    }
                    g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    g.setColor(Color.gray);
                    g.drawRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    if(board[col][row]==3){
                        //malt ein Kreuz uf das Feld, wenn es beschossen wurde und darauf kein Schiff ist
                        g.drawLine(col*squareSize, row*squareSize, squareSize, squareSize);
                        g.drawLine(col*squareSize, (row*squareSize)+squareSize, squareSize, row*squareSize);
                    }
                }
            }
        }
        else{
            for(int col = 0; col < 10; col++) {
                for(int row = 0; row < 10; row++) {
                    if(board[col][row] == 1){
                        //Wenn Player1 sein Schif da ist.
                        Color darkGray = new Color(128,136,151);
                        g.setColor(darkGray);
                    }
                    else if(board[col][row]<3){
                        //wenn auf dem Feld kein Schiff ist, nur Wasser
                        g.setColor(Color.blue);
                    }
                    else{
                        //Wenn es nicht beschossen wurde oder wenn auf dem beschossenen Feld kein Schiff ist
                        g.setColor(Color.red);
                    }
                    g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    g.setColor(Color.gray);
                    g.drawRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    if(board[col][row]==3){
                        g.drawLine(col*squareSize, row*squareSize, squareSize, squareSize);
                        g.drawLine(col*squareSize, (row*squareSize)+squareSize, squareSize, row*squareSize);
                    }
                }
            }
        }
    }

}


