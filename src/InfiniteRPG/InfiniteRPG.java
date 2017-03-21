/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jay
 */
public class InfiniteRPG extends Applet {
    
    
    Thread thread;
    Graphics buffer;
    Image offscreen;
    int size = 600;
    World world;
    int count = 0;
    Player player;
    private int scale;
    
    private Timer timer;
    private final int DELAY = 10;
    
    @Override
    public void init(){
        resize(size, size+10);
        
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        
        offscreen = createImage(size, size);
        buffer = offscreen.getGraphics();
        
        world = new World();
        player = world.getPlayer();
        
        Tile t = player.getTile();
        world.setScale(size/t.size());
        scale = world.getScale();
        System.out.println(scale);
        
        timer = new Timer(true);
        timer.schedule(new Step(), 0, 10);
    }
    
    
    @Override
    public void update(Graphics g) { 
          paint(g); 
     } 
    
    @Override
    public void paint(Graphics g){
        Tile t = player.getTile();
        for (int i = 0; i < t.size(); i++){
            for (int j = 0; j < t.size(); j++){
                if (t.getWalls()[i][j])
                    buffer.fillRect(i*scale, j*scale, scale, scale);
            }
        }
        for (Entity e: world.getSpells()){
            buffer.drawImage(e.getSprite().getImage(), (int)((e.getX()-e.getOffset())*scale), (int)((e.getY()-e.getOffset())*scale-e.getOffset()), this);
        }
        buffer.fillOval((int)(player.getX()*scale), (int)(player.getY()*scale), scale, scale);
        buffer.drawString("chunk: "+t.getChunk()+" tile: "+t.getLoc(), 0, size);
        g.drawImage(offscreen, 0, 0, this);
        buffer.clearRect(0, 0, size, size);
    }
    
    private class Step extends TimerTask {

        @Override
        public void run() {
            world.step();
            repaint();
        }
        
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
    
    private class MAdapter extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            player.attack(e.getX()-player.getX()*scale, e.getY()-player.getY()*scale);
        }
    }
 
}
