package de.buw.se4de;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fire extends Enemy{

    Random r = new Random();
    float upperbound = 1.0f;
    boolean friendly=false;

    public Fire(int x, int y, ID id, Handler handler) {
        super(x, y, id,1,handler,50);
        handler.amountOfFires++;
        movementspeed=2;
    }

    @Override
    public void tick(int deltatick) {
        while(handler.amountOfFires < 5){//TODO delete + waves //TODO dont spawn in my face + spawn methode + delete this
            int randomX = ThreadLocalRandom.current().nextInt(100, 500);
            int randomY = ThreadLocalRandom.current().nextInt(100, 400);
            handler.addObject(new Fire (randomX, randomY, (ID.Fire), handler));
        }
        //speed_x = r.nextFloat(upperbound) - r.nextFloat(upperbound);
        //speed_y = r.nextFloat(upperbound) - r.nextFloat(upperbound);

        if(!friendly) {
            speed_x = (handler.player.x - x) + (r.nextFloat(upperbound) - r.nextFloat(upperbound));//towards player + random movement
            speed_y = (handler.player.y - y) + (r.nextFloat(upperbound) - r.nextFloat(upperbound));//towards player + random movement
            if(Math.sqrt(handler.player.x*handler.player.x + handler.player.y*handler.player.y) <= attackrange){
//TODO FIND BUG
                attack();
            }
            oncooldown-= deltatick / 60;
        }else {
            //TODO fiend other flame/or other ideas
        }
        double mult = movementspeed/Math.sqrt(speed_x*speed_x+speed_y*speed_y);
        speed_x *= mult;
        speed_y *= mult;
        x += (int)speed_x * deltatick;
        y += (int)speed_y * deltatick;

        collision();
    }

    /*@Override
    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);
            if(temp == this){
                continue;//things intersect themself, also add death method to object
            }

            if ((temp.getId() == ID.Frame)) {
                if (getBounds().intersects(temp.getBounds())) {
                    x += speed_x * -1;
                    y += speed_y * -1;
                }
            }
            if(friendly){
                if ((temp.getId() == ID.Fire)) {
                    if (getBounds().intersects(temp.getBounds())) {
                        killed();
                        ((Fire)temp).killed();
                    }
                }
            }
        }
        if (getBounds().intersects(handler.gui.getBounds())) {
            x += speed_x * -1;
            y += speed_y * -1;
        }

    }*/
    @Override
    public void setFriendly(boolean f){
        friendly = true;
    }
    public boolean getFriendly(){return friendly;}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,this.attackrange,this.attackrange);
        if(!friendly)
            g.setColor(Color.red);
        else
            g.setColor(Color.GREEN);

        g.fillRect(x, y, 30, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    @Override
    public void killed(){
        handler.player.setFiresextinguished();
        handler.removeObject(this);
    }
}
