/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.io.Serializable;

/**
 *
 * @author jay
 */
public class Chunk implements Serializable{
    private World world;
    private int size;
    private int tileSize;
    private Loc loc;
    private Tile[][] map;
    
    public int size(){
        return size;
    }
    
    public Loc getLoc(){
        return loc;
    }
    
    public World getWorld(){
        return world;
    }
    
    public Tile getTile(Loc loc){
        return map[loc.getX()][loc.getY()];
    }
    
    public Chunk(Loc loc, int size, int tileSize, World world){
        this.world = world;
        this.loc = loc;
        this.size = size;
        this.tileSize = tileSize;
        map = new Tile[size][size];
        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                map[j][i] = new Tile(new Loc(j, i), tileSize,this);
            }
        }
    }
    
    @Override
    public String toString(){
        return world.name(loc);
    }
    
}
