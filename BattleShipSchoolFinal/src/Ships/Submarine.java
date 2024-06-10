/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ships;

import java.awt.Point;
import model.Board;

/**
 *
 * @author luc
 */
public class Submarine extends Ship{
    
    public Submarine
        (int col, int row, String direction){
        super(col,row,direction);
        lifePoints = 4;
        length = 3;
    }
    
    @Override 
    public void setShip(Board board){
        if(direction.equals("h1")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col+i, row);
                Point position = new Point(col+i,row);
                alFields.add(position);
            }
            board.setShip(col+1, row+1);
            Point position = new Point(col+1,row+1);
            alFields.add(position);
        }
        //simpler wäre es else zu benutzen, ich habe es aber so geschrieben damit man erkennt dass so das Schiff in vertikaler Form gemalt wird
        if(direction.equals("v1")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col+1, row+i);
                Point position = new Point(col+1,row+i);
                alFields.add(position);
            }
            board.setShip(col+1, row+1);
            Point position = new Point(col,row+1);
            alFields.add(position);
        }
        if(direction.equals("h2")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col+i, row+1);
                Point position = new Point(col+i,row+1);
                alFields.add(position);
            }
            board.setShip(col+1, row);
            Point position = new Point(col+1,row);
            alFields.add(position);
        }
        if(direction.equals("v2")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col, row+i);
                Point position = new Point(col+i,row);
                alFields.add(position);
            }
            board.setShip(col+1, row+1);
            Point position = new Point(col+1,row+1);
            alFields.add(position);
        }
    }
    
    @Override
    public String getShipType() {
        return "S3";
    }
}
