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
    private final int size = 1;
    private final int chunkSize = 2;
    private final int tileSize = 32;
    private Player player;
    
    
    public World(){
        center = new Loc(0, 0);
        map = new Chunk[size*2+1][size*2+1];
        
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                map[j+size][i+size] = new Chunk(new Loc(j, i), chunkSize, tileSize, this);
            }
        }
        
        player = new Player(getChunk(center).getTile(new Loc(0,0)), "Jay", 0);
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public Chunk getChunk(Loc loc){
        if (isChunk(loc))
            return map[loc.getX() - center.getX() + size][loc.getY() - center.getY() + size];
        Loader loader = new Loader(name(loc));
        if (loader.isSuccess())
            return loader.getChunk();
        else
            return null;
    }
    
    private boolean isChunk(Loc loc){
        return loc.getX() - center.getX() + size >= 0 && loc.getY() - center.getY() + size >= 0 && loc.getX() - center.getX() <= size && loc.getY() - center.getY() <= size;
    }
    
    public Tile load(int x, int y, Tile tile){
        System.out.println(center);
        System.out.println(tile.getChunk().getLoc());
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                System.out.print(new Loc(j + center.getX(), i + center.getY()));
            }
            System.out.print('\n');
        }
        center = tile.getChunk().getLoc().get(x, y);
        System.out.println(center);
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                save(map[j+size][i+size]);
                map[j+size][i+size] = load(new Loc(j + center.getX(), i + center.getY()));
            }
        }
        System.out.println();
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                System.out.print(new Loc(j + center.getX(), i + center.getY()));
            }
            System.out.print('\n');
        }
        return map[size][size].getTile(tile.getLoc().get(x*(1-chunkSize), y*(1-chunkSize)));
    }
    
    private Chunk load(Loc loc){
        Loader loader = new Loader(name(loc));
        if (loader.isSuccess())
            return loader.getChunk();
        return new Chunk(loc, chunkSize, tileSize, this);
    }
    
    private void save(Chunk chunk){
        Loader loader = new Loader(chunk);
    }
    
    public String name(Loc loc){
        return "chunk"+loc.getX()+"x"+loc.getY()+"y";
    }
}
