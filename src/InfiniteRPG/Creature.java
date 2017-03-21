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
public abstract class Creature implements Entity{
    
    private World world;
    
    private Tile tile;
    private final String name;
    private final Sprite sprite;
    private int level;
    
    private int constitution;
    private int strength;
    private int dexterity;
    private int agility;
    private int spellpower;
    private int hp;
    
    private double x;
    private double y;
    
    private double speed;
    
    public Creature(Tile tile, String name, int level){
        this.tile = tile;
        this.world = tile.getChunk().getWorld();
        this.name = name;
        this.sprite = null;//new Sprite(name);
        this.level = level;
        
        speed = .2;
        
        int base_ability_score = (level) * (level);
        
        constitution = base_ability_score;
        strength = base_ability_score;
        dexterity = base_ability_score;
        agility = base_ability_score;
        spellpower = base_ability_score;
        
        hp = 20 + level * level + constitution;
        
        for (int i = tile.size() + 1; i < tile.size()*tile.size(); i++){
            if (!tile.getWalls()[i%tile.size()][i/tile.size()]){
                x = i%tile.size();
                y = i/tile.size();
                break;
            }
        }
    }
    
    @Override
    public boolean move(){
        return true;
    }
    
    @Override
    public Sprite getSprite(){
        return sprite;
    }
    
    @Override
    public void __move__(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Tile getTile(){
        return tile;
    }
    
    @Override
    public void setTile(Tile t){
        tile = t;
    }
    
    @Override
    public double getX(){
        return x;
    }
    
    @Override
    public double getY(){
        return y;
    }
    
    public void setSpeed(double s){
        speed = s;
    }
    public double getSpeed(){
        return speed;
    }
    
    public void attack(double dx, double dy){
        Spell s = new Fireball(x, y, tile, dx, dy);
        world.addSpell(s);
    }
    
    @Override
    public double getOffset(){
        return 0;
    }
    
}
