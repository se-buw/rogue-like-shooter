package de.buw.se4de;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected int x,y;
    int sizex = 30;
    int sizey = 30;
    protected float speed_x, speed_y;
    protected ID id;
    public boolean alive=true;
    protected BufferedImage sprite;
    protected int spriteFrame = 0; // current frame
    protected int spriteFrames = 1; // how many frames the sprite has
    protected int spriteSize = 1; // the size (x,y) of the sprite
    protected int spriteUpdateTime = 1000; // how often the sprite updates (with 1~10ms)
    protected int spriteTimer = 0; // internal timer

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public GameObject(int x, int y, int w, int h, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        sizex=w;
        sizey=h;
    }

    //update of the object
    public abstract void tick(int deltatick);
    //rendering of the object
    public abstract void draw(Graphics g, int deltatick);

    public int getX() {
        return x + sizex/2;
    }

    public int getY() {
        return y + sizey/2;
    }

    public ID getId() {
        return id;
    }

    public int getHealth(){
        return 0;
    }

    //get bounding rectangle for the collision system
    public abstract Rectangle getBounds();

    public int getSizex() {
        return sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void kill() {
        alive = false;
    }
}
