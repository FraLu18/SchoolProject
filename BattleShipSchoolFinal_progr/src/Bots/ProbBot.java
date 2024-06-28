/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bots;

import java.awt.Color;
import java.awt.Graphics;
import model.OpponentBoard;
import model.OwnBoard;

/**
 *
 * @author luc
 */
public class ProbBot extends BotPlayer{
    
    private int shipL;
    private int shipH;
    
    private int firstHitCol = -1;
    private int firstHitRow = -1;
    
    private int oldHitCol = -1;
    private int oldHitRow = -1;
    
    private int hitCol = -1;
    private int hitRow = -1;
    
    private int trys = 0;
    
    private int fieldValue = 0;
    
    private int probBoard[][] = new int[10][10];
    
    public ProbBot(OwnBoard ownBoard, OpponentBoard opponentBoard){
        super(ownBoard, opponentBoard);
        //initProbBoard();
    }
    
    /*private void initProbBoard(){
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
                probBoard[col][row] = 0;
            }
        }
    }
    
    private void setShipLength(String ship){
        if(ship.equals("S1")){
            shipL = 4;
            shipH = 2;
        }
        else if(ship.equals("S2")){
            shipL = 4;
            shipH = 1;
        }
        else if(ship.equals("S3")){
            shipL = 3;
            shipH = 2;
        }
        else if(ship.equals("S4")){
            shipL = 3;
            shipH = 1;
        }
        else{
            shipL = 2;
            shipH = 1;
        }
    }
    
    private void setFieldValueForOneShip(int col, int row){
        int verticalShipLength = shipH;
        int verticalShipHeight = shipL;
        if (ownBoard.isPossibleToSetShipBot(col, row, shipL, shipH, "h1")) {
            fieldValue++;
        }
        if (ownBoard.isPossibleToSetShipBot(col, row, shipL, shipH, "h2")) {
            fieldValue++;
        }
        if (ownBoard.isPossibleToSetShip(col, row, verticalShipLength, verticalShipHeight, "v1")) {
            fieldValue++;
        }
        if (ownBoard.isPossibleToSetShip(col, row, verticalShipLength, verticalShipHeight, "v2")) {
            fieldValue++;
        }
    }
    
    private void setValueForFieldsByHit(){
        
        if (firstHitCol != -1 && firstHitRow != -1) {
            if (oldHitCol != -1 && oldHitRow != -1) {
                if(hitCol != -1 && hitRow != -1) {
                    if (hitCol > oldHitCol && hitRow == oldHitRow && hitCol+1 <= 9 && super.getOwnBoard().getConditionOfField(hitCol+1, hitRow) < 2) {
                        probBoard[hitCol+1][hitRow] += 25;
                    }
                    else if (hitCol > oldHitCol && hitRow == oldHitRow && firstHitCol-1 > 0 &&  super.getOwnBoard().getConditionOfField(firstHitCol-1, firstHitRow) < 2) {
                        probBoard[firstHitCol-1][hitRow] += 25;
                    }
                    else if (hitRow > oldHitRow && hitCol== oldHitCol && hitRow+1 <= 9 &&  super.getOwnBoard().getConditionOfField(hitCol, hitRow+1) < 2) {
                        probBoard[hitCol][hitRow+1] += 25;
                    }
                    else if (hitRow > oldHitRow && hitCol== oldHitCol && firstHitRow-1 > 0 &&  super.getOwnBoard().getConditionOfField(hitCol, firstHitRow-1) < 2) {
                        probBoard[hitCol][firstHitRow-1] += 25;
                    }
                    if (hitCol < oldHitCol && hitRow == oldHitRow && hitCol-1 > 0 &&  super.getOwnBoard().getConditionOfField(hitCol-1, hitRow) < 2) {
                        probBoard[oldHitCol-1][oldHitRow] += 25;
                    }
                    if (hitRow > oldHitRow && hitCol == oldHitCol && hitRow+1 <= 9 && super.getOwnBoard().getConditionOfField(hitCol, hitRow+1) < 2){
                        probBoard[oldHitCol][oldHitRow+1] += 25;
                    }
                }
                else{
                    if (oldHitCol > firstHitCol && oldHitRow == firstHitRow && oldHitCol+1 <= 9 &&  super.getOwnBoard().getConditionOfField(oldHitCol+1, oldHitRow) < 2) {
                        probBoard[oldHitCol+1][oldHitRow] += 25;
                    }
                    else if (oldHitCol > firstHitCol && oldHitRow == firstHitRow && firstHitCol-1 > 0 && super.getOwnBoard().getConditionOfField(firstHitCol-1, firstHitRow) < 2) {
                        probBoard[firstHitCol-1][firstHitRow] += 25;
                    }
                    else if (oldHitRow < firstHitRow && oldHitCol == firstHitCol && oldHitRow-1 > 0 && super.getOwnBoard().getConditionOfField(oldHitCol, oldHitRow-1) < 2) {
                        probBoard[oldHitCol][oldHitRow-1] += 25;
                    }
                    else if (oldHitRow < firstHitRow && oldHitCol == firstHitCol && firstHitRow+1 <= 9 && super.getOwnBoard().getConditionOfField(firstHitCol, firstHitRow+1) < 2) {
                        probBoard[firstHitCol][firstHitRow+1] += 25;
                    }
                    if (oldHitCol < firstHitCol && oldHitRow == firstHitRow && oldHitCol-1 > 0 && super.getOwnBoard().getConditionOfField(oldHitCol-1, oldHitRow) < 2) {
                        probBoard[oldHitCol-1][oldHitRow] += 25;
                    }
                    if (oldHitRow > firstHitRow && oldHitCol == firstHitCol && oldHitRow+1 <= 9 && super.getOwnBoard().getConditionOfField(oldHitCol, oldHitRow+1) < 2){
                        probBoard[firstHitCol][firstHitRow+1] += 25;
                    }
                }
            }
            else{
                if (firstHitCol+1 <= 9 && firstHitCol+1 <= 9 && super.getOwnBoard().getConditionOfField(firstHitCol+1, firstHitRow) < 2){
                    probBoard[firstHitCol+1][firstHitRow] += 25;
                }
                if (firstHitCol-1 > 0 && firstHitCol-1 > 0 &&  super.getOwnBoard().getConditionOfField(firstHitCol-1, firstHitRow) < 2) {
                    probBoard[firstHitCol-1][firstHitRow] += 25;
                }
                if (firstHitRow+1 <= 9 && firstHitRow+1 <= 9 &&  super.getOwnBoard().getConditionOfField(firstHitCol, firstHitRow+1) < 2) {
                    probBoard[firstHitCol][firstHitRow+1] += 25;
                }
                if (firstHitRow-1 > 0 && firstHitRow-1 > 0 &&  super.getOwnBoard().getConditionOfField(firstHitCol, firstHitRow-1) < 2) {
                    probBoard[firstHitCol][firstHitRow-1] += 25;
                }
            }
        }          
    }
    
    private void updateProbBoard(){
        initProbBoard();
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
                if(ownBoard.getConditionOfFieldForBot(col, row) < 2) {
                    setShipLength("S1");
                    setFieldValueForOneShip(col, row);
                    setShipLength("S2");
                    setFieldValueForOneShip(col, row);
                    setShipLength("S3");
                    setFieldValueForOneShip(col, row);
                    setShipLength("S4");
                    setFieldValueForOneShip(col, row);
                    setShipLength("S5");
                    setFieldValueForOneShip(col, row);
                    probBoard[col][row] = fieldValue;
                    fieldValue = 0;
                }
                else{
                    probBoard[col][row] = 0;
                }
            }
        }
        setValueForFieldsByHit();
    }
    
    */
    
