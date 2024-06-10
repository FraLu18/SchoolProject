/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package players;

import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Ship;
import Ships.Submarine;
import java.awt.Point;
import java.util.ArrayList;
import model.OpponentBoard;
import model.OwnBoard;

/**
 *
 * @author luc
 */
public class BetterBot {
    
    private OwnBoard ownBoard;
    private OpponentBoard opponentBoard;
    
    private ArrayList<Ship> alShips = new ArrayList<>();
    
    private String dir="h";
    private int shipLength  = 0;
    private int shipHeight = 0;
    
    private int shootCol;
    private int shootRow;
    private boolean isOrientPoint = false;
    
    private int firstHitShootCol=-1;
    private int firstHitShootRow=-1;
    private int lastHitCol=-1;
    private int lastHitRow=-1;
    private int lastShootedCol;
    private int lastShootedRow;
    private Point orginalFirstHit = new Point(0,0);
    private boolean stepMade = false;
    private int numOfTrys = 1;
    private String shootDir = "r";
    
    public BetterBot(OwnBoard ownBoard, OpponentBoard opponentBoard){
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
        
        if(isOrientPoint==false){
            while(isValid == false){ // !isValid
                //col = randomNum(0,10);
                //row = randomNum(0,10);
                col = randomNum(0,4);
                row = randomNum(0,4);
                if(ownBoard.getConditionOfField(col, row) < 2){ // <- Use NAMED constants (These are magic numbers)
                    //isValid = true;
                    shootCol = col;
                    shootRow = row;
                    System.out.println("shootcol = " + shootCol + " shootrow = " + shootRow);
                    if(ownBoard.getConditionOfField(col, row)==1){
                        firstHitShootCol=col;
                        firstHitShootRow = row;
                        lastHitCol = firstHitShootCol;
                        lastHitRow = firstHitShootRow;
                        orginalFirstHit.x = firstHitShootCol;
                        orginalFirstHit.y = firstHitShootRow;
                        isOrientPoint = true;
                        System.out.println(shootDir);
                        shootDir = "r";
                    }
                    shoot();
                    //break; // <- Bad code
                    isValid = true;
                }
            }
        }
        else{
            // Create method consecutiveShot(int xDir, int yDir, ..)
            //setCorrectshootDirection();
            if(lastHitCol+1 < 10 && ownBoard.getConditionOfField(lastHitCol+1, lastHitRow) < 2 && shootDir.equals("r")){
                //rechts
                if(lastHitCol==0){
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
                shootCol = lastHitCol+1;
                shootRow = lastHitRow;
                ownBoard.shoot(lastHitCol+1, lastHitRow);
                if(ownBoard.getConditionOfField(lastHitCol+1, lastHitRow) >= 3){
                    lastHitCol = lastHitCol+1;
                }
                else{
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                    shootDir = "l";
                }
                //consecutiveShot(1, -1, "l");
            }
            else if(lastHitCol-1 >= 0 && ownBoard.getConditionOfField(lastHitCol-1, lastHitRow) < 2 && shootDir.equals("l")){
                //links
                if(lastHitCol==9){
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
                shootCol = lastHitCol-1;
                shootRow = lastHitRow;
                ownBoard.shoot(lastHitCol-1, lastHitRow);
                if(ownBoard.getConditionOfField(lastHitCol-1, lastHitRow) >= 3){
                    lastHitCol = lastHitCol-1;
                }
                else{
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                    shootDir = "x";
                }
            }
            else if(lastHitRow-1 >= 0 && ownBoard.getConditionOfField(lastHitCol, lastHitRow-1) < 2){
                //oben
                shootDir="x";
                if(lastHitRow==0){
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
                shootCol = lastHitCol;
                shootRow = lastHitRow-1;
                ownBoard.shoot(lastHitCol, lastHitRow-1);
                if(ownBoard.getConditionOfField(lastHitCol, lastHitRow-1) >= 3){
                    lastHitRow = lastHitRow-1;
                }
                else{
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
            }
            else if(lastHitRow+1 < 10 && ownBoard.getConditionOfField(lastHitCol, lastHitRow+1) < 2){
                //unten
                shootDir="x";
                if(lastHitRow==9){
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
                shootCol = lastHitCol;
                shootRow = lastHitRow+1;
                ownBoard.shoot(lastHitCol, lastHitRow+1);
                if(ownBoard.getConditionOfField(lastHitCol, lastHitRow+1) >= 3){
                    lastHitRow = lastHitRow+1;
                }
                else{
                    lastHitCol = firstHitShootCol;
                    lastHitRow = firstHitShootRow;
                }
            }
            else if(orginalFirstHit.x+numOfTrys <= 9 && stepMade == false){
                System.out.println("switched to right , " + orginalFirstHit.x + " + " + numOfTrys + " = " + (orginalFirstHit.x+numOfTrys));
                firstHitShootCol = orginalFirstHit.x+numOfTrys;
                lastHitCol = firstHitShootCol;
                lastHitRow = firstHitShootRow;
                shootDir = "r";
                stepMade = true;
                numOfTrys++;
                chooseFieldToShoot();
            }
            else if(orginalFirstHit.y-numOfTrys-1 >= 0 && stepMade == true){
                System.out.println("switched to left , " + orginalFirstHit.x + " - " + numOfTrys + " = " + (orginalFirstHit.x-numOfTrys-1));
                firstHitShootCol = orginalFirstHit.x-numOfTrys-1;
                lastHitCol = firstHitShootCol;
                lastHitRow = firstHitShootRow;  
                shootDir = "r";
                stepMade = false;
                numOfTrys++;
                chooseFieldToShoot();
            }
            else{
                numOfTrys++;
                chooseFieldToShoot();
            }
            
            if(ownBoard.getConditionOfField(lastHitCol, lastHitRow) >= 4){
                //wenn Schiff versenkt, geht er in den SEARCH MODE rein.
                isOrientPoint = false;
                stepMade = false;
                shootDir = "r";
                numOfTrys = 1;
                System.out.println("sunk");
                //falls noch ein Treffer vorhanden obwohl das gejagte Schiff versenkt ist, geht er in HUNT MODE
                for (int searchCol = 0; searchCol < 10; searchCol++) {
                    for (int searchRow = 0; searchRow < 10; searchRow++) {
                        if(ownBoard.getConditionOfField(searchCol, searchRow) == 3){
                            //Findet alten Treffer der ein anderes als das von ihm gejagte Schiff, getroffen hat.
                            //Suche dieses Schiff jetzt.
                            //geht in HUNT MODE
                            firstHitShootCol = searchCol;
                            firstHitShootRow = searchRow;
                            orginalFirstHit.x = firstHitShootCol;
                            orginalFirstHit.y = firstHitShootRow;
                            isOrientPoint = true;
                            break;
                        }
                    }
                }
            }            
        }
        
    }
    
    public void setCorrectshootDirection(){
        if(lastHitCol+1 < 10 && ownBoard.getConditionOfField(lastHitCol+1, lastHitRow) < 2){
            shootDir = "r";
        }
        else if(lastHitCol-1 >= 0 && ownBoard.getConditionOfField(lastHitCol-1, lastHitRow) < 2){
            shootDir = "l";
        }
        else if(lastHitCol-1 >= 0 && ownBoard.getConditionOfField(lastHitCol, lastHitRow-1) < 2){
            shootDir = "u";
        }
        else if(lastHitRow+1 < 10 && ownBoard.getConditionOfField(lastHitCol-1, lastHitRow+1) < 2){
            shootDir = "d";
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

