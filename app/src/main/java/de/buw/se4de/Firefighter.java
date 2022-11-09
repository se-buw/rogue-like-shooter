package de.buw.se4de;

import javax.print.attribute.standard.JobKOctets;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Firefighter extends Object{
    Handler handler;
    int hearts;

    public Firefighter(int x, int y, ID id, Handler handler, int hearts){
        super(x, y, id);
        this.handler = handler;
        this.hearts = hearts;
    }

    public int getHearts(){
        return hearts;
    }

    public void tick() {
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
            else if ((temp.getId() == ID.Fire)){
                if (getBounds().intersects(temp.getBounds())){
                    boolean found_heart = false;

                    for (int k=0; k < handler.objects.size(); k++){
                        Object temp2 = handler.objects.get(k);

                        if ((temp2.getId() == ID.Hearts) && (!found_heart)){
                            handler.objects.removeLastOccurrence(temp2);
                            found_heart = true;
                            handler.objects.remove(temp);
                            //creates a new fire immediately after the last one is dead
                            int randomX = ThreadLocalRandom.current().nextInt(100, 500);
                            int randomY = ThreadLocalRandom.current().nextInt(100, 500);
                            handler.addObject(new Fire (randomX, randomY, (ID.Fire), handler));
                            hearts--;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle(x, y, 30, 30);
    }

}
