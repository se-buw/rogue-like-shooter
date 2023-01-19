package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class TankFire extends Enemy{
    float casttime = 1.5f;
    float maxcasttime = 1.5f;
    public TankFire(int x,int y, Handler handler) {
        super(x, y,10, 50, 50,70,handler);
        movementspeed = 2;
        attackdamage = 3;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/tank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteFrames = 4;
        spriteSize = 8;
        spriteUpdateTime = 10;
    }

    @Override
    public void draw(Graphics g, int deltatick) {
        if(!friendly)
            g.setColor(new Color(255,100,0));
        else
            g.setColor(Color.GREEN);
        super.draw(g, deltatick);
        drawrange(g);
    }

    public void drawrange(Graphics g){
        try {
            g.setColor(new Color(255,255,0));
            if (inrange)
                g.setColor(new Color(255,(int)(255 * Math.max(casttime/maxcasttime,0)),0));
            g.drawOval(getX() - attackrange, getY() - attackrange, this.attackrange * 2, this.attackrange * 2);
        }catch (Exception e){
            System.out.println(casttime/maxcasttime);
        }
    }
    @Override
    public void tick(int deltatick){
        super.tick(deltatick);
        if(inrange)
            casttime -= ((float)deltatick) / 60.0f;
        else
            casttime = maxcasttime;
        if(casttime <= 0)
            attack();
    }
    @Override
    public void attack(){
        if(casttime <= 0.0f){
            casttime = maxcasttime;
            handler.player.takedamage(attackdamage);
        }
    }
}
