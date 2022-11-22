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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Handler {
    //used to manage all objects we have in the game
    public LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();//TODO private
    public LinkedList<Projectile> projectiles = new LinkedList<Projectile>();//TODO better container
    private boolean up = false, down = false, right = false, left = false;
    public int wave=0;
    public GUI gui;//TODO gui private
    public boolean game_isrunning = false;
    public Firefighter player = new Firefighter(50, 50, ID.Player, this,4);
    Vector<Spawnarea> Spawn;


    public void tick(int deltatick){
        player.tick(deltatick);
        for(Iterator<GameObject> iterator = gameObjects.iterator(); iterator.hasNext();) {//TODO renew all loops //changed loops so we dont skip shit
            GameObject ob = iterator.next();
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
        for (Spawnarea s: Spawn)
            s.draw(g);
        for (GameObject ob : gameObjects)//TODO renew all loops //changed loops so we dont skip shit
            ob.draw(g);
        for (Projectile p : projectiles)//TODO renew all loops //changed loops so we dont skip shit
            p.draw(g);
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

    public void removeProjectile(Projectile p){
        projectiles.remove(p);
    }
    public void clear(){
        gameObjects.clear();
        projectiles.clear();
        up = false; down = false; right = false; left = false;
        wave = 0;
        player = new Firefighter(50, 50, ID.Player, this,4);
        gui = new GUI(0,0,ID.GUI,player);
    }
    private void newwave(){
        ++wave;

    }
    public void changemap(Map m) {
        clear();
        if(m.vwall != null) {
            gameObjects.addAll(m.vwall);
        }
        Spawn = new Vector<>();
        Spawn.addAll(m.vspawn);
    }

    public void Spawn(int wave){

    }
}
