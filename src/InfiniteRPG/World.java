/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.util.ArrayList;
import java.util.ListIterator;


/**
 *
 * @author jay
 */
public class World {
    
    private Loc center;
    private Chunk[][] map;
    private final int size = 3;
    private final int chunkSize = 5;
    private final int tileSize = 32;
    private Player player;
    private ArrayList<Creature> creatures = new ArrayList();
    private ArrayList<Spell> spells = new ArrayList();
    private int scale;
    
    
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
        
        creatures.add(player = new Player(map[size][size].getTile(new Loc(0,0)), "Jay", 0));
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
    
    public void setScale(int s){
        scale = s;
    }
    
    public int getScale(){
        return scale;
    }
    
    public void addCreature(Creature e){
        creatures.add(e);
    }
    
    public void removeCreature(Creature e){
        creatures.remove(e);
    }
    
    public void addSpell(Spell s){
        spells.add(s);
    }
    
    public void removeSpell(Spell s){
        spells.remove(s);
    }
    public ArrayList<Spell> getSpells(){
        return spells;
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
    
    public void step(){
        ListIterator<Creature> lic = creatures.listIterator();
        while (lic.hasNext()){
            if(!lic.next().move())
                lic.remove();
        }
        ListIterator<Spell> lis = spells.listIterator();
        while (lis.hasNext()){
            if(!lis.next().move())
                lis.remove();
        }
    }
}
