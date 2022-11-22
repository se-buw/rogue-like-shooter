package de.buw.se4de.gameflow;

import de.buw.se4de.*;
import de.buw.se4de.Frame;
import de.buw.se4de.input.KeyInput;
import de.buw.se4de.input.MouseInput;
import de.buw.se4de.map.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas {
    int width=1200;
    int height=800;
    private final Handler handler;

    public Game() {

        new de.buw.se4de.Window(width, height, "Firefighter", this);

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler,this));

        initialize();
    }
    public void initialize(){
        handler.clear();
        Map m = new Map();
        m.getMap("0");
        handler.changemap(m);
        handler.wave.newwave();
        //create a frame
        handler.addObject(new Frame(0, 0, ID.Wall, 2, height)); // left
        handler.addObject(new Frame(width - 20 , 0, ID.Wall, 2, height)); // right
        handler.addObject(new Frame(0, 0, ID.Wall, width, 2)); // top
        handler.addObject(new Frame(0, height - 40, ID.Wall, width, 2)); // bottom

        //Create UI
        handler.addGUI(new GUI(0,0,ID.GUI,handler.player));

        handler.game_isrunning = true;
        run();

    }
    public void stop() throws InterruptedException {
        handler.game_isrunning = false;
        //thread.join();
    }

    public static void main(String[]args){
        new Game();
    }

    //game loop
    //updates the frame 60 times per second
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while(handler.game_isrunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                handler.tick((int)delta);
                delta -= (int)delta;
                draw();
            }
        }

        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //image rendering
    public void draw(){
        //BufferStrategy is responsible for preloading 3 frames before updating to make game run smoothly
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //background

        g.setColor(Color.gray);
        g.fillRect(0, 0,width,height);
        handler.draw(g);

        //make Player freeze and call "Game over" when there are no hearts left
        if(handler.player.getHealth() == 0){//TODO irgendwann anders Replay
            handler.draw(g);
            handler.gui.DrawDeathScreen(g);
            for(int i=0;i<3;++i)
                bs.show();
            handler.game_isrunning = false;
        }

        g.dispose();
        bs.show();
    }
}
