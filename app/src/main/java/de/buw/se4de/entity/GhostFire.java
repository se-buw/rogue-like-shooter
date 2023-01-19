package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class GhostFire extends Enemy{
    public GhostFire(int x, int y, Handler h) {
        super(x, y, ID.Enemy, 3, h,150 );
        movementspeed = 2;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/ghost.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteFrames = 4;
        spriteSize = 8;
        spriteUpdateTime = 8;
    }

    @Override
    public void draw(Graphics g, int deltatick) {
        super.draw(g, deltatick);
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
