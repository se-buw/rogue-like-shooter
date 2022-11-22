package de.buw.se4de.map;

import de.buw.se4de.ID;
import de.buw.se4de.GameObject;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(int x, int y, ID id) {
        super(x, y, 30, 30, id);
    }

    @Override
    public void tick(int deltatick) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(50,100,50));
        g.fillRect(x,y,getSizex(),getSizey());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getSizex(),getSizey());
    }
}
