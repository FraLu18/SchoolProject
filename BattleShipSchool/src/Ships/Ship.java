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
public abstract class Ship {
    
    protected Board board; // Objekt von Board
    protected int lifePoints; // Trefferpunkte des Schiffes
    protected boolean isSunk = false; // Sagt aus ob Schiff gesunken ist oder nicht.
    protected int col;
    protected int row;
    protected int length = 0;
    protected String direction;
    
    public Ship(int col, int row, String direction){
        this.col = col;
        this.row = row;
        this.direction = direction;
    }
    
    public void hitted(){
        //Zieht Trefferpunkt ab (Methode wird aufgerufen wenn das Schiff getroffen wurde)
        lifePoints--;
    }
    
    public boolean isSunk(){
        //Gibt zurück ob das Schiff gesunken ist oider nicht 
        if(lifePoints == 0){
            isSunk = true;
        }
        else{
            isSunk = false;
        }
        return isSunk;
    }
    
    public int getLength(){
        return length;
    }
    
    public abstract void setShip();

}
