package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.GameObject;
import de.buw.se4de.gameflow.Handler;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class Fireball extends Projectile{
    public Fireball(int x, int y, int dir_x, int dir_y, Handler handler,int dmg) {
        super(x-15, y-15, ID.PROJECTILE, dir_x, dir_y, handler);
        damage = dmg;
        spriteSize = 64;
        spriteFrames = 60;
        spriteUpdateTime = 1;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/fireball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g, int deltatick){
        super.draw(g, deltatick);
        // g.fillOval(x, y, getSizex(), getSizey());
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
