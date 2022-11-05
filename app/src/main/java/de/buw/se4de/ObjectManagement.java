package de.buw.se4de;

import java.awt.*;
import java.util.LinkedList;

public class ObjectManagement {

    LinkedList<Object> objects = new LinkedList<Object>();

    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).tick();
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).draw(g);
        }
    }

    public void addObject(Object obj){
        objects.add(obj);
    }

    public void removeObject(Object obj){
        objects.remove(obj);
    }
}
