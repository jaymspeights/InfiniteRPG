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
public class World {
    
    private Loc center;
    private Chunk[][] map;
    private int size = 5;
    private Entity player;
    
    public World(){
        center = new Loc(0, 0);
        map = new Chunk[size*2+1][size*2+1];
        
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                map[j+size][i+size] = new Chunk(new Loc(j, i), this);
            }
        }
        
        player = new Entity(getChunk(center).getTile(new Loc(0,0)), "Jay", 0);
    }
    
    public Entity getPlayer(){
        return player;
    }
    
    //add OutOfBounds handling!!!
    public Chunk getChunk(Loc loc){
        return map[loc.getX() - center.getX() + size][loc.getY() - center.getY() + size];
    }
    
    //add memory checking for saved chunks!!!
    public boolean isChunk(Loc loc){
        return loc.getX() - center.getX() + size >= 0 && loc.getY() - center.getY() + size >= 0;
    }
}
