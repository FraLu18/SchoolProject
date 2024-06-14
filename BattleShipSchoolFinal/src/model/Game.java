package model;

import Ships.AircraftCarrier;
import Ships.Battleship;
import Ships.Cruiser;
import Ships.Destroyer;
import Ships.Ship;
import Ships.Submarine;
import java.awt.Graphics;
import java.util.ArrayList;
import players.BetterBot;
import players.Bot;
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
    
    private OwnBoard ownBoard;
    private OpponentBoard opponentBoard;
    private Player player;
    //private Bot bot;
    private Bot bot;
    
    private boolean turn = true; //true: Mensch am Zug, false: Computer am Zug
    private String shipType = "";
    private String direction = "h1";
    private int shipLength=0;
    private int shipHeight=0;
    private int numOfShips = 0;
    private int numOfBotShips = 0;
    private boolean isShipSelected = false;
    
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private ArrayList<Ship> botShips = new ArrayList<>();
    
    public Game(){
        ownBoard = new OwnBoard();
        opponentBoard = new OpponentBoard();
        player = new Player(ownBoard,opponentBoard);
        bot = new Bot(ownBoard,opponentBoard);
        setBotShipsList();
    }
    
    public void setPlayerShipsList(){
        playerShips = player.getShipList();
    }
    
    public void setBotShipsList(){
        botShips = bot.getShipList();
    }
    
    public void switchTurn(){
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
    
    public void removeShip(){
        player.removeShip();
    }
    
    public boolean isShipPlacedSuccessfully(int col, int row){
        return player.isSuccesfullyPlaced();
    }
    
    public void setSuccessfullyPlaced(boolean result){
        player.setSuccesfullyPlaced(result);
    }
    
    private void handleHit(int col, int row){
        if(turn == true){
            for (int i = 0; i < botShips.size(); i++) {
                for (int j = 0; j < botShips.get(i).getFieldsOfShip().size(); j++) {
                    if(botShips.get(i).getColOfFieldFromShip(j) == col && botShips.get(i).getRowOfFieldFromShip(j) == row){
                        botShips.get(i).hitted();
                        handleSunkShips(opponentBoard,botShips);
                        //System.out.println("test");
                        break;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < playerShips.size(); i++) {
                for (int j = 0; j < playerShips.get(i).getFieldsOfShip().size(); j++) {
                    if(playerShips.get(i).getColOfFieldFromShip(j) == bot.getShootCol() && playerShips.get(i).getRowOfFieldFromShip(j) == bot.getShootRow()){
                        playerShips.get(i).hitted();
                        handleSunkShips(ownBoard,playerShips);
                        System.out.println("hitHandeled");
                        //System.out.println("testtt");
                        break;
                    }
                }
            }
        }
    }
    
    public void playerShoot(int col, int row){
        boolean isHit = false;
        if(opponentBoard.getConditionOfField(col, row)==1){
            handleHit(col, row);
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
        //System.out.println("con: " + ownBoard.getConditionOfField(bot.getShootCol(), bot.getShootRow()));
        if(ownBoard.getConditionOfField(bot.getShootCol(), bot.getShootRow())>=3){
            handleHit(bot.getShootCol(), bot.getShootRow());
            //System.out.println("again");
            botShoot();
        }
        else{
            switchTurn();
        } 
    }
    
    public void handleSunkShips(Board board, ArrayList<Ship> listOfShips){
        //System.out.println("bro");
        for (int i = 0; i < listOfShips.size(); i++) {
            if(listOfShips.get(i).isSunk()==true){
                for (int fieldIndex = 0; fieldIndex < listOfShips.get(i).getFieldsOfShip().size(); fieldIndex++) {
                    board.sunk(listOfShips.get(i).getColOfFieldFromShip(fieldIndex), listOfShips.get(i).getRowOfFieldFromShip(fieldIndex), listOfShips.get(i).getShipType());
                    //System.out.println("brother");
                }
            }
        }
    }
    
    public boolean getTurn(){
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
        direction = "h1";
    }
    
    public String getDirection(){
        System.out.println(direction);
        return direction;
    }
    
    public int getNumOfPlacedShips(){
        return player.getNumOfShips();
    }
    
    public int getNumOfBotShips(){
        return numOfBotShips;
    }
    
    public void setSelected(boolean isShipSelected){
        this.isShipSelected = isShipSelected;
    }
    
    public boolean isShipSelected(){
        return isShipSelected;
    }
    
    public void gameOver(){
        
    }
    
    public void restart(){
        
    }
    
    public void drawOpponentBoard(Graphics g, int squareSize){
        opponentBoard.drawBoard(g, squareSize);
    }
    
    public void drawOwnBoard(Graphics g, int squareSize){
        ownBoard.drawBoard(g, squareSize);
    }
    
}
