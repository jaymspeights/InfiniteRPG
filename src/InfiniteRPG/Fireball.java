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
public class Fireball extends Spell{
     
    public Fireball(double x, double y, Tile tile, double dx, double dy){
        super(x, y, tile, dx, dy);
        setRadius(.5);
        System.out.println((int)(getRadius()*tile.getChunk().getWorld().getScale()));
        setSprite(new Sprite("Fireball.gif", 2*(int)(getRadius()*tile.getChunk().getWorld().getScale())));
    }

    
}
