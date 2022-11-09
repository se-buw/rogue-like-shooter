package de.buw.se4de;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fire extends Object{
    Handler handler;
    Random r = new Random();
    float upperbound = 15.0f;

    public Fire(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        handler.amountOfFires++;
    }

    @Override
    public void tick() {
        if(handler.amountOfFires < 5){
            int randomX = ThreadLocalRandom.current().nextInt(100, 500);
            int randomY = ThreadLocalRandom.current().nextInt(100, 500);
            handler.addObject(new Fire (randomX, randomY, (ID.Fire), handler));
        }
        speed_x = r.nextFloat(upperbound) - r.nextFloat(upperbound);
        speed_y = r.nextFloat(upperbound) - r.nextFloat(upperbound);

        x += (int)speed_x;
        y += (int)speed_y;

        collision();

        }

    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);

            if ((temp.getId() == ID.Frame) || (temp.getId() == ID.Hearts)) {
                if (getBounds().intersects(temp.getBounds())) {
                    x += speed_x * -1;
                    y += speed_y * -1;
                }
            }
        }
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
