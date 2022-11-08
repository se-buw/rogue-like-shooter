package de.buw.se4de;

import java.awt.*;

public class Bullet extends Object {
    Location location; // TODO: Use Location class

    public Bullet(int x, int y, ID id, int dir_x, int dir_y) {
        super(x, y, id);
        calculateSpeed(x, y, dir_x, dir_y);
    }

    public void calculateSpeed(int fromX, int fromY, int toX, int toY)
    {
        double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        double speed = 10;
        this.speed_y = (float)((toY - fromY) * speed / distance);
        this.speed_x = (float)((toX - fromX) * speed / distance);
    }

    public void tick() {
        x += speed_x;
        y += speed_y;

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 10, 10);
    }
}
