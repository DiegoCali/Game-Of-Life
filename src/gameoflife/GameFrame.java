/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import java.awt.HeadlessException;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class GameFrame extends JFrame{
    
    private final GamePanel panel = new GamePanel();
    public GameFrame() throws HeadlessException {        
        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                panelMouseClicked(e);
            }
        });
        this.panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                panelKeyPressed(e);
            }
        });
        this.add(panel);
        this.setTitle("The game of life (By Diego Cali)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null); 
        this.panel.setGraphics(panel.getGraphics());
    }
    
    public void panelMouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();        
        panel.isTouchingaCell(x, y, panel.getGraphics());
    }    
    
    public void panelKeyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){           
            panel.setConditionRun();
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            panel.restart(panel.getGraphics());
        }
    }
        
}
