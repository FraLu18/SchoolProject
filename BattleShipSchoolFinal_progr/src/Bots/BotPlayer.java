/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bots;

import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Ship;
import Ships.Submarine;
import java.awt.Graphics;
import java.util.ArrayList;
import model.Board;
import model.OpponentBoard;
import model.OwnBoard;

/**
 *
 * @author FraLu999
 */
public abstract class BotPlayer {
    
    protected OwnBoard ownBoard;
    protected OpponentBoard opponentBoard;
    
    protected ArrayList<Ship> alShips = new ArrayList<>();
    
    protected String dir="h1";
    protected int shipLength  = 0;
    protected int shipHeight = 0;
    
    protected int shootCol;
    protected int shootRow;
    
    public BotPlayer(OwnBoard ownBoard, OpponentBoard opponentBoard){
        this.ownBoard = ownBoard;
        this.opponentBoard = opponentBoard;
        setShips();
    }
    
    protected int randomNum(int min, int max){
        return (int)((Math.random() * (max - min)) + min); 
    }
    
    public ArrayList<Ship> getShipList(){
        return alShips;
    }
    
    private void setShipStates(){
        //setzt direktion zufallig
        //passt Lange und Hohe des Schiffes an
        int randomNum = randomNum(1,4);
        if(randomNum==1){
            dir = "h1";
        }
        else if(randomNum==2){
            dir = "h2";
        }
        else if(randomNum==3){
            dir = "v1";
        }
        else{
            dir = "v2";
        }
        if(dir.equals("h1") || dir.equals("h2")){
            if(alShips.size()==0){
                shipLength = 4;
                shipHeight = 2;
            }
            else if(alShips.size()==1){
                shipLength = 4;
                shipHeight = 1;
            }
            else if(alShips.size()==2){
                shipLength = 3;
                shipHeight = 2;
            }
            else if(alShips.size()==3){
                shipLength = 3;
                shipHeight = 1;
            }
            else if(alShips.size()==4){
                shipLength = 2;
                shipHeight = 1;
            }
        }
        else{
            if(alShips.size()==0){
                shipLength = 2;
                shipHeight = 4;
            }
            else if(alShips.size()==1){
                shipLength = 1;
                shipHeight = 4;
            }
            else if(alShips.size()==2){
                shipLength = 2;
                shipHeight = 3;
            }
            else if(alShips.size()==3){
                shipLength = 1;
                shipHeight = 3;
            }
            else if(alShips.size()==4){
                shipLength = 1;
                shipHeight = 2;
            }
        }
    }
    
    public void setShips(){
        //Bot setzt alle Schiffe
        alShips.clear();
        while(alShips.size() < 5){
            int col = randomNum(0,10);
            int row = randomNum(0,10);
            setShipStates();
            if(alShips.isEmpty()){
                if(opponentBoard.isPossibleToSetShip(col, row, shipLength, shipHeight, dir)==true){
                    AircraftCarrier aircraftCarrier = new AircraftCarrier(col, row, dir);
                    aircraftCarrier.setShip(opponentBoard);
                    alShips.add(aircraftCarrier);
                }
            }  
            else if(alShips.size()==1){
                if(opponentBoard.isPossibleToSetShip(col, row, shipLength, shipHeight, dir)==true){
                    Battleship battleship = new Battleship(col, row, dir);
                    battleship.setShip(opponentBoard);
                    alShips.add(battleship);
                }
            }
            else if(alShips.size()==2){
                if(opponentBoard.isPossibleToSetShip(col, row, shipLength, shipHeight, dir)==true){                  
                    Submarine submarine = new Submarine(col, row, dir);
                    submarine.setShip(opponentBoard);
                    alShips.add(submarine);
                }
            }
            else if(alShips.size()==3){
                if(opponentBoard.isPossibleToSetShip(col, row, shipLength, shipHeight, dir)==true){
                    Cruiser cruiser = new Cruiser(col, row, dir);
                    cruiser.setShip(opponentBoard);
                    alShips.add(cruiser);
                }
            }
            else if(alShips.size()==4){
                if(opponentBoard.isPossibleToSetShip(col, row, shipLength, shipHeight, dir)==true){
                    Destroyer destroyer = new Destroyer(col, row, dir);
                    destroyer.setShip(opponentBoard);
                    alShips.add(destroyer);
                }
            }
        }
    }
    
    public abstract void chooseFieldToShoot();

    public void shoot(){
        ownBoard.shoot(shootCol, shootRow);
    }
    
    private void setShootedFieldCoordinates(int col, int row){
        shootCol = col;
        shootRow = row;
    }
    
    public int getShootCol(){
        return shootCol;
    }
    
    public int getShootRow(){
        return shootRow;
    }
    
    public Board getOwnBoard(){
        return ownBoard;
    }
    
    public Board getOpponentBoard(){
        return opponentBoard;
    }
    
    public void restart(){
        //started alles neu
        alShips.clear();
        opponentBoard.resetBoard();
        dir = "h1";
    }
    
    public abstract void draw(Graphics g, int width, int height);
}
