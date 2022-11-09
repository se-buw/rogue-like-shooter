package de.buw.se4de;

import javax.print.attribute.standard.JobKOctets;
import java.awt.*;

public class Firefighter extends Object{
    Handler handler;

    public Firefighter(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public void tick()
    {
        x += speed_x;
        y += speed_y;

        collision();

        if(handler.isUp()){
            speed_y = -5;
        } else if (!handler.isDown()){
            speed_y = 0;
        }

        if(handler.isDown()){
            speed_y = 5;
        } else if (!handler.isUp()){
            speed_y = 0;
        }

        if(handler.isRight()){
            speed_x = 5;
        } else if (!handler.isLeft()){
            speed_x = 0;
        }

        if(handler.isLeft()){
            speed_x = -5;
        } else if (!handler.isRight()){
            speed_x = 0;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, 30, 30);
    }

    private void collision(){
        for (int i=0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if ((temp.getId() == ID.Frame) || (temp.getId() == ID.Hearts)){
                if (getBounds().intersects(temp.getBounds())){
                    x += speed_x * -1;
                    y += speed_y * -1;
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle(x, y, 30, 30);
    }

}
