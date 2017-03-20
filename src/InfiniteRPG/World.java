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
    private final int tileSize = 16;
    private Player player;
    
    
    public World(){
        center = new Loc(0, 0);
        map = new Chunk[size*2+1][size*2+1];
        
        reloadChunks();
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                if(!map[j+size][i+size].isGenerated())
                    save(map[j+size][i+size].generate());
            }
        }
        
        player = new Player(map[size][size].getTile(new Loc(0,0)), "Jay", 0);
    }
    
    private void reloadChunks(){
       for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                map[j+size][i+size] = load(center.get(j, i));
            }
        } 
    }
    private void saveChunks(){
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                save(map[j+size][i+size]);
            }
        }
    }
    private void generateChunks(){
        for (int i = -size; i <= size; i++){
            for (int j = -size; j <= size; j++){
                if(!map[j+size][i+size].isGenerated())
                    map[j+size][i+size].generate();
            }
        }
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public Chunk getChunk(Loc loc){
        if (isChunk(loc))
            return map[loc.getX() - center.getX() + size][loc.getY() - center.getY() + size];
        try {
            Loader loader = new Loader(name(loc), this);
            if (loader.isSuccess())
                return loader.getChunk();
        } catch (Exception ex) {
        }
        return null;
    }
    
    public Chunk loaded_chunk(Loc loc){
        if (isChunk(loc))
            return map[loc.getX() - center.getX() + size][loc.getY() - center.getY() + size];
        return null;
    }
    
    private boolean isChunk(Loc loc){
        if (loc.getX() - center.getX() + size >= 0 && loc.getY() - center.getY() + size >= 0 && loc.getX() - center.getX() <= size && loc.getY() - center.getY() <= size)
            return map[loc.getX() - center.getX() + size][loc.getY() - center.getY() + size].isGenerated();
        return false;
    }
    
    public Tile load(int x, int y, Tile tile){
        center = tile.getChunk().getLoc().get(x, y);
        saveChunks();
        reloadChunks();
        generateChunks();
        return map[size][size].getTile(tile.getLoc().get(x*(1-chunkSize), y*(1-chunkSize)));
    }
    
    private Chunk load(Loc loc){
        Loader loader;
        try {
            loader = new Loader(name(loc), this);
            if (loader.isSuccess())
                return loader.getChunk();
        } catch (Exception ex) {
        }
        return new Chunk(loc, chunkSize, tileSize, this); 
    }

    private void save(Chunk chunk){
        try {
            Loader loader = new Loader(chunk);
        } catch (Exception ex) {
            System.out.println("Save Failed");
            return;
        }
    }
    
    public String name(Loc loc){
        return "chunk"+loc.getX()+"x"+loc.getY()+"y";
    }
}
