package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.Object;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Water extends Projectile {
    int bounce;
    int bounce_limit;
    int damage;
    int speed_multiplier;

    public Water(int x, int y, ID id, int dir_x, int dir_y, Handler handler) {
        super(x, y, id,dir_x,dir_y,handler,8,8);
        bounce_limit = Firefighter.power.BOUNCE.lvl;
        speed_multiplier = Firefighter.power.PROJECTILE_SPEED.lvl;
        damage=Firefighter.power.PROJECTILE_DMG.lvl;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, getSizex(), getSizey());
    }
    @Override
    protected void collision(){
        //check if Water intersects the Fire, in this case Fire object disappears and appears in another place
        for (Object temp:handler.objects){
            if (getBounds().intersects(temp.getBounds())){
                if(temp.getId() == ID.Frame) {
                    speed_x = speed_x * -1;
                    speed_y = speed_y * -1;
                    if(++bounce > bounce_limit)//TODO reflection(maybe)
                        kill();
                }
                else if (temp.getId() == ID.Enemy){
                    if(Firefighter.power.PIERCING_PROJECTILE.lvl == 0)
                            kill();
                    ((Enemy)temp).takedamage(damage);
                    //TODO Waves in game?
                }
            }
        }
    }
}
