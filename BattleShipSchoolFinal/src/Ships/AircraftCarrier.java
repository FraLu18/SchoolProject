package Ships;

import java.awt.Point;
import model.Board;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luc
 */
public class AircraftCarrier extends Ship{
       
    public AircraftCarrier(int col, int row, String direction){
        super(col,row,direction);
        lifePoints = 7;
        length = 4;
    }
    
    @Override
    public String getShipType(){
        return "S1";
    }
    
    @Override 
    public void setShip(Board board){
        if(direction.equals("h1")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row);
                Point position = new Point(col+i,row);
                alFields.add(position);
            }
            for (int i = 0; i < 3; i++) {
                board.setShip(col+i, row+1);
                Point position = new Point(col+i,row+1);
                alFields.add(position);
            }
        }
        else if(direction.equals("v1")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col, row+i);
                Point position = new Point(col,row+i);
                alFields.add(position);
            }
            for (int i = 0; i < 4; i++) {
                board.setShip(col+1, row+i);
                Point position = new Point(col+1,row+i);
                alFields.add(position);
            }
        }
        else if(direction.equals("h2")){
            for (int i = 0; i < 3; i++) {
                board.setShip(col+i+1, row);
                Point position = new Point(col+i+1,row);
                alFields.add(position);
            }
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row+1);
                Point position = new Point(col+i,row+1);
                alFields.add(position);
            }
        }
        else if(direction.equals("v2")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col, row+i);
                Point position = new Point(col,row+i);
                alFields.add(position);
            }
            for (int i = 0; i < 3; i++) {
                board.setShip(col+1, row+i+1);
                Point position = new Point(col+1,row+i+1);
                alFields.add(position);
            }
        }
    }
    
}
