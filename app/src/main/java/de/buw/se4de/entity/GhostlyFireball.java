package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class GhostlyFireball extends Projectile{
    int fade = 0;
    boolean fadingin = true;
    public GhostlyFireball(int x, int y, int dir_x, int dir_y, Handler handler) {
        super(x-25, y-25, ID.PROJECTILE, dir_x, dir_y, handler,3);
        damage = 2;
    }

    @Override
    public void draw(Graphics g) {
        if(fadingin) {
            fade += 1;
            if (fade >= 100)
                fadingin = false;
        }
        else {
            fade -= 1;
            if(fade<=1)
                fadingin=true;
        }

        g.setColor(new Color((int)(1.5*fade),200, 250));
        g.fillOval(getX(),getY(),getSizex(),getSizey());
    }

    @Override
    protected void collision() {
        if(x<=0 || y<=0)
            kill();
        if(handler.player.getBounds().intersects(this.getBounds())){
            handler.player.takedamage(damage);
            kill();
        }
    }
}
