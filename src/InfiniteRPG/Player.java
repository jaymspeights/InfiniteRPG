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
public class Player implements Entity{

    private Tile tile;
    private final String name;
    private final Sprite sprite;
    private int level;
    
    private int constitution;
    private int strength;
    private int dexterity;
    private int agility;
    private int spellpower;
    private int hp;
    
    private double x;
    private double y;
    
    private double dx;
    private double dy;
    private double speed;
    
    public Player(Tile tile, String name, int level){
        this.tile = tile;
        this.name = name;
        this.sprite = new Sprite(name);
        this.level = level;
        
        speed = .2;
        
        int base_ability_score = (level) * (level);
        
        constitution = base_ability_score;
        strength = base_ability_score;
        dexterity = base_ability_score;
        agility = base_ability_score;
        spellpower = base_ability_score;
        
        hp = 20 + level * level + constitution;
        
        for (int i = tile.size() + 1; i < tile.size()*tile.size(); i++){
            if (!tile.getWalls()[i%tile.size()][i/tile.size()]){
                x = i%tile.size();
                y = i/tile.size();
                break;
            }
        }
    }
    
    @Override
    public void move(){
        if (dx != 0 || dy != 0)
            tile.move(dx + x, dy + y, this);
    }
    
    @Override
    public void __move__(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Tile getTile(){
        return tile;
    }
    
    @Override
    public void setTile(Tile t){
        tile = t;
    }
    
    @Override
    public double getX(){
        return x;
    }
    
    @Override
    public double getY(){
        return y;
    }


    public void keyPressed(KeyEvent e) {
        switch (Character.toLowerCase(e.getKeyChar())){
            case 'w':
                dy = -speed;
                break;
                
            case 'a':
                dx = -speed;
                break;
                
            case 's':
                dy = speed;
                break;
                
            case 'd':
                dx = speed;
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
