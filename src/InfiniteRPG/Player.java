/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.awt.event.KeyEvent;

/**
 *
 * @author Jay Speights
 */
public class Player extends Creature{
    
    private double dx;
    private double dy;
    
    public Player(Tile tile, String name, int level){
        super(tile, name, level);
    }
    
    @Override
    public boolean move(){
        if (dx != 0 || dy != 0)
            return getTile().move(dx + getX(), dy + getY(), this);
        return true;
    }

    public void keyPressed(KeyEvent e) {
        switch (Character.toLowerCase(e.getKeyChar())){
            case 'w':
                dy = -getSpeed();
                break;
                
            case 'a':
                dx = -getSpeed();
                break;
                
            case 's':
                dy = getSpeed();
                break;
                
            case 'd':
                dx = getSpeed();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (Character.toLowerCase(e.getKeyChar())){
            case 'w':
                dy = 0;
                break;
                
            case 'a':
                dx = 0;
                break;
                
            case 's':
                dy = 0;
                break;
                
            case 'd':
                dx = 0;
                break;
        }
    }
}
