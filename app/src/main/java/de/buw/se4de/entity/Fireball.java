package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.GameObject;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class Fireball extends Projectile{
    public Fireball(int x, int y, ID id, int dir_x, int dir_y, Handler handler,int dmg) {
        super(x, y, id, dir_x, dir_y, handler);
        damage = dmg;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillOval(x, y, getSizex(), getSizey());
    }
    @Override
    public void collision(){
        for(GameObject temp: handler.gameObjects) {
            if (this.getBounds().intersects(temp.getBounds())) {
                if (temp.getId() == ID.GUI || temp.getId() == ID.Wall)
                    kill();
            }
        }
        if(this.getBounds().intersects(handler.player.getBounds())) {
            handler.player.takedamage(damage);
            kill();
        }
    }
}
