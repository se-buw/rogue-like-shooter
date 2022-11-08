package de.buw.se4de;

import java.awt.*;

public class Frame extends Object{

    int width, length;

    public Frame(int x, int y, ID id, int width, int length) {
        super(x, y, id);
        this.length = length;
        this.width = width;
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, length);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, length);
    }
}
