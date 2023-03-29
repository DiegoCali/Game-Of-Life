/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
public class GamePanel extends JPanel{
    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;        
    public static ArrayList<Cell> cells = new ArrayList<>();
    public boolean running;
    private Graphics grafs;    
    private Thread hilo = new Thread(new Runnable() {
        @Override
        public void run() {
            while (running) { 
                try {
                    Thread.sleep(100);
                    for(Cell cell: cells){
                        cell.updateNearbyCells();                         
                    }                    
                    for (Cell cell : cells) {
                        cell.updateCondition(grafs);
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }                
            }
        }
    });
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));                
        this.setFocusable(true);  
        this.running = false;
        createCells();
        assignNearbyCells();        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        for (Cell cell : cells) {
            cell.draw(g);
        }
    }
    
    private void createCells(){    
        int i = 0;
        for(int y = 0; y < SCREEN_HEIGHT; y += UNIT_SIZE){
            for(int x = 0; x < SCREEN_WIDTH; x += UNIT_SIZE){               
                Cell newCell = new Cell(x, y, i);
                cells.add(newCell); 
                i++;
            }
        }        
    }
    
    private void assignNearbyCells(){
        for(Cell cell: cells){
            double[] cellCenter = cell.getCenter();
            for(Cell nearby: cells){
                //lados
                boolean rightCell = nearby.isTouching(cellCenter[0] + 25, cellCenter[1]);
                boolean leftCell = nearby.isTouching(cellCenter[0] - 25, cellCenter[1]);
                boolean upperCell = nearby.isTouching(cellCenter[0], cellCenter[1] - 25);
                boolean lowerCell = nearby.isTouching(cellCenter[0], cellCenter[1] + 25);
                //diagonales
                boolean firstSide = nearby.isTouching(cellCenter[0] + 25, cellCenter[1] + 25);
                boolean secondSide = nearby.isTouching(cellCenter[0] - 25, cellCenter[1] + 25);
                boolean thirdSide = nearby.isTouching(cellCenter[0] + 25, cellCenter[1] - 25);
                boolean fourthSide = nearby.isTouching(cellCenter[0] - 25, cellCenter[1] - 25);
                if(rightCell || leftCell || upperCell || lowerCell || firstSide
                        || secondSide || thirdSide || fourthSide){
                    cell.addNearbyCell(nearby);
                }
            }
        }
    }
    
    public void isTouchingaCell(int x, int y, Graphics g){        
        for(Cell cell: cells){                        
            if (cell.isTouching(x, y)){                
                boolean condition = cell.isAlive();                 
                cell.setAlive(!condition);                
                cell.changeColor(g);
                repaint();
            }
        }
    }
    
    public void setConditionRun(){        
        this.running = !this.running; 
        startGameOfLife();
    }
    
    public void setGraphics(Graphics g){
        this.grafs = g;
    }    
    
    private void startGameOfLife(){
        if(running){
           if(!hilo.isAlive()){
                hilo.start();
           }else{
                hilo.resume();
           }
        }else{
            hilo.suspend();
        }
    }
}
