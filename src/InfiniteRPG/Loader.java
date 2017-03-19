/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfiniteRPG;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jay Speights
 */
public class Loader {

    public Chunk getChunk() {
        return chunk;
    }

    public String getFilename() {
        return filename;
    }

    public Boolean isSuccess() {
        return success;
    }
    
    private Chunk chunk = null;
    private String filename = null;
    private Boolean success = false;
    
    public Loader(Chunk chunk){
        this.chunk = chunk;
        this.filename = "Chunks/"+chunk.toString()+".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            oos.writeObject(chunk);
            this.success = true;
	} catch (Exception e) {
	}
    }
    
    public Loader(String filename){
        this.filename = "Chunks/"+filename + ".ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filename))) {
            this.chunk = (Chunk) ois.readObject();
            this.success = true;
        } catch (Exception e) {
        }
    }
}
