package de.buw.se4de;

import de.buw.se4de.entity.Firefighter;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

public class GUI extends GameObject {
    float levelup=0;
    String lvlpower;
    int width=1200;
    int height=30;
    Firefighter player;
    Handler handler;
    final Rectangle restartbutton;
    public GUI(int x, int y, ID id,Firefighter p,Handler h) {
        super(x, y, id);
        player = p;
        restartbutton = new Rectangle(490,450,230,70);
        handler = h;
    }

    @Override
    public void tick(int deltatick) {
        if(levelup >= 0)
            levelup -= (float)deltatick/60.0f;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("extinguished: " + player.getFiresextinguished(), 280, 20);
        g.drawString("time: " + handler.gettime()/1000 +"s",400,20);
        g.drawString("wave: "+handler.wave.getWave(), 700,20);
        if(levelup > 0) {
            g.setColor(Color.YELLOW);
            g.drawString("'" + lvlpower + "'",500,20);
        }

        //g.drawString("extinguished: " + player, 280, 20);

        for(int hp = 0;hp <  player.getHealth();++hp){
            drawhearts(g,hp,Color.red);
        }
        for(int hp = 0;hp <  player.getAmor();++hp){
            drawhearts(g,hp,Color.MAGENTA);
        }


    }

    private void drawhearts(Graphics g,int hp,Color c){
        g.setColor(c);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("\u2665",hp*30 , 30);
    }
    public void DrawDeathScreen(Graphics g){
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.drawString("Game Over", 380, 400);
        g.setFont(new Font("TimesRoman", Font.BOLD, 60));
        g.drawString("Restart!",500,500);
        g.drawRect(restartbutton.x, restartbutton.y, restartbutton.width, restartbutton.height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, width,height);
    }

    public Rectangle getRestartbutton() {
        return restartbutton;
    }

    public void drawlevelup(String p){
        levelup = 4;
        lvlpower = p;
    }
}
