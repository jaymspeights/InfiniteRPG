/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.awt.Image;

/**
 *
 * @author jay
 */
public class Sprite {
    private String name;
    private Image image;
    
    public Sprite(String name){
        this.name = name;
    }
    
    private Image getImage(){
        return image;
    }
}
