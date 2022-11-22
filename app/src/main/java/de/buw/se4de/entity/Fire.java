package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;

import java.awt.*;

public class Fire extends Enemy {

    public Fire(int x, int y, Handler handler) {
        super(x, y, ID.Enemy,1,handler,30);
        movementspeed = 2;
        attackdamage = 1;
        health=2;
    }
    @Override
    public void setFriendly(boolean f){
        friendly = true;
    }
    @Override
    public void draw(Graphics g) {
        if(!friendly)
            g.setColor(Color.red);
        else
            g.setColor(Color.GREEN);

        g.fillRect(x, y, 30, 30);
        super.drawrange(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}
