package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class GhostFire extends Enemy{
    int fade=0;
    boolean fadingin=true;
    public GhostFire(int x, int y, Handler h) {
        super(x, y, ID.Enemy, 3, h,150 );
        movementspeed = 2;
    }

    @Override
    public void draw(Graphics g) {
        if(friendly)
            g.setColor(Color.GREEN);
        else {
            if (fadingin) {
                fade += 1;
                if (fade >= 100)
                    fadingin = false;
            } else {
                fade -= 1;
                if (fade <= 1)
                    fadingin = true;
            }
            g.setColor(new Color((int) (1.5 * fade), 200, 250));
        }
        g.fillRect(x,y,getSizex(),getSizey());
        super.drawrange(g);
    }
    @Override
    public void collision(){}
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getSizex(),getSizey());
    }
    @Override
    public void attack(){
        if(oncooldown <= 0.0f) {
            oncooldown = cooldown;
            new GhostlyFireball(getX(),getY(),handler.player.getX(),handler.player.getY(),handler);
        }
    }
}
