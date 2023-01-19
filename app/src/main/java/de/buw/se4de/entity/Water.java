package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.GameObject;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class Water extends Projectile {
    int bounce;
    int bounce_limit;
    float invincible = 0.0f;

    public Water(int x, int y, int dir_x, int dir_y, Handler handler) {
        super(x-4, y-4, ID.Water,dir_x,dir_y,handler,8,8);
        bounce_limit = Firefighter.power.BOUNCE.lvl;
        speed *= Firefighter.power.PROJECTILE_SPEED.lvl;
        damage = Firefighter.power.PROJECTILE_DMG.lvl;
        speed_multiplier = Firefighter.power.PROJECTILE_SPEED.lvl;
        spriteSize = 64;
        spriteFrames = 60;
        spriteUpdateTime = 5;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void calculateSpeed(int fromX, int fromY, int toX, int toY) {
        double mult = speed/Math.sqrt((toY - fromY)*(toY - fromY) + (toX - fromX)*(toX - fromX));
        this.speed_y = (float)((toY - fromY)*mult);
        this.speed_x = (float)((toX - fromX)*mult);
    }
    @Override
    public void draw(Graphics g, int deltatick) {
        super.draw(g, deltatick);
    }
    @Override
    protected void collision(){
        //check if Water intersects the Fire, in this case Fire object disappears and appears in another place
        for (GameObject temp:handler.gameObjects){
            if (getBounds().intersects(temp.getBounds())){
                if(temp.getId() == ID.Wall) {
                    if(invincible > 0)
                        continue;
                    else if(++bounce > bounce_limit){//TODO reflection(maybe)? some angles not working fine maybe look at not hit hitbox aswll
                        kill();
                        continue;
                    }
                    speed_x*=-1;
                    speed_y*=-1;

                    invincible = 0.01f;
                }
                else if (temp.getId() == ID.Enemy){
                    kill();
                    ((Enemy)temp).takedamage(damage);
                }
            }
        }
    }

    @Override
    public void tick(int deltatick){
        if (invincible > 0)
            invincible -= ((float)deltatick)/60.0f;
        super.tick(deltatick);
    }
}
