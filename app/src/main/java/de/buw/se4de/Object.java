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

    public abstract void tick();
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeed_x() {
        return speed_x;
    }

    public void setSpeed_x(float speed_x) {
        this.speed_x = speed_x;
    }

    public float getSpeed_y() {
        return speed_y;
    }

    public void setSpeed_y(float speed_y) {
        this.speed_y = speed_y;
    }

    public ID getId() {
        return id;
    }

    public int getHearts(){
        return 0;
    }

    public void setId(ID id) {
        this.id = id;
    }

    //get bounding rectangle
    public abstract Rectangle getBounds();

}
