/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author luc
 */
public class OpponentBoard extends Board {
    
    
    
    
    @Override
    public void drawBoard(Graphics g, int squareSize){
        //Malt das Spielbrett, jedes Feld mit der passenden Farbe
        //Das Spielfeld wo man eigene Treffer sieht, Fehlschüsse, verfügbare Felder und wo man schiesst
            for(int col = 0; col < 10; col++) {
                for(int row = 0; row < 10; row++) {
                    if(board[col][row]<3){
                        //wenn das Feld unbeschossen ist oder wenn doch, kein Schif darauf ist
                        Color waterBlue= new Color(48,181,226);
                        g.setColor(waterBlue);
                    }
                    else if(board[col][row]==3){
                        //wenn das Feld beschossen wurde ist und ein Schif drauf ist
                        g.setColor(Color.red);
                    }
                    else if(board[col][row]==4){
                        Color aircraftcarrierSunk = new Color(30,60,110);
                        g.setColor(aircraftcarrierSunk);
                    }
                    else if(board[col][row]==5){
                        Color battleshipSunk = new Color(50,100,150);
                        g.setColor(battleshipSunk);
                    }
                    else if(board[col][row]==6){
                        Color submarineSunk = new Color(60,130,180);
                        g.setColor(submarineSunk);
                    }
                    else if(board[col][row]==7){
                        Color cruiserSunk = new Color(90,150,200);
                        g.setColor(cruiserSunk);
                    }
                    else if(board[col][row]==8){
                        Color destroyerSunk = new Color(70,140,180);
                        g.setColor(destroyerSunk);
                    }
                    g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    g.setColor(Color.gray);
                    g.drawRect(col*squareSize, row*squareSize, squareSize, squareSize);
                    //System.out.println("test");
                    if(board[col][row]==2){
                        //malt ein Kreuz uf das Feld, wenn es beschossen wurde und darauf kein Schiff ist
                        g.drawLine(col*squareSize, row*squareSize, col*squareSize+squareSize, row*squareSize+squareSize);
                        g.drawLine(col*squareSize, (row*squareSize)+squareSize, col*squareSize+squareSize, row*squareSize);
                    }
                }
            }
    }
}
