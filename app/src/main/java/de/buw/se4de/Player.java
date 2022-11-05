package de.buw.se4de;

import java.awt.*;

public class Player extends Object{

    ObjectManagement objects;

    public Player (int x, int y, ID id, ObjectManagement objects){
        super(x, y, id);
        this.objects = objects;
    }

    public void tick()
    {
        x += speed_x;
        y += speed_y;

        if(objects.isUp()){
            speed_y = -5;
        } else if (!objects.isDown()){
            speed_y = 0;
        }

        if(objects.isDown()){
            speed_y = 5;
        } else if (!objects.isUp()){
            speed_y = 0;
        }

        if(objects.isRight()){
            speed_x = 5;
        } else if (!objects.isLeft()){
            speed_x = 0;
        }

        if(objects.isLeft()){
            speed_x = -5;
        } else if (!objects.isRight()){
            speed_x = 0;
        }
    }

    public void draw(Graphics g){
        //example to test
        g.setColor(Color.blue);
        g.fillRect(x, y, 30, 30);
    }

}
