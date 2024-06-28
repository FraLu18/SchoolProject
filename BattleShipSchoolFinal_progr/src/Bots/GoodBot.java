/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bots;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.OpponentBoard;
import model.OwnBoard;

/**
 *
 * @author luc
 */

public class GoodBot extends BotPlayer{   
    //Computer Player
    private final int WATER = 0;
    private final int SHIP = 1;
    private final int WATERHIT = 2;
    private final int SHIPHIT = 3;
    private final int SUNK = 4;
    private final int ROCKET = 10;
    
    private ArrayList<Point> preferredFields = new ArrayList<>(); //List fur Felder die er prefferieren soll
    private boolean isOrientPoint = false;
    
    public GoodBot(OwnBoard ownBoard, OpponentBoard opponentBoard){
        super(ownBoard, opponentBoard);
        System.out.println("Good Bot activated");
    }
    
    @Override
    public void chooseFieldToShoot(){
        //wahlt ein Ziel aus und beschiesst es
        boolean isValid = false;
        int col;
        int row;
        if (!isOrientPoint) {
            while(isValid == false){
                col = super.randomNum(0,10);
                row = super.randomNum(0,10);
                if(super.getOwnBoard().getConditionOfField(col, row) < WATERHIT && col%2==0 && row%2==0){
                    isValid = true;
                    shootCol = col;
                    shootRow = row;
                    if(super.getOwnBoard().getConditionOfField(col, row)==SHIP){
                        isOrientPoint = true;
                        updateListOfPreferredFields(col,row);
                    }
                    super.getOwnBoard().shoot(col, row);                    
                    break;
                }
            }
        }
        else{
            while(isValid == false){
                col = super.randomNum(0,10);
                row = super.randomNum(0,10);
                for (int i = 0; i < preferredFields.size(); i++) { 
                    if (preferredFields.get(i).getX()==col && preferredFields.get(i).getY()==row) {
                        isValid = true;
                        preferredFields.remove(i);
                        shootCol = col;
                        shootRow = row;
                        super.getOwnBoard().shoot(col, row);
                        if(super.getOwnBoard().getConditionOfField(col, row) >= SUNK || preferredFields.isEmpty()){
                            isOrientPoint=false;
                            checkForNewOrientPoint();
                        }
                        break;
                    }
                } 
            }
        }       
    }
    
    public void checkForNewOrientPoint(){
        //schaut ob er ein weiteres noch nicht versunkenes Schiff gefunden hatte
        int col;
        int row;
        for (int i = 0; i < preferredFields.size(); i++) {
            col=(int)(preferredFields.get(i).getX());
            row=(int)(preferredFields.get(i).getY());
            if(ownBoard.getConditionOfField(col, row) == SHIPHIT){
                isOrientPoint = true;
                updateListOfPreferredFields(col,row);
                break;
            }
        }
    }
    
    public void updateListOfPreferredFields(int col, int row){
        //updated die veraltete Liste
        preferredFields.clear();
        for (int c = col-2; c <= col+2; c++) {
            for (int r = row-2; r <= row+2; r++) {
                if(r >= 0 && r < 10 && c >= 0 && c < 10 && super.getOwnBoard().getConditionOfField(c, r) < WATERHIT){
                    Point field = new Point(c, r);
                    preferredFields.add(field);
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics g, int width, int height) {
        
        g.setColor(Color.blue);

        // Halbkreis unten
        int ovalWidth = width - 20; // Hier wird 20 als Randabstand verwendet
        g.fillOval(10, height / 2, ovalWidth, height / 2);

        // Kopf auf dem Halbkreis
        int headSize = Math.min(width, height) / 2;
        int headTopLeftX = width / 2 - headSize / 2;
        int headTopLeftY = height / 2 - headSize / 2;

        Color color = new Color(233, 208, 152);
        g.setColor(color);
        g.fillOval(headTopLeftX, headTopLeftY, headSize, headSize);
        
        int eyeSize = headSize / 6;
        int eyeCenter = headSize / 2;
        
        int eye1X = headTopLeftX + headSize - eyeCenter / 2 - eyeSize;
        int eye2X = headTopLeftX + eyeCenter / 2;
        int eyeY = headTopLeftY + headSize / 3; // y-Position der Augen

        g.setColor(Color.black);
        g.fillOval(eye1X, eyeY, eyeSize, eyeSize);
        g.fillOval(eye2X, eyeY, eyeSize, eyeSize);
    }

}
