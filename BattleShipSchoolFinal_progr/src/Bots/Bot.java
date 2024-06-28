package Bots;

import java.awt.Color;
import java.awt.Graphics;
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
public class Bot extends BotPlayer {
    //Computer Player
    private final int WATER = 0;
    private final int SHIP = 1;
    private final int WATERHIT = 2;
    private final int SHIPHIT = 3;
    private final int SUNK = 4;
    private final int ROCKET = 10;
    
    public Bot(OwnBoard ownBoard, OpponentBoard opponentBoard){
        super(ownBoard, opponentBoard);
        System.out.println("Random Bot activated");
    }
    
    @Override
    public void chooseFieldToShoot(){
        //wahlt Feld zum schiessen aus und beschiesst es dann
        boolean isValid = false;
        int col;
        int row;
        while(isValid == false){
            col = super.randomNum(0,10);
            row = super.randomNum(0,10);
            if(super.getOwnBoard().getConditionOfField(col, row) < WATERHIT){
                isValid = true;
                shootCol = col;
                shootRow = row;
                super.getOwnBoard().shoot(col, row);
                break;
            }
        }
    }
    
    @Override
    public void draw(Graphics g, int width, int height) {
        
        g.setColor(Color.green);

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
