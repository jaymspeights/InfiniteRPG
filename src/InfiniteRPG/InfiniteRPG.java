/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jay
 */
public class InfiniteRPG extends Applet implements Runnable, KeyListener {
    
    
    Thread thread;
    Graphics buffer;
    Image offscreen;
    int size = 600;
    World world;
    
    @Override
    public void init(){
        resize(600,600);
        thread = new Thread(this);
        offscreen = createImage(size, size);
        buffer = offscreen.getGraphics();
        
        world = new World();
    }
    
    @Override
    public void run() {
        while (true){
            repaint();
            try {
                thread.sleep(100/6);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public void kill(){
        System.out.println("Killing");
        thread = null;
    }
    
    @Override
    public void update(Graphics g) { 
          paint(g); 
     } 
    
    @Override
    public void paint(Graphics g){
        Tile t = world.getPlayer().getTile();
        int scale = size/t.size();
        for (int i = 0; i < t.size(); i++){
            for (int j = 0; j < t.size(); j++){
                if (t.getWalls()[i][j])
                    buffer.fillRect(i*scale, j*scale, scale, scale);
            }
        }
        buffer.fillOval(world.getPlayer().getLoc().getX()*scale, world.getPlayer().getLoc().getY()*scale, scale, scale);
        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
