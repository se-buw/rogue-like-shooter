package de.buw.se4de.map;

import de.buw.se4de.ID;

import java.awt.*;

public class Spawnarea extends Wall {
    public Spawnarea(int x, int y, ID id, int spriteID) {
        super(x, y, id, spriteID);
    }

    @Override
    public void draw(Graphics g, int deltatick) {
        super.draw(g, deltatick);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,0,0);
    }
}
