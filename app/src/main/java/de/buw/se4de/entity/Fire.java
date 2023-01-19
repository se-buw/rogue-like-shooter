package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

public class Fire extends Enemy {
    float casttime = 0.8f;
    float maxcasttime = 0.8f;
    public Fire(int x, int y, Handler handler) {
        super(x, y, ID.Enemy,3,handler,30);
        movementspeed = 4;
        attackdamage = 1;
        health=2;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/fire.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteFrames = 4;
        spriteSize = 8;
        spriteUpdateTime = 8;
    }
    @Override
    public void draw(Graphics g, int deltatick) {
        if(!friendly)
            g.setColor(Color.red);
        else
            g.setColor(Color.GREEN);

        // g.fillRect(x, y, 30, 30);
        super.draw(g, deltatick);
        drawrange(g);
    }
    @Override
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
