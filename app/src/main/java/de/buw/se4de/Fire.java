package de.buw.se4de;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Fire extends Object{

    Handler handler;

    public Fire(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 30, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}
