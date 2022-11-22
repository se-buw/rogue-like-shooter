package de.buw.se4de.gameflow;

import de.buw.se4de.Wave;
import de.buw.se4de.entity.Firefighter;
import de.buw.se4de.GUI;
import de.buw.se4de.ID;
import de.buw.se4de.GameObject;
import de.buw.se4de.entity.Projectile;
import de.buw.se4de.map.Map;
import de.buw.se4de.map.Spawnarea;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Handler {
    //used to manage all objects we have in the game
    public LinkedList<GameObject> gameObjects = new LinkedList<>();
    public LinkedList<Projectile> projectiles = new LinkedList<>();
    private boolean up = false, down = false, right = false, left = false;
    public Wave wave = new Wave(this);
    public GUI gui;
    public boolean game_isrunning = false;
    public Firefighter player = new Firefighter(50, 50, ID.Player, this,4);

    Vector<Spawnarea> Spawn = new Vector<>();


    public void tick(int deltatick){
        player.tick(deltatick);
        boolean spawnnewwave=false;
        for(Iterator<GameObject> iterator = gameObjects.iterator(); iterator.hasNext();) {
            GameObject ob = iterator.next();
            ob.tick(deltatick);
            if(!ob.alive) {
                spawnnewwave = wave.onkill();
                iterator.remove();
            }
        }
        for(Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
            Projectile p = iterator.next();
            p.tick(deltatick);
            if(!p.alive)
                iterator.remove();
        }
        wave.tick(deltatick);
        if(spawnnewwave)
            wave.newwave();
    }

    public void draw(Graphics g) {
        for (Spawnarea s: Spawn)
            s.draw(g);
        try {
            for (GameObject ob : gameObjects)
                ob.draw(g);
        }catch (ConcurrentModificationException ignored){}
        try {
            for (Projectile p : projectiles)
                p.draw(g);
        }catch (ConcurrentModificationException ignored){}
        player.draw(g);
        gui.draw(g);
    }

    public void addObject(GameObject obj){
        gameObjects.add(obj);
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

    public void clear(){
        gameObjects.clear();
        projectiles.clear();
        up = false; down = false; right = false; left = false;
        player = new Firefighter(50, 50, ID.Player, this,4);
        gui = new GUI(0,0,ID.GUI,player);
        wave.setWave(0);
    }

    public void changemap(Map m) {
        clear();
        if(m.vwall != null) {
            gameObjects.addAll(m.vwall);
        }
        Spawn.addAll(m.vspawn);
        wave.changespawn(m.vspawn);
    }

}
