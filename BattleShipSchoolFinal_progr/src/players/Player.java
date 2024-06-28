
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
public class Player {
    //Human Player
    private OwnBoard ownBoard;
    private OpponentBoard opponentBoard;
    
    private boolean succesfullyPlaced = false;
    private int shotCol = 0;
    private int shotRow = 0;
    private int previousCol = 0;
    private int previousRow = 0;
    private int oldCondition;
    private int realOldCondition;
    private final int WATER = 0;
    private final int SHIP = 1;
    private final int WATERHIT = 2;
    private final int SHIPHIT = 3;
    private final int SUNK = 4;
    private final int ROCKET = 10;
    private boolean isRocketShootHorizontally;
    private boolean isFirstRocketShot = true;
    
    private ArrayList<Ship> alShips = new ArrayList<>();
    
    
    public Player(OwnBoard ownBoard, OpponentBoard opponentBoard){
        this.ownBoard = ownBoard;
        this.opponentBoard = opponentBoard;
    }
    
    public void shoot(int col, int row){
        opponentBoard.shoot(col, row); 
    }
    
    public int getNumOfShips(){
        return alShips.size();
    }
    
    public ArrayList<Ship> getShipList(){
        return alShips;
    }
    
    public void removeShip(){
        if(!alShips.isEmpty()) {
            int lastIndex = alShips.size()-1;
            //setzt alle Felder des Schiffes zu Wasser
            alShips.get(lastIndex).unsetShip(ownBoard);
            //entfernt das Schiff von der List der lebenden Schiffe
            alShips.remove(lastIndex);
        }
    }
    
    private boolean isThisShipAlreadyPlaced(String character){
        //uberpruft ob das ausgewahlte Schiff bereits platziert wurde
        boolean result = false;
        for (int i = 0; i < alShips.size(); i++) {
            if(alShips.get(i).getShipType().equals(character)){
                result = true;
                break;
            }
        }
        return result;
    }
    
    public boolean isSuccesfullyPlaced(){
        return succesfullyPlaced;
    }
    
    public void setSuccesfullyPlaced(boolean pSuccesfullyPlaced){
        succesfullyPlaced = pSuccesfullyPlaced;
    }
    
    private void setRocketDir(boolean result){
        isRocketShootHorizontally = result;
    }
    
    public boolean isRocketShootHorizontally(){
        return isRocketShootHorizontally;
    }
    
    public void finshLastRocketShoot(){
        //schiesst das letzte feld ab
        if(oldCondition == WATER) {
            opponentBoard.changefieldCondition(shotCol, shotRow, oldCondition);
            opponentBoard.shoot(shotCol, shotRow);
        }
        else if(oldCondition == SHIP) {
            opponentBoard.changefieldCondition(shotCol, shotRow, oldCondition);
            opponentBoard.shoot(shotCol, shotRow);
        }
        else{
            opponentBoard.changefieldCondition(shotCol, shotRow, oldCondition);
        }
    }
    
    public void shootRocket(int col, int row){
        //Code für Animation und das schiessen der Rakete
        if(isFirstRocketShot) {
            setRocketDir(true);
            shotCol = col;
            shotRow = row;
            oldCondition = opponentBoard.getConditionOfField(col, row);
            previousCol = shotCol;
            previousRow = shotRow;
            setPreviousCondition(oldCondition);
            opponentBoard.setRocket(col, row);
            isFirstRocketShot = false;
        } 
        else{
            if(opponentBoard.getConditionOfField(shotCol, shotRow) < SUNK || opponentBoard.getConditionOfField(shotCol, shotRow) == ROCKET){
                opponentBoard.changefieldCondition(shotCol, shotRow, oldCondition);
            } 
            setPreviousCondition(opponentBoard.getConditionOfField(shotCol, shotRow));
            previousCol = shotCol;
            previousRow = shotRow;
            opponentBoard.shoot(shotCol, shotRow);         
            oldCondition = opponentBoard.getConditionOfField(col, row);
            opponentBoard.setRocket(col, row);
            shotCol = col;
            shotRow = row;
        }
    }
    
    public void setPreviousCondition(int pCondition){
        realOldCondition = pCondition;
    }
    
    public int getPreviousCondition(){
        return realOldCondition;
    }
    
    public int getPreviousCol(){
        return previousCol;
    }
    
    public int getPreviousRow(){
        return previousRow;
    }
    
    public int getShootedColByRocket(){
        return shotCol;
    }
    
    public int getShootedRowByRocket(){
        return shotRow;
    }
    
    public void setShip(int col, int row, int lengthOfShip, int heightOfShip, String direction, String chipCharacter){
        //setzt Schiff ins ownBoard
        if(ownBoard.isPossibleToSetShip(col,row,lengthOfShip, heightOfShip, direction)==true){
            if(chipCharacter.equals("S1")){
                if(isThisShipAlreadyPlaced("S1")==false){
                    AircraftCarrier aircraftCarrier = new AircraftCarrier(col, row, direction);
                    aircraftCarrier.setShip(ownBoard);
                    alShips.add(aircraftCarrier);
                }    
            }
            if(chipCharacter.equals("S2")){
                if(isThisShipAlreadyPlaced("S2")==false){
                    Battleship battleship = new Battleship(col, row, direction);
                    battleship.setShip(ownBoard);
                    alShips.add(battleship);
                }    
            }
            if(chipCharacter.equals("S3")){
                if(isThisShipAlreadyPlaced("S3")==false){
                    Submarine submarine = new Submarine(col, row, direction);
                    submarine.setShip(ownBoard);
                    alShips.add(submarine);
                }    
            }
            if(chipCharacter.equals("S4")){
                if(isThisShipAlreadyPlaced("S4")==false){
                    Cruiser cruiser = new Cruiser(col, row, direction);
                    cruiser.setShip(ownBoard);
                    alShips.add(cruiser);
                }
            }
            if(chipCharacter.equals("S5")){
                if(isThisShipAlreadyPlaced("S5")==false){
                    Destroyer destroyer = new Destroyer(col, row, direction);
                    destroyer.setShip(ownBoard);
                    alShips.add(destroyer);
                }
            }
            succesfullyPlaced = true;
        }
        else{
            succesfullyPlaced=false;
        }
    }
    
    public void restart(){
        //started alles neu
        alShips.clear();
        succesfullyPlaced = false;
        ownBoard.resetBoard();
    }
}
