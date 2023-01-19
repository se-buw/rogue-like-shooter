package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

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

    @Override
    public void draw(Graphics g, int deltatick) {
        spriteTimer += deltatick;
        if (spriteTimer > spriteUpdateTime) {
            spriteTimer = spriteTimer % spriteUpdateTime;
            spriteFrame = (spriteFrame + 1) % spriteFrames;
        }
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform old = g2d.getTransform();
        g2d.translate(getX(), getY());
        g2d.rotate(Math.atan2(speed_y, speed_x));
        g2d.drawImage(sprite, -getSizex(), -getSizey(), getSizex(), getSizey(), spriteFrame*spriteSize, 0, (spriteFrame+1)*spriteSize, spriteSize, null);
        g2d.setTransform(old);
    }

    protected abstract void collision();
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getSizex(),getSizey());
    }
}
