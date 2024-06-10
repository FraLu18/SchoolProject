package controller;

import java.awt.event.MouseEvent;
import javax.swing.Timer;
import model.Game;
import players.Bot;
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
    private boolean enabled = true;
    
    public MainFrame() {
        initComponents();
        game = new Game();
        ownBoardPanel.setGame(game);
        timer = new Timer(100, evt -> botShoot());
        opponentBoardPanel.setGame(game);
        aircraftCarrierButton.setEnabled(true);
        battleshipButton.setEnabled(false);
        submarineButton.setEnabled(false);
        cruiserButton.setEnabled(false);
        destroyerButton.setEnabled(false);
        update();
        setLocationRelativeTo(null);
    }
    
    public void update(){
        ownBoardPanel.repaint();
        opponentBoardPanel.repaint();
    }
    
    public void botShoot(){
        while(game.getTurn()==false){
            game.botShoot();
        }
        System.out.println("STOP");
        System.out.println("-----------------");
        timer.stop();
        update();
        enabled = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ownBoardPanel = new view.ownBoardPanel();
        opponentBoardPanel = new view.opponentBoardPanel();
        aircraftCarrierButton = new javax.swing.JButton();
        battleshipButton = new javax.swing.JButton();
        submarineButton = new javax.swing.JButton();
        cruiserButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        destroyerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(169, 169, 169));

        jPanel1.setBackground(new java.awt.Color(112, 128, 144));

        ownBoardPanel.setPreferredSize(new java.awt.Dimension(400, 400));
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

        jButton5.setBackground(new java.awt.Color(153, 0, 0));
        jButton5.setText("RESTART");

        destroyerButton.setBackground(new java.awt.Color(64, 96, 160));
        destroyerButton.setText("Destroyer");
        destroyerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destroyerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(opponentBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ownBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(submarineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(battleshipButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aircraftCarrierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cruiserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(destroyerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
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
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ownBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opponentBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opponentBoardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opponentBoardPanelMousePressed
        int x = evt.getX();
        int y = evt.getY();

        int col = x / (opponentBoardPanel.getWidth() / 10);
        int row = y / (opponentBoardPanel.getHeight() / 10);
        
        //if(game.getNumOfPlacedShips()==5 && game.getTurn()==true && enabled == true){
            game.playerShoot(col, row);
            update();
            if(game.getTurn()==false){
                System.out.println("-----------------");
                System.out.println("StART");
                enabled = false;
                timer.start();
            }
        //}
    }//GEN-LAST:event_opponentBoardPanelMousePressed

    private void ownBoardPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ownBoardPanelMousePressed

        if(evt.getButton() == MouseEvent.BUTTON1){
            int x = evt.getX();
            int y = evt.getY();

            int col = x / (opponentBoardPanel.getWidth() / 10);
            int row = y / (opponentBoardPanel.getHeight() / 10);

            if(game.isShipSelected()==true){
                game.playerSetShip(col, row);
                //player.setShip(col, row, game.getShipLength(), game.getShipHeight(), game.getShipType());
                update();
            }
        }
        if(evt.getButton() == MouseEvent.BUTTON3){
           if(game.isShipSelected()){
            game.changeDirection();
            System.out.println("You changed the direction to " + game.getDirection());
            update();
           } 
        }
        
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
            System.out.println(game.getNumOfPlacedShips());
            game.setPlayerShipsList();
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
    private javax.swing.JButton cruiserButton;
    private javax.swing.JButton destroyerButton;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private view.opponentBoardPanel opponentBoardPanel;
    private view.ownBoardPanel ownBoardPanel;
    private javax.swing.JButton submarineButton;
    // End of variables declaration//GEN-END:variables
}
