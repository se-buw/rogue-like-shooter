package de.buw.se4de.entity;

import de.buw.se4de.GameObject;
import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class Puddle extends Projectile{
    float active;

    public Puddle(int x, int y, Handler handler) {
        super(x, y, ID.PROJECTILE, x, y, handler);
        active = 0.0f;
        damage = 1;
    }

    @Override
    public void tick(int deltatick){
        if(active > 0)
            active -= ((float)deltatick)/60.0f;
        else
            collision();
    }
    @Override
    public void draw(Graphics g, int deltatick) {
        if(active <= 0) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 30, 30);
        }
    }
    @Override
    protected void collision() {
        if(active <= 0){
            for (GameObject o: handler.gameObjects){
                if(o.getId() == ID.Enemy) {
                    if (getBounds().intersects(o.getBounds())) {
                        ((Enemy)o).takedamage(damage);
                        kill();
                    }
                }
            }
        }
    }
}
