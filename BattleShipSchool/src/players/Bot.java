package players;

import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Ship;
import Ships.Submarine;
import java.util.ArrayList;
import model.Board;
import model.OpponentBoard;
import model.OwnBoard;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luc
 */
public class Bot {
    //Computer Player
    private OwnBoard ownBoard;
    private OpponentBoard opponentBoard;
    
    private ArrayList<Ship> alShips = new ArrayList<>();
    
    private String dir="h";
    private int shipLength  = 0;
    private int shipHeight = 0;
    
    private int shootCol;
    private int shootRow;
    
    public Bot(OwnBoard ownBoard, OpponentBoard opponentBoard){
        this.ownBoard = ownBoard;
        this.opponentBoard = opponentBoard;
        setShip();
    }
    
    private int randomNum(int min, int max){
        return (int)((Math.random() * (max - min)) + min); 
    }
    
    public ArrayList<Ship> getShipList(){
        return alShips;
    }
    
    private void setShipStates(){
        int randomNum = randomNum(1,2);
        if(randomNum==1){
            dir = "h";
        }
        else{
            dir = "v";
        }
        //if(dir.equals("h")){
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
        //}
        /*else{
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
        }*/
    }
    
    public void setShip(){
        alShips.clear();
        while(alShips.size() < 5){
            setShipStates();
            int col = randomNum(0,10);
            int row = randomNum(0,10);
            //System.out.println(col);
            //System.out.println(row);
            if(alShips.size()==0){
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
    
    public void chooseFieldToShoot(){
        boolean isValid = false;
        int col;
        int row;
        while(isValid == false){
            col = randomNum(0,10);
            row = randomNum(0,10);
            if(ownBoard.getConditionOfField(col, row) < 2){
                isValid = true;
                shootCol = col;
                shootRow = row;
                break;
            }
        }
    }
    
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
}
