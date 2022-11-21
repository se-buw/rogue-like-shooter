package de.buw.se4de.map;

import de.buw.se4de.ID;
import de.buw.se4de.Object;

import java.awt.*;

public class Wall extends Object {

    public Wall(int x, int y, ID id) {
        super(x, y, 40, 36, id);
    }

    @Override
    public void tick(int deltatick) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
