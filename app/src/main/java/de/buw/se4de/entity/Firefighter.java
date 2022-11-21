package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.Object;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Firefighter extends Object {
    private Handler handler;
    private int health;
    private int firesextinguished;
    private int movementspeed = 6;
    private int level;//TODO exp
    public Firefighter(int x, int y, ID id, Handler handler, int hearts) {
        super(x, y, id);
        this.handler = handler;
        this.health = hearts;
        firesextinguished = 0;
        level = 0;
    }

    public int getHealth() {
        return health;
    }

    public void tick(int deltatick) {
        double mult = movementspeed/Math.sqrt(speed_x*speed_x+speed_y*speed_y);
        speed_x *= mult;
        speed_y *= mult;

        x += (int) speed_x * deltatick;
        y += (int) speed_y * deltatick;

        collision();

        if (handler.isUp()) {
            speed_y = -1;
        } else if (!handler.isDown()) {
            speed_y = 0;
        }

        if (handler.isDown()) {
            speed_y = 1;
        } else if (!handler.isUp()) {
            speed_y = 0;
        }

        if (handler.isRight()) {
            speed_x = 1;
        } else if (!handler.isLeft()) {
            speed_x = 0;
        }

        if (handler.isLeft()) {
            speed_x = -1;
        } else if (!handler.isRight()) {
            speed_x = 0;
        }
    }

    public void draw(@NotNull Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, getSizex(), getSizey());
    }

    private void collision() {//TODO wände vlt ändern
        if (getBounds().intersects(handler.gui.getBounds())) {
            x += speed_x * -1;
            y += speed_y * -1;
        }
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);
            if (getBounds().intersects(temp.getBounds())) {
                x += speed_x * -1;
                y += speed_y * -1;
            }
        }
    }
//TODO hearts shifted to GUI
//TODO adding missing flame
//TODO Put borders in one vector(with gui) so that i dont have so many special cases to remember


    @Override
    public Rectangle getBounds() {
       return new Rectangle(x, y, 30, 30);
    }
    private void levelup(){
        System.out.println("LEVELED UP");//TODO powers regulated(1 of 3 random powers to choose from)
        ++level;
        ++power.BOUNCE.lvl;
        ++power.PROJECTILE_SPEED.lvl;
        ++power.PIERCING_PROJECTILE.lvl;
    }
    public int getFiresextinguished() {
        return firesextinguished;
    }
    public void setFiresextinguished(){
        ++firesextinguished;
        if(firesextinguished % 5 == 0)
            levelup();
    }

    public void takedamage(int attackdamage) {
        health-=attackdamage;
        if(health <= 0)
            System.out.println("DEAD");//TODO death method
    }

    public enum power{
        BOUNCE(0,5),PROJECTILE_SPEED(1,30),MORE_PROJECTILES(1,2),PIERCING_PROJECTILE(0,1),
        EXPLODING_PROJECTILE(0,3),PUDDLE_ON_DEATH(0,1),SHIELD(0,5),ARMOR(0,4),SWEETS(0,4),PROJECTILE_DMG(1,100);
        int lvl;
        int maxlvl;
        power(int l,int ml){
            lvl = l;
            maxlvl=ml;
        }

    }
}