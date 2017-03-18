/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.util.ArrayList;

/**
 *
 * @author jay
 */
public class Tile {
    
    private final Loc loc;
    private ArrayList<Entity> entities;
    private boolean[][] walls;
    private Chunk chunk;
    private int size = 32;
    
    public Tile(Loc loc, Chunk chunk){
        this.chunk = chunk;
        this.loc = loc;
        walls = new boolean[size][size];
        generate();
    }
    
    public int size(){
        return size;
    }
    
    private void generate(){
        boolean[][] neighbor;
        int start, doorWidth;
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                walls[i][j] = false;
            }
        }
        
        //set top wall
        if (loc.getY() > 0){
            neighbor = chunk.getTile(loc.get(0, -1)).getWalls();
            for (int i = 0; i < size; i++){
                walls[i][0] = neighbor[i][0];
            }
        }
        else if (chunk.getWorld().isChunk(chunk.getLoc().get(0, -1))){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(0, -1)).getTile(loc.get(0, chunk.size()-1)).getWalls();
            for (int i = 0; i < size; i++){
                walls[i][0] = neighbor[i][0];
            }
        }
        else {
            for (int i = 0; i < size; i++){
                walls[i][0] = true;
            }
            doorWidth = (int)(Math.random()*(size-2)+1); // (1 to size-1)
            start = (int)(Math.random()*(size-2-doorWidth)+1);
            for (int i = 0; i < doorWidth; i++){
                walls[i+start][0] = false;
            }
        }
        
        //set left wall
        if (loc.getX() > 0){
            neighbor = chunk.getTile(loc.get(-1, 0)).getWalls();
            System.arraycopy(neighbor[0], 0, walls[0], 0, size);
        }
        else if (chunk.getWorld().isChunk(chunk.getLoc().get(-1, 0))){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(-1, 0)).getTile(loc.get(chunk.size()-1, 0)).getWalls();
            System.arraycopy(neighbor[0], 0, walls[0], 0, size);
        }
        else {
            for (int i = 0; i < size; i++){
                walls[0][i] = true;
            }
            doorWidth = (int)(Math.random()*(size-2)+1); // (1 to size-1)
            start = (int)(Math.random()*(size-2-doorWidth)+1);
            for (int i = 0; i < doorWidth; i++){
                walls[0][i+start] = false;
            }
        }
        
        //set bottom wall
        for (int i = 0; i < size; i++){
                walls[i][size-1] = true;
            }
        doorWidth = (int)(Math.random()*(size-2)+1); // (1 to size-1)
        start = (int)(Math.random()*(size-2-doorWidth)+1);
        for (int i = 0; i < doorWidth; i++){
            walls[i+start][size-1] = false;
        }
        
        //set right wall
        for (int i = 0; i < size; i++){
            walls[size-1][i] = true;
        }
        doorWidth = (int)(Math.random()*(size-2)+1); // (1 to size-1)
        start = (int)(Math.random()*(size-2-doorWidth)+1);
        for (int i = 0; i < doorWidth; i++){
            walls[size-1][i+start] = false;
        }
    }
    
    public boolean[][] getWalls(){
        return walls;
    }
    
    public Loc getLoc(){
        return loc;
    }
    
    public void move(Loc loc, Entity e){
        if (!walls[(int)loc.getX()][(int)loc.getY()]){
            e.__move__(loc);
        }
    }
}
