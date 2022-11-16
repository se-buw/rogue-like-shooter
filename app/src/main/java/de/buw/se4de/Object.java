package de.buw.se4de;

import java.awt.*;

public abstract class Object {
    protected int x,y;
    protected float speed_x, speed_y;
    protected ID id;

    public Object (int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //update of the object
    public abstract void tick(int deltatick);
    //rendering of the object
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

}
