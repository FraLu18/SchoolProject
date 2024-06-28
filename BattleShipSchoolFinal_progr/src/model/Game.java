package model;

import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Ship;
import Ships.Submarine;
import java.awt.Graphics;
import java.util.ArrayList;
import Bots.BetterBot;
import Bots.Bot;
import Bots.BotPlayer;
import Bots.GoodBot;
import Bots.ProbBot;
import java.awt.Color;
import players.Player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luc
 */
public class Game {
    
    private OwnBoard ownBoard; //eigene Brett, wo der Mensch seine Schiffe platziert
    private OpponentBoard opponentBoard; //Das Brett wo der Bot seine Schiffe platziert
    private Player player; 
    //private Bot bot;
    
    private boolean turn = true; //true: Mensch am Zug, false: Computer am Zug
    private String shipType = "";
    private String direction = "h1";
    private int shipLength=0;
    private int shipHeight=0;
    private int numOfShips = 0;
    private int numOfBotShips = 0;
    private boolean isShipSelected = false;
    private BotPlayer bot;
    
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private ArrayList<Ship> botShips = new ArrayList<>();
    private ArrayList<String> bots = new ArrayList<>();
    
    public Game(){
        ownBoard = new OwnBoard();
        opponentBoard = new OpponentBoard();
        player = new Player(ownBoard,opponentBoard);
        createListOfBots();
    }
    
    public void createBot(int selectedIndex){
        if(selectedIndex==0){
           bot = new Bot(ownBoard, opponentBoard);
        }
        else{
           bot = new GoodBot(ownBoard, opponentBoard);
        }
        setBotShipsList();
    }
    
    public void createListOfBots(){
        bots.add("Weak Bot");
        bots.add("Medium Bot");
    }
    
    public Object[] updateList(){
        return bots.toArray();
    }
    
    public void setPlayerShipsList(){
        playerShips = player.getShipList();
    }
    
    public void setBotShipsList(){
        botShips = bot.getShipList();
    }
    
    public void switchTurn(){
        //wenn turn true ist der Benutzer dran, sonst ist der Bot drann
        if(turn == true){
            turn = false;
        }
        else{
            turn = true;
        }
    }
    
    public void playerSetShip(int col, int row){
        player.setShip(col, row, shipLength, shipHeight, direction, shipType);
    }
    
    public void removeShip(boolean toRemove){
        //entfernt letztes platziertes Schiff
        if(toRemove){
            player.removeShip();
        }      
    }
    
    public boolean isShipPlacedSuccessfully(int col, int row){
        //uberpruft ob das Schiff platziert wurde
        return player.isSuccesfullyPlaced();
    }
    
    public void setSuccessfullyPlaced(boolean result){
        //setzt resultat ob es erfolgreich platziert wurde
        player.setSuccesfullyPlaced(result);
    }
    
    private void handleHit(int col, int row, ArrayList<Ship> ships){
        //Falls Treffer wird gesucht zu welchem Schiff das Feld gehort und entfernt einen Trefferpunkt dieses Schiffes
        for (int i = 0; i < ships.size(); i++) {
            for (int j = 0; j < ships.get(i).getFieldsOfShip().size(); j++) {
                if(ships.get(i).getColOfFieldFromShip(j) == col && ships.get(i).getRowOfFieldFromShip(j) == row){
                    ships.get(i).hitted();
                    if (turn) {
                        handleSunkShips(opponentBoard,ships);
                    }else{
                        handleSunkShips(ownBoard,ships);
                    }
                    
                    break;
                }
            }
        }
    }
    
    public int getResultOfShot(int col, int row){
        //Resultat ob es ein Treffer war oder nicht
        return opponentBoard.getConditionOfField(col, row);
    }
    
    public void playerShoot(int col, int row){
        boolean isHit = false;
        if(opponentBoard.getConditionOfField(col, row)==1){
            handleHit(col, row, botShips);
            isHit = true;
        }
        else if(opponentBoard.getConditionOfField(col, row)<2){
            switchTurn();
        }
        player.shoot(col, row);
    }
    
