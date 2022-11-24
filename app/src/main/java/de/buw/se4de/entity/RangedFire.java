package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class RangedFire extends Enemy{
    public RangedFire(int x, int y, Handler h) {
        super(x, y, ID.Enemy, 2, h, 250);
        oncooldown = 2.0f;
    }
    @Override
    public void draw(Graphics g) {
        if(!friendly)
            g.setColor(Color.orange);
        else
            g.setColor(Color.GREEN);

        g.fillRect(x, y, getSizex(), getSizey());
        super.drawrange(g);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getSizex(),getSizey());
    }
    @Override
    public void attack(){
        if(oncooldown <= 0.0f) {
            oncooldown = cooldown;
            new Fireball(getX(),getY(),handler.player.getX(),handler.player.getY(),handler,1);
        }
    }
}
