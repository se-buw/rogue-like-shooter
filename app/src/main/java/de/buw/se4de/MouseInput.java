package de.buw.se4de;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;

    public MouseInput(Handler objects)
    {
        this.handler = objects;
    }

    @Override
    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        /*
        for(int i = 0; i < handler.objects.size(); i++){//give player vector to handler
            Object temp = handler.objects.get(i);

            if (temp.getId() == ID.Player){
                handler.addObject(new Water(temp.getX()+10, temp.getY() + 10, ID.Water, x, y, handler));
            }
        }*/
        if(e.getButton() == MouseEvent.BUTTON1) {
            if (handler.game_isrunning)
                handler.addObject(new Water(handler.player.getX() + 10, handler.player.getY() + 10, ID.Water, x, y, handler));//TODO coming with level up
                //TODO new initialize in Game class that can be called repeatedly
            else if (!handler.game_isrunning) {
                //if(x<handler.gui.getrestartbutton){

                //}
            }
        }else {
            if (handler.game_isrunning){
                handler.addObject(new Sweets(handler.player.getX() + 10, handler.player.getY() + 10, ID.Water, x, y, handler));//TODO coming with level up
            }
        }
    }
}