    public void botShoot(){
        bot.chooseFieldToShoot();
        System.out.println(bot.getShootCol() + " , " + bot.getShootRow());
        if(ownBoard.getConditionOfField(bot.getShootCol(), bot.getShootRow())>=3){
            handleHit(bot.getShootCol(), bot.getShootRow(), playerShips);
            botShoot();
        }
        else{
            switchTurn();
        } 
    }
    
    
    public void handleSunkShips(Board board, ArrayList<Ship> listOfShips){
        //Falls Schiff versunken, werden alle Felder dieses Schiffes auf versunken gesetzt
        for (int i = 0; i < listOfShips.size(); i++) {
            if(listOfShips.get(i).isSunk()==true){
                for (int fieldIndex = 0; fieldIndex < listOfShips.get(i).getFieldsOfShip().size(); fieldIndex++) {
                    board.sunk(listOfShips.get(i).getColOfFieldFromShip(fieldIndex), listOfShips.get(i).getRowOfFieldFromShip(fieldIndex), listOfShips.get(i).getShipType());

                }
            }
        }
    }
    
    public boolean getTurn(){
        //Gibt zuruck wer am Zug ist
        return turn;
    }
    
    public void botTurn(){
        turn = false;
    }
    
    public void humanTurn(){
        turn = true;
    }
    
    public void setShipLength(int shipLength){
        this.shipLength = shipLength;
    }
    
    public int getShipLength(){
        return shipLength;
    }   
    
    public void setShipHeigth(int shipHeight){
        this.shipHeight = shipHeight;
    }
    
    public int getShipHeight(){
        return shipHeight;
    }
    
    public void setShipType(String pShipType){
        shipType = pShipType;
    }
    
    public String getShipType(){
        return shipType;
    }
    
    public void changeDirection(){
        //ändert Richtung des Schiffes und aktualisiert die Schiffslange und Hohe in der Logik
        int oldLength = getShipLength();
        if(direction.equals("h1")){
            direction = "v1";
            System.out.println(direction);
        }
        else if(direction.equals("v1")){
            direction = "h2";
        }
        else if(direction.equals("h2")){
            direction = "v2";
        }
        else if(direction.equals("v2")){
            direction = "h1";
        }
        shipLength = getShipHeight();
        shipHeight = oldLength;
        System.out.println(shipLength + " , " + shipHeight);
    }
    
    public void changeToStartDirection(){
        //setzt Schiff auf default Richung
        direction = "h1";
    }
    
    public String getDirection(){
        //gibt aktuelle Richtung des Schiffs
        System.out.println(direction);
        return direction;
    }
    
    public int getNumOfPlacedShips(){
        //Gibt die Anzahl an platzierten Schiffen zuruck
        return player.getNumOfShips();
    }
    
    public int getNumOfBotShips(){
        //Gibt die Anzahl der Schiffe vom Bot
        return numOfBotShips;
    }
    
    public void setSelected(boolean isShipSelected){
        //setzt auf selektiert oder nicht
        this.isShipSelected = isShipSelected;
    }
    
    public boolean isShipSelected(){
        //gibt an ob ein Schiff ausgewahlt ist
        return isShipSelected;
    }
    
    public void rocketShoot(int col, int row){
        player.shootRocket(col, row);
        int cond = player.getPreviousCondition();
        if(cond == 1) {
            handleHit(player.getPreviousCol(), player.getPreviousRow(), botShips);
        }    
        //handleSunkShips(opponentBoard, botShips);
    }
    
    public boolean isRocketShootHorizontally(){
        return player.isRocketShootHorizontally();
    }
    
    public void finishLastRocketshoot(){
        player.finshLastRocketShoot();
    }
    
    public boolean isPlayerWinner(){
        //Gibt zuruck ob der Benutzer schon gewonnen hat
        boolean isWinner = true;
        for (int i = 0; i < botShips.size(); i++) {
            if(botShips.get(i).isSunk()==false){
                //System.out.println("failed");
                isWinner = false;
            }
        }
        return isWinner;
    }
    
    public boolean isPlayerLoser(){
        //Gibt zuruck ob der Bot schon gewonnen hat
        boolean isLoser = true;
        for (int i = 0; i < playerShips.size(); i++) {
            if(playerShips.get(i).isSunk()==false){
                isLoser = false;
            }
        }
        return isLoser;
    }
    
    public void restart(){
        //started in der Logik alles neu
        player.restart();
        bot.restart();
    }
    
    public void drawOpponentBoard(Graphics g, int squareSize){
        opponentBoard.drawBoard(g, squareSize);
    }
    
    public void drawOwnBoard(Graphics g, int squareSize){
        ownBoard.drawBoard(g, squareSize);
    }
    
    public void draw(Graphics g, int width, int height){
        if(bot != null){
            bot.draw(g, width, height);
        }
        else{
            g.setColor(Color.gray);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.red);
            g.drawLine(0, 0, width, height);
            g.drawLine(0, height, width, 0);
        }
    }
    
}
