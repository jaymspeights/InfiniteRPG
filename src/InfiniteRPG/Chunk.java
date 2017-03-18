/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

/**
 *
 * @author jay
 */
public class Chunk {
    private World world;
    private int size = 10;
    private Loc loc;
    private Tile[][] map;
    
    public Chunk(Loc loc, String name, World world){
        this.loc = loc;
        this.world = world;
        //initialize tiles based on file stored under name.txt
    }
    
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
    
    public Chunk(Loc loc, World world){
        this.world = world;
        this.loc = loc;
        map = new Tile[size][size];
        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                map[j][i] = new Tile(new Loc(j, i), this);
            }
        }
    }
    
}
