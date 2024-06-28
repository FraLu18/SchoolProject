package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import model.Game;
import Bots.Bot;
import players.Player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author luc
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    private Game game;
    private Timer timer;
    private Timer rocketTimer;
    private boolean enabled = false;
    private boolean isNewShip = true; //ist es ein neues Schiff
    private boolean makeshiftShipInBoard = false; //ist provisorisches Schiff im Brett
    private boolean isRocketSelected = false; // ist die Rakete ausgewahlt
    private boolean isVertical; //istdas zu platzierende Schif in vertikaler Richtung
    private boolean isEnabledToShoot = true;
    private int distance = 0;
    private boolean isBotChoosen = false;
    
    private int rCol = 0;//fur Rakete
    private int rRow = 0;//fur Rakete
    
    public MainFrame() {
        initComponents();
        game = new Game();
        ownBoardPanel.setGame(game);
        timer = new Timer(100, evt -> botShoot());
        //200
        rocketTimer = new Timer(200, evt -> rocketShoot());
        opponentBoardPanel.setGame(game);
        botPanel.setGame(game);
        botBarList.setListData(game.updateList());
        aircraftCarrierButton.setEnabled(true);
        battleshipButton.setEnabled(false);
        submarineButton.setEnabled(false);
        cruiserButton.setEnabled(false);
        destroyerButton.setEnabled(false);
        rocketButton.setEnabled(false);
        update();
        setLocationRelativeTo(null);
        setButtons();
        messagePanel.setBackground(Color.green);
        setTitle("Battleship Game");
    }
    
    public void update(){
        ownBoardPanel.repaint();
        opponentBoardPanel.repaint();
        botPanel.repaint();
    }
    
    public void botShoot(){
        //Bot schiesst
        int trys = 0;
        while(game.getTurn()==false && game.isPlayerLoser()==false){
            game.botShoot();
            update();
            trys++;
        }
        isPlayerLoser();
        //System.out.println("STOP");
        //System.out.println("-----------------");
        timer.stop();
        update();
        if(!game.isPlayerLoser()){
            enabled = true;
        }    
    }
    
    public void rocketShoot(){
        //Animation und aufrufe des Schusses
        enabled = false;
        game.rocketShoot(distance, rRow);
        //System.out.println(distance);
        distance++;
        if(distance == 10){
            game.finishLastRocketshoot();
            enabled = true;
            game.switchTurn();
            isRocketSelected = false;
            if(!game.isPlayerWinner()){
                timer.start();
            }
            rocketTimer.stop();
        }
        update();
    }
    
    public void isPlayerWinner(){
       if(game.isPlayerWinner()){
           System.out.println("Winner");
           enabled = false;
           messageLabel.setText("Congratulations captain, you won the battle!");
           messagePanel.setBackground(Color.yellow);
       }
    }
    
    public void isPlayerLoser(){
        if(game.isPlayerLoser()){
            enabled = false;
            messageLabel.setText("The battle is lost! All ships have sunk...");
            messagePanel.setBackground(Color.white);
        }    
    }
    
    private void setButtons(){
        //setzt Buttons
        if(game.getNumOfPlacedShips()==0){
            aircraftCarrierButton.setEnabled(true);
            battleshipButton.setEnabled(false);
            submarineButton.setEnabled(false);
            cruiserButton.setEnabled(false);
            destroyerButton.setEnabled(false);
        }
        else if(game.getNumOfPlacedShips()==1){
            battleshipButton.setEnabled(true);
            aircraftCarrierButton.setEnabled(false);
            submarineButton.setEnabled(false);
            cruiserButton.setEnabled(false);
            destroyerButton.setEnabled(false);
        }
        else if(game.getNumOfPlacedShips()==2){
            submarineButton.setEnabled(true);
            aircraftCarrierButton.setEnabled(false);
            battleshipButton.setEnabled(false);
            cruiserButton.setEnabled(false);
            destroyerButton.setEnabled(false);
        }
        else if(game.getNumOfPlacedShips()==3){
            cruiserButton.setEnabled(true);
            aircraftCarrierButton.setEnabled(false);
            battleshipButton.setEnabled(false);
            submarineButton.setEnabled(false);
            destroyerButton.setEnabled(false);
        }
        else if(game.getNumOfPlacedShips()==4){
            destroyerButton.setEnabled(true);
            aircraftCarrierButton.setEnabled(false);
            battleshipButton.setEnabled(false);
            submarineButton.setEnabled(false);
            cruiserButton.setEnabled(false);
        }
        else{
            aircraftCarrierButton.setEnabled(false);
            battleshipButton.setEnabled(false);
            submarineButton.setEnabled(false);
            cruiserButton.setEnabled(false);
            destroyerButton.setEnabled(false);
            rocketButton.setEnabled(true);
            game.setPlayerShipsList();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ownBoardPanel = new view.ownBoardPanel();
        opponentBoardPanel = new view.opponentBoardPanel();
        aircraftCarrierButton = new javax.swing.JButton();
        battleshipButton = new javax.swing.JButton();
        submarineButton = new javax.swing.JButton();
        cruiserButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        destroyerButton = new javax.swing.JButton();
        rocketButton = new javax.swing.JButton();
        messagePanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        botBarPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        botBarList = new javax.swing.JList();
        selectBotButton = new javax.swing.JButton();
        botPanel = new view.BotPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(169, 169, 169));

        jPanel1.setBackground(new java.awt.Color(112, 128, 144));

        ownBoardPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        ownBoardPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ownBoardPanelMouseMoved(evt);
            }
        });
        ownBoardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ownBoardPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout ownBoardPanelLayout = new javax.swing.GroupLayout(ownBoardPanel);
        ownBoardPanel.setLayout(ownBoardPanelLayout);
        ownBoardPanelLayout.setHorizontalGroup(
            ownBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        ownBoardPanelLayout.setVerticalGroup(
            ownBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        opponentBoardPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        opponentBoardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                opponentBoardPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout opponentBoardPanelLayout = new javax.swing.GroupLayout(opponentBoardPanel);
        opponentBoardPanel.setLayout(opponentBoardPanelLayout);
        opponentBoardPanelLayout.setHorizontalGroup(
            opponentBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        opponentBoardPanelLayout.setVerticalGroup(
            opponentBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        aircraftCarrierButton.setBackground(new java.awt.Color(64, 96, 160));
        aircraftCarrierButton.setText("Aircraft Carrier");
        aircraftCarrierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aircraftCarrierButtonActionPerformed(evt);
            }
        });

        battleshipButton.setBackground(new java.awt.Color(64, 96, 160));
        battleshipButton.setText("Battleship");
        battleshipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleshipButtonActionPerformed(evt);
            }
        });

        submarineButton.setBackground(new java.awt.Color(64, 96, 160));
        submarineButton.setText("Submarine");
        submarineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submarineButtonActionPerformed(evt);
            }
        });

        cruiserButton.setBackground(new java.awt.Color(64, 96, 160));
        cruiserButton.setText("Cruiser");
        cruiserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cruiserButtonActionPerformed(evt);
            }
        });

        restartButton.setBackground(new java.awt.Color(153, 0, 0));
        restartButton.setText("RESTART");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        destroyerButton.setBackground(new java.awt.Color(64, 96, 160));
        destroyerButton.setText("Destroyer");
        destroyerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destroyerButtonActionPerformed(evt);
            }
        });

        rocketButton.setBackground(new java.awt.Color(189, 150, 12));
        rocketButton.setText("ROCKET");
        rocketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rocketButtonActionPerformed(evt);
            }
        });

        messagePanel.setBackground(new java.awt.Color(0, 255, 0));

        messageLabel.setBackground(new java.awt.Color(0, 255, 0));
        messageLabel.setText("Welcome Player! Pls set your ships to start the game !  (Place the ships in the right square)");

        javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(messageLabel)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        botBarPanel.setBackground(new java.awt.Color(112, 128, 144));

        botBarList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(botBarList);

        selectBotButton.setBackground(new java.awt.Color(64, 96, 160));
        selectBotButton.setText("choose Bot");
        selectBotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBotButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botPanelLayout = new javax.swing.GroupLayout(botPanel);
        botPanel.setLayout(botPanelLayout);
        botPanelLayout.setHorizontalGroup(
            botPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        botPanelLayout.setVerticalGroup(
            botPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout botBarPanelLayout = new javax.swing.GroupLayout(botBarPanel);
        botBarPanel.setLayout(botBarPanelLayout);
        botBarPanelLayout.setHorizontalGroup(
            botBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(selectBotButton, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(botBarPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(botPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botBarPanelLayout.setVerticalGroup(
            botBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(botPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectBotButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(opponentBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ownBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(messagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rocketButton, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(submarineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(battleshipButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aircraftCarrierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cruiserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(restartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(destroyerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ownBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opponentBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(aircraftCarrierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(battleshipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(submarineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cruiserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(destroyerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rocketButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(messagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opponentBoardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opponentBoardPanelMousePressed
        int x = evt.getX();
        int y = evt.getY();

        int col = x / (opponentBoardPanel.getWidth() / 10);
        int row = y / (opponentBoardPanel.getHeight() / 10);
        
        if (!isRocketSelected && enabled == true && game.getTurn()==true && isBotChoosen==true) {
            if (game.getResultOfShot(col, row)==1) {
                messageLabel.setText("You scored!");
                messagePanel.setBackground(Color.green);
            }
            else if (game.getResultOfShot(col, row)==0) {
                messageLabel.setText("The shot missed!...");
                messagePanel.setBackground(Color.blue);
            }
            else{
                messageLabel.setText("You have already shot at this field, please choose another one!");
                messagePanel.setBackground(Color.red);
            }
            game.playerShoot(col, row);
            isPlayerWinner();
        }
        else if(enabled == true && isBotChoosen==true){
            rCol = col;
            rRow = row;
            rocketTimer.start();
        }
        else if(!isBotChoosen){
            messageLabel.setText("Please choose an opponent before you start the game!");
            messagePanel.setBackground(Color.red);
        }
        else{
            if(!game.isPlayerWinner() && !game.isPlayerLoser()) {
                messageLabel.setText("You haven't placed all your ships yet, or it's not your turn right now!");
                messagePanel.setBackground(Color.red);
            }      
        }
        update();
        if(game.getTurn()==false){
            //System.out.println("-----------------");
            //System.out.println("StART");
            enabled = false;
            timer.start();
        }
        //}
    }//GEN-LAST:event_opponentBoardPanelMousePressed

    private void ownBoardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ownBoardPanelMousePressed
        int x = evt.getX();
        int y = evt.getY();

        int col = x / (opponentBoardPanel.getWidth() / 10);
        int row = y / (opponentBoardPanel.getHeight() / 10);
        
        if(evt.getButton() == MouseEvent.BUTTON1){

            if(game.isShipSelected()==true){
                if(game.isShipPlacedSuccessfully(col, row)==true && game.getNumOfPlacedShips() != 5){
                    game.setSelected(false);
                    game.setSuccessfullyPlaced(false);
                    isNewShip = true;
                    makeshiftShipInBoard = false;
                    setButtons();
                    messageLabel.setText("Your ship has been successfully placed!");
                    messagePanel.setBackground(Color.green);
                }
                else if(game.isShipPlacedSuccessfully(col, row)==true && game.getNumOfPlacedShips() == 5){
                    game.setSelected(false);
                    game.setSuccessfullyPlaced(false);
                    isNewShip = true;
                    makeshiftShipInBoard = false;
                    setButtons();
                    enabled = true;
                    messageLabel.setText("The battle has begun, your ships are ready for action! We take the first shot, Captain!");
                    messagePanel.setBackground(Color.green);
                }
                else{
                    if(!game.isPlayerWinner() && !game.isPlayerLoser()){
                        messageLabel.setText("No ship can be placed here, try somewhere else!");
                        messagePanel.setBackground(Color.red);
                    }        
                }
                update();
            } 
            else{
                messagePanel.setBackground(Color.red);
            }
        }

        if(evt.getButton() == MouseEvent.BUTTON3){
           if(game.isShipSelected()){
                game.changeDirection();
                if(game.isShipSelected()){
                    if(game.isShipPlacedSuccessfully(col, row)){
                       isNewShip = false;
                       makeshiftShipInBoard = true;
                       game.removeShip(makeshiftShipInBoard);
                       messageLabel.setText("Your ship can be placed here!");
                       messagePanel.setBackground(Color.green);
                    }
                    else{
                        messageLabel.setText("You can't place your ship here, please place it somewhere else!");
                        messagePanel.setBackground(Color.red);
                    }
                    game.playerSetShip(col, row);
                    repaint();
                }
                update();
           } 
        }
    }//GEN-LAST:event_ownBoardPanelMousePressed

    private void cruiserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cruiserButtonActionPerformed
        game.setShipType("S4");
        game.setShipLength(3);
        game.setShipHeigth(1);
        game.changeToStartDirection();
        game.setSelected(true);
    }//GEN-LAST:event_cruiserButtonActionPerformed

    private void aircraftCarrierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aircraftCarrierButtonActionPerformed
        game.setShipType("S1");
        game.setShipLength(4);
        game.setShipHeigth(2);
        game.changeToStartDirection();
        game.setSelected(true);
    }//GEN-LAST:event_aircraftCarrierButtonActionPerformed

    private void battleshipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleshipButtonActionPerformed
        game.setShipType("S2");
        game.setShipLength(4);
        game.setShipHeigth(1);
        game.changeToStartDirection();
        game.setSelected(true);
    }//GEN-LAST:event_battleshipButtonActionPerformed

    private void submarineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submarineButtonActionPerformed
        game.setShipType("S3");
        game.setShipLength(3);
        game.setShipHeigth(2);
        game.changeToStartDirection();
        game.setSelected(true);
    }//GEN-LAST:event_submarineButtonActionPerformed

    private void destroyerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destroyerButtonActionPerformed
        game.setShipType("S5");
        game.setShipLength(2);
        game.setShipHeigth(1);
        game.changeToStartDirection();
        game.setSelected(true);
    }//GEN-LAST:event_destroyerButtonActionPerformed

    private void ownBoardPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ownBoardPanelMouseMoved
        int x = evt.getX();
        int y = evt.getY();

        int col = x / (ownBoardPanel.getWidth() / 10);
        int row = y / (ownBoardPanel.getHeight() / 10);
        
        if(game.isShipSelected()){
            if(game.isShipPlacedSuccessfully(col, row)){
               isNewShip = false;
               makeshiftShipInBoard = true;
               game.removeShip(makeshiftShipInBoard);
               messageLabel.setText("Your ship can be placed here!");
               messagePanel.setBackground(Color.green);
            }
            else{
                messageLabel.setText("You can't place your ship here, please place it somewhere else!");
                messagePanel.setBackground(Color.red);
            }
            game.playerSetShip(col, row);
            repaint();
        }
    }//GEN-LAST:event_ownBoardPanelMouseMoved

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        //started neu, wenn Bot ausgewahlt
        if(isBotChoosen){
            game.restart();
            isBotChoosen = false;
            enabled = false;
            distance = 0;
            isNewShip = true;
            makeshiftShipInBoard = false;
            aircraftCarrierButton.setEnabled(true);
            battleshipButton.setEnabled(false);
            submarineButton.setEnabled(false);
            cruiserButton.setEnabled(false);
            destroyerButton.setEnabled(false);
            rocketButton.setEnabled(false);
            selectBotButton.setEnabled(true);
            update();
        }       
    }//GEN-LAST:event_restartButtonActionPerformed

    private void rocketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rocketButtonActionPerformed
        isRocketSelected = true;
        //Color gold = new Color(189,150,12);
        messageLabel.setText("You've brought out the big guns! Keep in mind that you can only shoot this rocket once...");
        messagePanel.setBackground(Color.yellow);
        rocketButton.setEnabled(false);
    }//GEN-LAST:event_rocketButtonActionPerformed

    private void selectBotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBotButtonActionPerformed
        int selectedIndex = botBarList.getSelectedIndex();
        if(botBarList.getSelectedIndex() != -1){
            game.createBot(selectedIndex);
            botBarList.setListData(game.updateList());
            isBotChoosen = true;
            selectBotButton.setEnabled(false);
            update();
        }
        else{
            messageLabel.setText("Please choose an opponent before you start the game!");
            messagePanel.setBackground(Color.red);
        }
    }//GEN-LAST:event_selectBotButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aircraftCarrierButton;
    private javax.swing.JButton battleshipButton;
    private javax.swing.JList botBarList;
    private javax.swing.JPanel botBarPanel;
    private view.BotPanel botPanel;
    private javax.swing.JButton cruiserButton;
    private javax.swing.JButton destroyerButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JPanel messagePanel;
    private view.opponentBoardPanel opponentBoardPanel;
    private view.ownBoardPanel ownBoardPanel;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton rocketButton;
    private javax.swing.JButton selectBotButton;
    private javax.swing.JButton submarineButton;
    // End of variables declaration//GEN-END:variables
}
