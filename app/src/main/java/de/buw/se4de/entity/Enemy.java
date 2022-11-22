package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.GameObject;

import java.awt.*;

public abstract class Enemy extends GameObject {
    boolean friendly=false;
    protected int health;
    protected int attackrange;
    protected int attackdamage;
    protected int movementspeed=4;
    protected float cooldown=1.0f;
    protected float oncooldown;

    protected Handler handler;
    protected boolean inrange = false;

    public Enemy(int x, int y, ID id, int hp, Handler h, int ar) {
        super(x, y, id);
        health = hp;
        attackrange = ar;
        handler = h;
        if(!respawn())
            handler.wave.tospawn(this);

    }

    public boolean respawn() {
        for(GameObject go :handler.gameObjects) {
            if (go.getId() == ID.Enemy) {
                if (go.getBounds().intersects(this.getBounds())) {
                    return false;
                }
            }
        }
        handler.addObject(this);
        return true;
    }

    @Override
    public void tick(int deltatick) {
        if(!friendly) {
            if (Math.sqrt(Math.pow(handler.player.getX() - getX(), 2) + Math.pow(handler.player.getY() - getY(), 2)) <= attackrange + handler.player.getSizex()/2.0) {
                inrange = true;
                attack();
            } else {
                inrange = false;
                speed_x = (handler.player.getX() - getX());
                speed_y = (handler.player.getY() - getY());
                double mult = movementspeed/Math.sqrt(speed_x*speed_x+speed_y*speed_y);

                speed_x *= mult;
                speed_y *= mult;

                x += (int)speed_x * deltatick;
                y += (int)speed_y * deltatick;
            }
        }

        collision();
        if(x < 0 || y < 0) {//Todo DONT kill just guide them onto the righteous path
            System.out.println(x + ", " + y);
            kill();
        }
        oncooldown-= ((float)deltatick) / 60;
    }
    public void takedamage(int dmg){
        health -= dmg;
        if(health<=0){
            kill();
        }
    }
    public void collision() {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject temp = handler.gameObjects.get(i);
            if (temp == this/*||temp.getId()==ID.Enemy*/)
                continue;
            if (getBounds().intersects(temp.getBounds())) {
                x += speed_x * -1;
                y += speed_y * -1;
                break;
            }

        }
        if((getBounds().intersects(handler.gui.getBounds())||getBounds().intersects(handler.player.getBounds()))){
            x += speed_x * -1;
            y += speed_y * -1;
        }
    }
    public void setFriendly(boolean f){friendly=f;}
     public void kill() {
         handler.player.setFiresextinguished();
         super.kill();
     }
    public void attack(){
        if(oncooldown <= 0.0f) {
            oncooldown = cooldown;
            handler.player.takedamage(attackdamage);
        }
    }
    void drawrange(Graphics g){
        g.setColor(Color.YELLOW);
        if(inrange)
            g.setColor(Color.RED);
        g.drawOval(getX()-attackrange,getY()-attackrange,this.attackrange*2,this.attackrange*2);
    }

}
