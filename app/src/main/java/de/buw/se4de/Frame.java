package de.buw.se4de;

import java.awt.*;

public class Frame extends GameObject {

    int width, length;

    public Frame(int x, int y, ID id, int width, int length) {
        super(x, y, id);
        this.length = length;
        this.width = width;
    }

    public void tick(int deltatick) {
    }

    @Override
    public void draw(Graphics g, int deltatick) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, length);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, length);
    }
}
