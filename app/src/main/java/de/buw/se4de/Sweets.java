package de.buw.se4de;

import java.awt.*;

public class Sweets extends Water{
    public Sweets(int x, int y, ID id, int dir_x, int dir_y, Handler handler){
        super(x,y,id,dir_x,dir_y,handler);
    }
    @Override
    protected void collision() {
        for (int i=0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);
            if (getBounds().intersects(temp.getBounds())) {
                if (temp.getId() == ID.Frame) {
                    handler.removeObject(this);
                }
                if (temp.getId() == ID.Fire) {
                    ((Fire)temp).setFriendly(true);
                    handler.removeObject(this);
                }
            }
        }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 10, 10);
    }
}
