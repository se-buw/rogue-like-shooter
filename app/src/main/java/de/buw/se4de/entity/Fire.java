package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;

import java.awt.*;
import java.util.Random;

public class Fire extends Enemy {

    Random r = new Random();
    float upperbound = 1.0f;

    public Fire(int x, int y, ID id, Handler handler) {
        super(x, y, id,1,handler,50);
        movementspeed = 2;
        attackrange = 90;
        attackdamage = 1;
    }

    @Override
    public void tick(int deltatick) {
        if(!friendly) {
            speed_x = (handler.player.getX() - getX()) + (r.nextFloat(upperbound) - r.nextFloat(upperbound));//towards player + random movement
            speed_y = (handler.player.getY() - getY()) + (r.nextFloat(upperbound) - r.nextFloat(upperbound));//towards player + random movement
        }else {
            //TODO fiend other flame/or other ideas
        }
        double mult = movementspeed/Math.sqrt(speed_x*speed_x+speed_y*speed_y);

        speed_x *= mult;
        speed_y *= mult;

        x += (int)speed_x * deltatick;
        y += (int)speed_y * deltatick;

        super.tick(deltatick);

        collision();
    }
    @Override
    public void setFriendly(boolean f){
        friendly = true;
    }
    public boolean getFriendly(){return friendly;}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawOval(getX()-(attackrange/2),getY()-(attackrange/2),this.attackrange,this.attackrange);
        if(!friendly)
            g.setColor(Color.red);
        else
            g.setColor(Color.GREEN);

        g.fillRect(x, y, 30, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}
