package de.buw.se4de.map;

import de.buw.se4de.ID;
import de.buw.se4de.GameObject;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Wall extends GameObject {

    private int spriteOffset;

    public Wall(int x, int y, ID id, int spriteID) {
        super(x, y, 30, 30, id);
        spriteSize = 8;
        spriteOffset = spriteID * 8;
        try {
            sprite = ImageIO.read(new File("src/main/resources/sprites/maptiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick(int deltatick) {

    }

    @Override
    public void draw(Graphics g, int deltatick) {
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform old = g2d.getTransform();
        g2d.translate(getX(), getY());
        g2d.drawImage(sprite, -getSizex()/2, -getSizey()/2, getSizex()/2, getSizey()/2, spriteOffset, 0, spriteOffset+spriteSize, spriteSize, null);
        g2d.setTransform(old);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,getSizex(),getSizey());
    }
}
