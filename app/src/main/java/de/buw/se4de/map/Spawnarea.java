package de.buw.se4de.map;

import de.buw.se4de.ID;

import java.awt.*;

public class Spawnarea extends Wall {
    public Spawnarea(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(30,30,30));
        g.fillRect(x, y, getSizex(), getSizey());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,0,0);
    }
}
