package de.buw.se4de;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    //used to manage all objects we have in the game

    LinkedList<Object> objects = new LinkedList<Object>();//TODO private
    private boolean up = false, down = false, right = false, left = false;
    public int amountOfFires = 0;
    public GUI gui;//TODO gui private
    public boolean game_isrunning = false;
    public Firefighter player = new Firefighter(50, 50, ID.Player, this,4);;

    public void tick(int deltatick){
        player.tick(deltatick);
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).tick(deltatick);
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).draw(g);
        }
        player.draw(g);
        gui.draw(g);
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

    public void addGUI(GUI g){
        gui = g;
    }
}
