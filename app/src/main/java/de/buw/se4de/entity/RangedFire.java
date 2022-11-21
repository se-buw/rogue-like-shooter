package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class RangedFire extends Enemy{
    public RangedFire(int x, int y, ID id, int hp, Handler h, int ar) {
        super(x, y, id, hp, h, ar);
        oncooldown = 3.0f;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        if(Math.sqrt(Math.pow(handler.player.getX() - (getX()-attackrange/2),2) + Math.pow(handler.player.getY()-getY(),2)) <= attackrange)
            g.setColor(Color.RED);
        g.drawOval(getX()-(attackrange/2),getY()-(attackrange/2),this.attackrange,this.attackrange);
        if(!friendly)
            g.setColor(Color.orange);
        else
            g.setColor(Color.GREEN);

        g.fillRect(x, y, getSizex(), getSizey());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getSizex(),getSizey());
    }
    @Override
    public void tick(int deltatick){
        if(!friendly) {
            speed_x = (handler.player.getX() - getX());//towards player + random movement
            speed_y = (handler.player.getY() - getY());//towards player + random movement
            if(Math.sqrt(Math.pow(handler.player.getX() - (getX()-attackrange/2),2) + Math.pow(handler.player.getY()-getY(),2)) <= attackrange){
                attack();
            }
            else{
                double mult = movementspeed/Math.sqrt(speed_x*speed_x+speed_y*speed_y);

                speed_x *= mult;
                speed_y *= mult;

                x += (int)speed_x * deltatick;
                y += (int)speed_y * deltatick;
            }
            oncooldown-= ((float)deltatick) / 60;
            oncooldown-= deltatick / 60;
        }else {
            //TODO fiend other flame/or other ideas
        }
        collision();
    }
    @Override
    public void attack(){
        if(oncooldown <= 0.0f) {
            System.out.println("DIEEE!");
            oncooldown = cooldown;
            int shoot_x = (handler.player.getX() - getX());
            int shoot_y = (handler.player.getY() - getY());
            Fireball fb = new Fireball(getX(),getY(),ID.PROJECTILE,shoot_x,shoot_y,handler,1);
        }
    }
}
