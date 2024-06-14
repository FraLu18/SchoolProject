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
    
    public void setShip(int col, int row, int lengthOfShip, int heightOfShip, String direction, String chipCharacter){
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
    }
}
