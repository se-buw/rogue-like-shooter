package de.buw.se4de;

import java.awt.*;

public class Hearts extends Object{

    public Hearts(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        //drawHeart(g,x, y, 20, 20);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("\u2665", x, y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y - 30, 30, 30);
    }
}
