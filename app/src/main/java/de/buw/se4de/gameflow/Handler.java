package de.buw.se4de.gameflow;

import de.buw.se4de.entity.Firefighter;
import de.buw.se4de.GUI;
import de.buw.se4de.ID;
import de.buw.se4de.Object;
import de.buw.se4de.entity.Projectile;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {
    //used to manage all objects we have in the game
    public LinkedList<Object> objects = new LinkedList<Object>();//TODO private
    public LinkedList<Projectile> projectiles = new LinkedList<Projectile>();//TODO better container
    private boolean up = false, down = false, right = false, left = false;
    public int wave=0;
    public GUI gui;//TODO gui private
    public boolean game_isrunning = false;
    public Firefighter player = new Firefighter(50, 50, ID.Player, this,4);

    public void tick(int deltatick){
        player.tick(deltatick);
        for(Iterator<Object> iterator = objects.iterator(); iterator.hasNext();) {//TODO renew all loops //changed loops so we dont skip shit
            Object ob = iterator.next();
            ob.tick(deltatick);
            if(!ob.alive)
                iterator.remove();
        }

        for(Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {//TODO renew all loops //changed loops so we dont skip shit
            Projectile p = iterator.next();
            p.tick(deltatick);
            if(!p.alive)
                iterator.remove();
        }
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {//TODO renew all loops //changed loops so we dont skip shit
            p.draw(g);
        }

        for (Object ob : objects) {//TODO renew all loops //changed loops so we dont skip shit
            ob.draw(g);
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

    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    public void removeProjectile(Projectile p){
        projectiles.remove(p);
    }
    public void clear(){
        objects.clear();
        projectiles.clear();
        up = false; down = false; right = false; left = false;
        wave = 0;
        player = new Firefighter(50, 50, ID.Player, this,4);
        gui = new GUI(0,0,ID.GUI,player);
    }
}
