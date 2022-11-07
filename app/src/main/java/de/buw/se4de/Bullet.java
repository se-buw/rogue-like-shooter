package de.buw.se4de;

import java.awt.*;

public class Bullet extends Object {
    Location location;

    public Bullet(int x, int y, ID id,int dir_x, int dir_y) {
        super(x, y, id);
        speed_x = (dir_x - x) / 10.0f;
        speed_y = (dir_y - y) / 10.0f;
    }

    public void tick() {
        x += speed_x;
        y += speed_y;

    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, 5, 5);
    }
}