    @Override
    public void chooseFieldToShoot() {
        int bestCol = 0;
        int bestRow = 0;
        //updateProbBoard();
        int currentHighestValue = probBoard[0][0];
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
                if(probBoard[col][row] >= currentHighestValue) {
                    currentHighestValue = probBoard[col][row];
                    bestCol = col;
                    bestRow = row;
                }
            }
        }
        super.getOwnBoard();
        ownBoard.shoot(bestCol, bestRow);
        //ownBoard.shootInProbBoard(bestCol, bestRow);
        
        if(ownBoard.getConditionOfField(bestCol, bestRow)>=4){
            hitCol = -1;
            hitRow = -1;
            oldHitCol = -1;
            oldHitRow = -1;
        }
        if(ownBoard.getConditionOfField(bestCol, bestRow)==3) {
            if (firstHitCol == -1 && firstHitRow == -1) {
                firstHitCol = bestCol;
                firstHitRow = bestRow;
            }
            else if (oldHitCol == -1 && oldHitRow == -1) {
                oldHitCol = bestCol;
                oldHitRow = bestRow;
            }
            else{
                hitCol = bestCol;
                hitRow = bestRow;
            }
        }
        shootCol = bestCol;
        shootRow = bestRow;
    }
    
    @Override
    public void draw(Graphics g, int width, int height){
        g.setColor(Color.blue);
        g.fillOval(0, 1/3, width, -(2/3));
    }
    
}
