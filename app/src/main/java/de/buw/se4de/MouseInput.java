package de.buw.se4de;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private ObjectManagement objects;

    public MouseInput(ObjectManagement objects)
    {
        this.objects = objects;
    }

    public void mousePressed(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();

        for(int i = 0; i < objects.objects.size(); i++){
            Object temp = objects.objects.get(i);

            if (temp.getId() == ID.Player){
                objects.addObject(new Bullet(temp.getX()+10, temp.getY() + 10, ID.Bullet, x, y));
            }
        }


    }
}
