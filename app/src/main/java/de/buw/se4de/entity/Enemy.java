package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.Object;

public abstract class Enemy extends Object {
    boolean friendly=false;
    protected int health;
    protected int attackrange;
    protected int attackdamage;
    protected int movementspeed=4;
    protected float cooldown=1.0f;
    protected float oncooldown;

    protected Handler handler;

    public Enemy(int x, int y, ID id, int hp, Handler h, int ar) {
        super(x, y, id);
        health = hp;
        attackrange = ar;
        handler = h;
    }
    public Enemy(int x, int y,int width,int height, ID id, int hp, Handler h, int ar) {
        super(x, y,width,height, id);
        health = hp;
        attackrange = ar;
        handler = h;
    }
    @Override
    public void tick(int deltatick) {
        if(Math.sqrt(Math.pow(handler.player.getX() - (getX()-attackrange/2),2) + Math.pow(handler.player.getY()-getY(),2)) <= attackrange){
            attack();
        }
        oncooldown-= ((float)deltatick) / 60;
    }//TODO fix attackrange
    public void takedamage(int dmg){
        health -= dmg;
        if(health<=0){
            kill();
        }
    }
    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);
            if (temp == this||temp.getId() == ID.PROJECTILE) {
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
    public void setFriendly(boolean f){friendly=f;}
     public void kill() {
         handler.player.setFiresextinguished();
         super.kill();
     };//TODO killed
    public void attack(){
        if(oncooldown <= 0.0f) {
            System.out.println("DIEEE!");
            oncooldown = cooldown;
            handler.player.takedamage(attackdamage);
        }
    }

}
