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
public class Entity {
    
    private Loc loc;
    
    private Tile tile;
    
    private Sprite sprite;
    
    private Entity target;
    
    private String name;
    private int level;
    
    private int hp;
    
    private int constitution;
    private int strength;
    private int dexterity;
    private int agility;
    private int spellpower;
    
    public Entity(Tile tile, String name, int level){
        this.tile = tile;
        this.name = name;
        this.sprite = new Sprite(name);
        this.level = level;
        
        target = this;
        
        int base_ability_score = (level) * (level);
        
        constitution = base_ability_score;
        strength = base_ability_score;
        dexterity = base_ability_score;
        agility = base_ability_score;
        spellpower = base_ability_score;
        
        hp = 20 + level * level + constitution;
        
        for (int i = tile.size() + 1; i < tile.size()*tile.size(); i++){
            if (!tile.getWalls()[i%tile.size()][i/tile.size()]){
                loc = new Loc(i%tile.size(),i/tile.size());
                break;
            }
        }
    }
    
    public void move(int x, int y){
        tile.move(loc.get(x, y), this);
    }
    
    public void __move__(Loc loc){
        this.loc = loc;
    }
    
    public Tile getTile(){
        return tile;
    }
    
    public Loc getLoc(){
        return loc;
    }
    
}
