package de.buw.se4de;

import java.awt.*;
import java.util.LinkedList;

public class ObjectManagement {

    LinkedList<Object> objects = new LinkedList<Object>();
    private boolean up = false, down = false, right = false, left = false;

    public void tick(){
        for (Object object : objects) {
            object.tick();
        }
    }

    public void draw(Graphics g){
        for (Object object : objects) {
            object.draw(g);
        }
    }

    public void addObject(Object obj){
        objects.add(obj);
    }

    public void removeObject(Object obj){
        objects.remove(obj);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}