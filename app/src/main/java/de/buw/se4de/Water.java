package de.buw.se4de;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Water extends Object {

    Handler handler;

    public Water(int x, int y, ID id, int dir_x, int dir_y, Handler handler) {
        super(x, y, id);
        calculateSpeed(x, y, dir_x, dir_y);
        this.handler = handler;
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

        collision();
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
    }

    private void collision(){
        //check if Water intersects the Fire, in this case Fire object disappears and appears in another place

        for (int i=0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if (temp.getId() == ID.Fire){
                if (getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                    //creates a new fire immediately after the last one is dead
                    int randomX = ThreadLocalRandom.current().nextInt(100, 500);
                    int randomY = ThreadLocalRandom.current().nextInt(100, 500);
                    handler.addObject(new Fire (randomX, randomY, (ID.Fire), handler));
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
