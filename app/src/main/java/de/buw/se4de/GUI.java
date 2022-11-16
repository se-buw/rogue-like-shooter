package de.buw.se4de;

import java.awt.*;
import java.util.Vector;

public class GUI extends Object{//TODO add frame

    int width=600;
    int height=35;
    Firefighter player;
    final Rectangle restartbutton;
    public GUI(int x, int y, ID id,Firefighter p) {
        super(x, y, id);
        player = p;
        int hearts_x = 0;
        int hp = player.getHealth();//changed:added player to handler so one doesnt have to iterate over complete list everytime
        restartbutton = new Rectangle(160,300,200,100);
    }

    @Override
    public void tick(int deltatick) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("extinguished: " + player.getFiresextinguished(), 280, 20);

        for(int hp = 0;hp <  player.getHealth();++hp){
            drawhearts(g,hp);
        }
    }

    private void drawhearts(Graphics g,int hp){
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("\u2665",hp*30 , 30);
    }
    public void DrawDeathScreen(Graphics g){
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        g.drawString("Game Over", 150, 280);
        g.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 40));
        g.drawString("Restart",220,310);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, width,height);
    }
    public  Rectangle getRestartButton(){
        return restartbutton;
    }//TODO time
}
