package de.buw.se4de;

import java.awt.*;

public abstract class GameObject {//TODO entety class
    protected int x,y;
    int sizex = 30;
    int sizey = 30;
    protected float speed_x, speed_y;
    protected ID id;
    public boolean alive=true;

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
    public abstract void draw(Graphics g);

    public int getX() {
        return x + sizex/2;
    }

    public int getY() {
        return y + sizey/2;
    }

    public void setSpeed_x(float speed_x) {
        this.speed_x = speed_x;
    }

    public void setSpeed_y(float speed_y) {
        this.speed_y = speed_y;
    }

    public ID getId() {
        return id;
    }

    public int getHealth(){
        return 0;
    }

    //get bounding rectangle for the collision system
    public abstract Rectangle getBounds();//TODO kreisklasse

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
