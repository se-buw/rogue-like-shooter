package de.buw.se4de;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler objects;

    public MouseInput(Handler objects)
    {
        this.objects = objects;
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        for(int i = 0; i < objects.objects.size(); i++){
            Object temp = objects.objects.get(i);

            if (temp.getId() == ID.Player){
                objects.addObject(new Water(temp.getX()+10, temp.getY() + 10, ID.Water, x, y, objects));
            }
        }


    }
}
