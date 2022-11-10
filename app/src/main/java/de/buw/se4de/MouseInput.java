package de.buw.se4de;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;

    public MouseInput(Handler objects)
    {
        this.handler = objects;
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        for(int i = 0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if (temp.getId() == ID.Player){
                handler.addObject(new Water(temp.getX()+10, temp.getY() + 10, ID.Water, x, y, handler));
            }
        }


    }
}
