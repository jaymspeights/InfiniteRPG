package InfiniteRPG;

import java.io.Serializable;

public class Loc implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int x;
    private int y;
    
    public Loc(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setLoc(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void move(int x_velocity, int y_velocity){
        x += x_velocity;
        y += y_velocity;
    }
    
    public Loc get(int x, int y){
        return new Loc(this.x + x, this.y + y);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    @Override
    public String toString(){
       return "(" + x + ", " + y + ")"; 
    }
    
}
