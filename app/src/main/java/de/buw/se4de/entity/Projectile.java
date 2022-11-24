package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.GameObject;

import java.awt.*;
public abstract class Projectile extends GameObject {
    Handler handler;
    double speed = 7;
    int damage;
    int speed_multiplier;
    public Projectile(int x, int y, ID id, int dir_x, int dir_y, Handler handler) {
        super(x, y, id);
        calculateSpeed(x, y, dir_x, dir_y);
        this.handler = handler;
        handler.addProjectile(this);
    }
    public Projectile(int x, int y, ID id, int dir_x, int dir_y, Handler handler,int sp) {
        super(x, y, id);
        speed=sp;
        calculateSpeed(x, y, dir_x, dir_y);
        this.handler = handler;
        handler.addProjectile(this);

    }
    public Projectile(int x, int y, ID id, int dir_x, int dir_y, Handler handler,int sizex,int sizey) {
        super(x, y,sizex,sizey,id);
        calculateSpeed(x, y, dir_x, dir_y);
        this.handler = handler;
        handler.addProjectile(this);
    }
    public void calculateSpeed(int fromX, int fromY, int toX, int toY) {
        double mult = speed/Math.sqrt((toY - fromY)*(toY - fromY) + (toX - fromX)*(toX - fromX));
        this.speed_y = (float)((toY - fromY)*mult);
        this.speed_x = (float)((toX - fromX)*mult);
    }

    public void tick(int deltatick) {
        x += speed_x * deltatick * (1+speed_multiplier*0.1);
        y += speed_y * deltatick * (1+speed_multiplier*0.1);

        collision();
    }
    protected abstract void collision();
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getSizex(),getSizey());
    }
}
