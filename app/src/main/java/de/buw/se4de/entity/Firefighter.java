package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import de.buw.se4de.GameObject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.geom.AffineTransform;

import java.util.Vector;

public class Firefighter extends GameObject {
    final private Handler handler;
    private int health;
    private float armor;
    float hurt = 0.0f;
    float hurttime = 0.25f;
    private int firesextinguished;
    private final int movementspeed = 6;
    public Firefighter(int x, int y, ID id, Handler handler, int hearts) {
        super(x, y, id);
        this.handler = handler;
        this.health = Math.max(1, hearts); // give the firefighter a minimum of 1 health
        firesextinguished = 0;
        armor = power.ARMOR.lvl;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/firefighter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteSize = 32;
    }

    public int getHealth() {
        return health;
    }
    public int getAmor() {
        return (int)armor;
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
        hurt -= ((float)deltatick) / 60.0f;
        if(armor<=Math.min(power.ARMOR.lvl,health)){
            armor += ((float)deltatick) / 360.0f;
        }
    }

    @Override
    public void draw(@NotNull Graphics g, int deltatick) {
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform old = g2d.getTransform();
        g2d.translate(getX(), getY());
        g2d.rotate(Math.atan2(speed_x, -speed_y));
        g2d.drawImage(sprite, -getSizex(), -getSizey(), getSizex(), getSizey(), spriteFrame*spriteSize, 0, (spriteFrame+1)*spriteSize, spriteSize, null);
        g2d.setTransform(old);
        if(hurt >= 0.0f ){
            g.setColor(Color.red);
            g.fillOval(x, y-getSizey(), getSizex(), getSizey());
        }
    }

    private void collision() {
        if (getBounds().intersects(handler.gui.getBounds())) {
            x += speed_x * -1;
            y += speed_y * -1;
        }
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject temp = handler.gameObjects.get(i);
            if (getBounds().intersects(temp.getBounds())) {
                x += speed_x * -1;
                y += speed_y * -1;
            }
        }
    }
    @Override
    public Rectangle getBounds() {
       return new Rectangle(x+3, y+3, getSizex()-8, getSizey()-8);
    }
    private void levelup(){
        Vector<power> vpower =  power.refreshvector();
        if(vpower.size() == 0)
            return;
        int powertolevelup = handler.r.nextInt(0,vpower.size());
        power p = vpower.get(powertolevelup);
        handler.gui.drawlevelup(p.value);
        ++p.lvl;
    }
    public int getFiresextinguished() {
        return firesextinguished;
    }
    public void setFiresextinguished(){
        ++firesextinguished;
        if(firesextinguished % 10 == 0)
            levelup();
    }
    public void takedamage(int attackdamage) {
        armor -= attackdamage;
        if(armor < 0) {
            health += armor;
            health = Math.max(0, health); // clamp health to a minimum value of 0
            armor = 0;
        }
        hurt = hurttime * attackdamage;
    }
    public enum power{
        BOUNCE(0,0,5,"Bouncing projectiles"),PROJECTILE_SPEED(2,2,30,"Faster Projectiles"),
        EXPLODING_ENEMY(0,0,1,"Friendly Enemys Explode"),PUDDLE_ON_DEATH(0,0,1,"PUDDLE ON DEATH!!"),
        ARMOR(0,0,4,"Armor"),SWEETS(1,1,4,"Candy"),PROJECTILE_DMG(1,1,100,"More Damage"),
        STUN_DURATION(2,2,7,"Longer Stuns");
        final int minlvl;
        public int lvl;
        final int maxlvl;
        final String value;
        power(int min, int l,int ml,String v){
            minlvl = min ;
            lvl = l;
            maxlvl=ml;
            value = v;
        }
        public static void reset(){
                for(power p:power.values())
                    p.lvl = p.minlvl;
        }
        public static Vector<power> refreshvector(){
            Vector<power> vr = new Vector<>();
            for(power p:power.values())
                if(p.lvl<p.maxlvl)
                    vr.add(p);
            return vr;
        }

    }
}
