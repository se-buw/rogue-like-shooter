package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class RangedFire extends Enemy{
    double distance=0;
    public RangedFire(int x, int y, ID id, int hp, Handler h, int ar) {
        super(x, y, id, hp, h, ar);
        oncooldown = 2.0f;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        if(distance <= attackrange)
            g.setColor(Color.RED);
        g.drawOval(getX()-attackrange,getY()-attackrange,this.attackrange*2,this.attackrange*2);
        g.setColor(Color.green);
        g.drawLine(getX(),getY(),handler.player.getX(),handler.player.getY());
        g.drawOval((int)(getX()-distance),(int)(getY()-distance),(int)distance*2,(int)distance*2);
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
            speed_x = (handler.player.getX() - getX());
            speed_y = (handler.player.getY() - getY());

            float smallx = handler.player.getX() - (Math.abs(getX()));
            float smally = handler.player.getY() - (Math.abs(getY()));
            distance = Math.sqrt(smallx*smallx+smally*smally);
            if(distance <= attackrange){
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
            oncooldown = cooldown;
            Fireball fb = new Fireball(getX(),getY(),ID.PROJECTILE,handler.player.getX(),handler.player.getY(),handler,1);
        }
    }
}
