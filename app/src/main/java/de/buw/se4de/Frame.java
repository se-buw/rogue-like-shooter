package de.buw.se4de;

import java.awt.*;

public class Frame extends GameObject {//TODO into GUI

    int width, length;

    public Frame(int x, int y, ID id, int width, int length) {
        super(x, y, id);
        this.length = length;
        this.width = width;
    }

    public void tick(int deltatick) {
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, length);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, length);
    }//TODO use this as walls(smaller)
}
