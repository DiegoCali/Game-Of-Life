/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class Cell {
    private int x, y;  
    private double x_med, y_med;
    private boolean alive;
    private Color color;  
    private ArrayList<Cell> nearbyCells;
    private int ID;
    private int counter; //how many cells nearby are alive

    public Cell(int x, int y, int ID) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.color = Color.BLACK;
        this.alive = false;  
        setCenter(x, y);
        nearbyCells = new ArrayList<>();
    }   
    
    private void setCenter(int x, int y){
        this.x_med = (x + 12.5);
        this.y_med = (y + 12.5);
    }
    
    public boolean isTouching(double x, double y){
        boolean touching = false;
        if(x > this.x && x < (this.x + 25)){
            if(y > this.y && y < (this.y + 25)){
                touching = true;
                return touching;
            }
        }
        return touching;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, 25, 25);
        g.setColor(Color.white);
        g.drawRect(x, y, 25, 25);
    }
    
    public void changeColor(Graphics g){
        if(alive){
            this.color = Color.WHITE;
            draw(g);
        }else{
            this.color = Color.BLACK;
            draw(g);
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }   
    
    public void addNearbyCell(Cell cell){
        nearbyCells.add(cell);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public double[] getCenter(){
        double[] center = {this.x_med, this.y_med};
        return center;
    }
    
    public void updateNearbyCells(){   
        counter = 0;
        for(Cell cell: nearbyCells){
            if(cell.isAlive()){
                counter++;
            }
        }
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Cell> getNearbyCells() {
        return nearbyCells;
    } 
    
    public void updateCondition(Graphics g){
        if(this.isAlive()){
            if(this.counter == 2 || this.counter == 3){
                this.alive = true;
                changeColor(g);
            }else if(counter > 3 || counter <= 1){
                this.alive = false;
                changeColor(g);
            }
        }else{
            if(counter == 3){
                this.alive = true;
                changeColor(g);
            }
        }
    }
}
