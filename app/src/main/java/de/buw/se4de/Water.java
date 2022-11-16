package de.buw.se4de;

import org.checkerframework.checker.units.qual.Speed;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Water extends Object {

    Handler handler;
    int bounce;
    int bounce_limit;

    int speed_multiplier;

    public Water(int x, int y, ID id, int dir_x, int dir_y, Handler handler) {
        super(x, y, id);
        calculateSpeed(x, y, dir_x, dir_y);
        this.handler = handler;
        bounce_limit = Firefighter.power.BOUNCE.lvl;
        speed_multiplier = Firefighter.power.PROJECTILE_SPEED.lvl;
    }

    public void calculateSpeed(int fromX, int fromY, int toX, int toY)
    {
        double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        double speed = 7;
        this.speed_y = (float)((toY - fromY) * speed / distance);//TODO NORMALIZE!!!
        this.speed_x = (float)((toX - fromX) * speed / distance);
    }

    public void tick(int deltatick) {
        x += speed_x * deltatick * (1+speed_multiplier*0.1);
        y += speed_y * deltatick * (1+speed_multiplier*0.1);

        collision();
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
    }
    protected void collision(){
        //check if Water intersects the Fire, in this case Fire object disappears and appears in another place

        for (int i=0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);
            if (getBounds().intersects(temp.getBounds())){
                if(temp.getId() == ID.Frame) {
                    speed_x = speed_x * -1;
                    speed_y = speed_y * -1;
                    if(++bounce > bounce_limit)//TODO reflection(maybe)
                        handler.removeObject(this);
                }
                if (temp.getId() == ID.Fire){//TODO ID:ENEMY
                    if(Firefighter.power.PIERCING_PROJECTILE.lvl == 0)
                        handler.removeObject(this);
                    handler.removeObject(temp);
                    handler.player.setFiresextinguished();
                    //creates a new fire immediately after the last one is dead
                    int randomX = ThreadLocalRandom.current().nextInt(100, 500);
                    int randomY = ThreadLocalRandom.current().nextInt(100, 500);
                    handler.addObject(new Fire (randomX, randomY, (ID.Fire), handler));//TODO Waves in game? (and delete this)
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
