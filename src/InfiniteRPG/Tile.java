/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jay
 */
public class Tile implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private final Loc loc;
    private boolean[][] walls;
    private transient Chunk chunk;
    private int size;
    
    public Tile(Loc loc, int size, Chunk chunk){
        this.size = size;
        this.chunk = chunk;
        this.loc = loc;
        walls = new boolean[size][size];
        generate();
    }
    
    public void setChunk(Chunk c){
        chunk = c;
    }
    
    public int size(){
        return size;
    }
    
    public Chunk getChunk(){
        return chunk;
    }
    
    private int random_door(){
        return (int)(Math.random()*(size)*1.2-size/5)-1; // (1-size/2 to size-1)
    }
    
    private void generate(){
        boolean[][] neighbor;
        Chunk c;
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
                walls[i][0] = neighbor[i][size-1];
            }
        }
        else if (chunk.getWorld().getChunk(chunk.getLoc().get(0, -1))!=null){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(0, -1)).getTile(loc.get(0, chunk.size()-1)).getWalls();
            for (int i = 0; i < size; i++){
                walls[i][0] = neighbor[i][size-1];
            }
        }
        else {
            for (int i = 0; i < size; i++){
                walls[i][0] = true;
            }
            doorWidth = random_door();
            if (doorWidth > 1){
                start = (int)(Math.random()*(size-2-doorWidth)+1);
                for (int i = 0; i < doorWidth && i + start< size-1; i++){
                    walls[i+start][0] = false;
                }
            }
            
        }
        
        //set left wall
        if (loc.getX() > 0){
            neighbor = chunk.getTile(loc.get(-1, 0)).getWalls();
            System.arraycopy(neighbor[size-1], 0, walls[0], 0, size);
        }
        else if (chunk.getWorld().getChunk(chunk.getLoc().get(-1, 0))!=null){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(-1, 0)).getTile(loc.get(chunk.size()-1, 0)).getWalls();
            System.arraycopy(neighbor[size-1], 0, walls[0], 0, size);
        }
        else {
            for (int i = 0; i < size; i++){
                walls[0][i] = true;
            }
            doorWidth = random_door();
            if (doorWidth > 1){
                start = (int)(Math.random()*(size-2-doorWidth)+1);
                for (int i = 0; i < doorWidth && i + start < size-1; i++){
                    walls[0][i+start] = false;
                }
            }       
        }
        
        //set bottom wall
        if (loc.getY() == chunk.size()-1 && chunk.getWorld().getChunk(chunk.getLoc().get(0, 1))!=null){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(0, 1)).getTile(loc.get(0, 1-chunk.size())).getWalls();
            for (int i = 0; i < size; i++){
                walls[i][size-1] = neighbor[i][0];
            }
        }
        else {
            for (int i = 0; i < size; i++){
                walls[i][size-1] = true;
            }
            doorWidth = random_door();
            if (doorWidth > 1){
                start = (int)(Math.random()*(size-2-doorWidth)+1);
                for (int i = 0; i < doorWidth && i + start< size-1; i++){
                    walls[i+start][size-1] = false;
                }
            }
            
        }
        
        //set right wall
        if (loc.getX() == chunk.size()-1 && chunk.getWorld().getChunk(chunk.getLoc().get(1, 0))!=null){
            neighbor = chunk.getWorld().getChunk(chunk.getLoc().get(1, 0)).getTile(loc.get(1-chunk.size(), 0)).getWalls();
            System.arraycopy(neighbor[0], 0, walls[size-1], 0, size);
        }
        else {
            for (int i = 0; i < size; i++){
                walls[size-1][i] = true;
            }
            doorWidth = random_door();
            if (doorWidth > 1){
                start = (int)(Math.random()*(size-2-doorWidth)+1);
                for (int i = 0; i < doorWidth && i + start < size-1; i++){
                    walls[size-1][i+start] = false;
                }
            }       
        }
    }
    
    public boolean[][] getWalls(){
        return walls;
    }
    
    public Loc getLoc(){
        return loc;
    }
    
    public void move(double x, double y, Entity e){
        //left wall
        if (x < 0) {
            e.__move__(size + x - 1, y);
            if (loc.getX() > 0){
                e.setTile(chunk.getTile(loc.get(-1, 0)));
            }
            else if (chunk.getWorld().chunk(chunk.getLoc().get(-1, 0))!=null){
                e.setTile(chunk.getWorld().getChunk(chunk.getLoc().get(-1, 0)).getTile(loc.get(chunk.size()-1, 0)));
            }
            else {
                e.setTile(chunk.getWorld().load(-1, 0, this));
            }
        }
        //right wall
        else if (x >= size-1) {
            e.__move__(x - size + 1, y);
            if (loc.getX() < chunk.size()-1){
                e.setTile(chunk.getTile(loc.get(1, 0)));
            }
            else if (chunk.getWorld().chunk(chunk.getLoc().get(1, 0))!=null){
                e.setTile(chunk.getWorld().getChunk(chunk.getLoc().get(1, 0)).getTile(loc.get(1-chunk.size(), 0)));
            }
            else {
                e.setTile(chunk.getWorld().load(1, 0, this));
            }
        }
        //top wall
        else if (y < 0) {
            e.__move__(x, size + y - 1);
            if (loc.getY() > 0){
                e.setTile(chunk.getTile(loc.get(0, -1)));
            }
            else if (chunk.getWorld().chunk(chunk.getLoc().get(0, -1))!=null){
                e.setTile(chunk.getWorld().getChunk(chunk.getLoc().get(0, -1)).getTile(loc.get(0, chunk.size()-1)));
            }
            else {
                e.setTile(chunk.getWorld().load(0, -1, this));
            }
        }
        //bottom wall
        else if (y >= size-1) {
            e.__move__(x, y - size + 1);
            if (loc.getY() < chunk.size()-1){
                e.setTile(chunk.getTile(loc.get(0, 1)));
            }
            else if (chunk.getWorld().chunk(chunk.getLoc().get(0, 1))!=null){
                e.setTile(chunk.getWorld().getChunk(chunk.getLoc().get(0, 1)).getTile(loc.get(0, 1-chunk.size())));
            }
            else {
                e.setTile(chunk.getWorld().load(0, 1, this));
            }
        }
        else if (!walls[(int)x][(int)y] && !walls[(int)(x+1)][(int)(y+1)] && !walls[(int)(x)][(int)(y+1)] && !walls[(int)(x+1)][(int)(y)]){
            e.__move__(x, y);
        }
    }
}
