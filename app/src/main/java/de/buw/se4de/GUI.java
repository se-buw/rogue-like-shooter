package de.buw.se4de;

import de.buw.se4de.entity.Firefighter;

import java.awt.*;

public class GUI extends GameObject {

    int width=1200;
    int height=30;
    Firefighter player;
    final Rectangle restartbutton;
    public GUI(int x, int y, ID id,Firefighter p) {
        super(x, y, id);
        player = p;
        int hearts_x = 0;
        int hp = player.getHealth();//changed:added player to handler so one doesnt have to iterate over complete list everytime
        restartbutton = new Rectangle(490,450,230,70);
    }

    @Override
    public void tick(int deltatick) {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("extinguished: " + player.getFiresextinguished(), 280, 20);
        //g.drawString("extinguished: " + player, 280, 20);

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
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.drawString("Game Over", 380, 400);
        g.setFont(new Font("TimesRoman", Font.TYPE1_FONT, 60));
        g.drawString("Restart!",500,500);
        g.drawRect(restartbutton.x, restartbutton.y, restartbutton.width, restartbutton.height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, width,height);
    }
    //TODO time

    public Rectangle getRestartbutton() {
        return restartbutton;
    }
}
