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
public class Battleship extends Ship{
    
    public Battleship(int col, int row, String direction){
        super(col,row,direction);
        lifePoints = 4;
        length = 4;
    }
    
    @Override 
    public void setShip(Board board){
        if(direction.equals("h1")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row);
                Point position = new Point(col+i,row);
                alFields.add(position);
            }
        }
        //simpler wäre es else zu benutzen, ich habe es aber so geschrieben damit man erkennt dass so das Schiff in vertikaler Form gemalt wird
        if(direction.equals("v1")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col, row+i);
                Point position = new Point(col,row+i);
                alFields.add(position);
            }
        }
        if(direction.equals("h2")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col+i, row);
                Point position = new Point(col+i,row);
                alFields.add(position);
            }
        }
        //simpler wäre es else zu benutzen, ich habe es aber so geschrieben damit man erkennt dass so das Schiff in vertikaler Form gemalt wird
        if(direction.equals("v2")){
            for (int i = 0; i < 4; i++) {
                board.setShip(col, row+i);
                Point position = new Point(col,row+i);
                alFields.add(position);
            }
        }
    }

    @Override
    public String getShipType() {
        return "S2";
    }
}
