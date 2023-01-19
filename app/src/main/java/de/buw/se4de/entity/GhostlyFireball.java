package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class GhostlyFireball extends Projectile{
    public GhostlyFireball(int x, int y, int dir_x, int dir_y, Handler handler) {
        super(x-15, y-15, ID.PROJECTILE, dir_x, dir_y, handler,3);
        damage = 2;
        spriteSize = 64;
        spriteFrames = 60;
        spriteUpdateTime = 1;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/ghostball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, int deltatick) {
        super.draw(g, deltatick);
        // g.fillOval(getX(),getY(),getSizex(),getSizey());
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
