package Ships;

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
    public void setShip(){
        if(direction.equals("h")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row);
            }
            for (int i = 0; i < 3; i++) {
                board.setShip(col+i, row+1);
            }
        }
        //simpler wäre es else zu benutzen, ich habe es aber so geschrieben damit man erkennt dass so das Schiff in vertikaler Form gemalt wird
        if(direction.equals("v")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col, row+i);
            }
            for (int i = 0; i < 3; i++) {
                board.setShip(col+1, row+i);
            }
        }
    }
    
}
