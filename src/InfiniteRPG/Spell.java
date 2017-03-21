/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

/**
 *
 * @author Jay Speights
 */
abstract public class Spell implements Entity{
    
    private int damage;
    private Sprite sprite;
    private double speed;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double radius;
    private Tile tile;
    private World world;
    
    public Spell(double x, double y, Tile tile, double dx, double dy){
        this.damage = 10;
        this.x = x;
        this.y = y;
        this.speed = .5;
        double scale = speed/Math.sqrt(dx*dx + dy*dy);

        this.dx = scale*dx;
        this.dy = scale*dy;

        this.tile = tile;
        this.world = tile.getChunk().getWorld();
    }
    
    public int getDamage() {
        return damage;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public double getSpeed() {
        return speed;
    }
    
    public void fizzle(){
        
    }
    
    @Override
    public boolean move(){
        return tile.move(x+dx, y+dy, this);
    }

    @Override
    public void __move__(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setTile(Tile t) {
        this.tile = t;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
    
    public void setSprite(Sprite s){
        sprite = s;
    }
    
    public void setRadius(double r){
        radius = r;
    }
    
    public double getRadius(){
        return radius;
    }
    
    public double getOffset(){
        return getRadius();
    }
}
