package de.buw.se4de;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Enemy extends Object{

    protected int health;
    protected int attackrange;
    protected int attackdamage;
    protected int movementspeed;
    protected float cooldown=1.0f;
    protected float oncooldown;

    protected Handler handler;

    public Enemy(int x, int y, ID id,int hp,Handler h,int ar) {
        super(x, y, id);
        health = hp;
        attackrange = ar;
        handler = h;
    }

    @Override
    public void tick(int deltatick) {
        if(Math.sqrt(handler.player.x*handler.player.x + handler.player.y*handler.player.y) <= attackrange){
            attack();
        }
        oncooldown-= deltatick / 60;
    }

    public void takedamage(int dmg){
        health -= dmg;
        if(health<=0){
            killed();
        }
    }

    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);
            if (temp == this) {
                continue;
            }

            if (getBounds().intersects(temp.getBounds())) {
                x += speed_x * -1;
                y += speed_y * -1;
            }
        }
        if(getBounds().intersects(handler.player.getBounds())){
            x += speed_x * -1;
            y += speed_y * -1;
        }
    }
    abstract public void setFriendly(boolean f);
    abstract public void killed();//TODO killed
    public void attack(){
        if(oncooldown <= 0.0f) {
            oncooldown = cooldown;
            handler.player.takedamage(attackdamage);
        }
    }
}
