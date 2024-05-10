/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ships;

/**
 *
 * @author luc
 */
public class Battleship extends Ship{
    
    public Battleship(int col, int row, String direction){
        super(col,row,direction);
        lifePoints = 4;
        length = 4;
    }
    
    @Override 
    public void setShip(){
        if(direction.equals("h")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row);
            }
        }
        //simpler wäre es else zu benutzen, ich habe es aber so geschrieben damit man erkennt dass so das Schiff in vertikaler Form gemalt wird
        if(direction.equals("v")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col, row+i);
            }
        }
    }
}
