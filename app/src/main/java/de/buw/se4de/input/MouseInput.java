package de.buw.se4de.input;

import de.buw.se4de.ID;
import de.buw.se4de.entity.Sweets;
import de.buw.se4de.entity.Water;
import de.buw.se4de.gameflow.Game;
import de.buw.se4de.gameflow.Handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    public Handler handler;
    Game game;

    public MouseInput(Handler objects,Game g)
    {
        this.handler = objects;
        game = g;
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
                new Water(handler.player.getX(), handler.player.getY(), ID.Water, x, y, handler);
            else if (!handler.game_isrunning) {
                if(handler.gui.getRestartbutton().intersects(x,y,1,1))
                    game.initialize();
            }
        }else {
            if (handler.game_isrunning){
                new Sweets(handler.player.getX(), handler.player.getY(), ID.Water, x, y, handler);
            }
        }
    }
}
