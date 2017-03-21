/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

/**
 *
 * @author Jay Speights
 */
public interface Entity {
    public boolean move();
    
    public void __move__(double x, double y);
    
    public Tile getTile();
    
    public void setTile(Tile t);
    
    public double getX();
    
    public double getY();
    
    public Sprite getSprite();
    
    public double getOffset();
}
