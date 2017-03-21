/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author jay
 */
public class Sprite {
    private String name;
    private Image image;
    
    public Sprite(String name, int size){
        this.name = name;
        try {
            image = ImageIO.read(new File("Sprites/"+name)).getScaledInstance(size, size, Image.SCALE_SMOOTH);
        } catch (Exception e) {
        }
    }
    
    public Image getImage(){
        return image;
    }
}
