package de.buw.se4de;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends Canvas implements Runnable {

    private Boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game() {
        new Window(600, 600, "Firefighter", this);
        start();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
        handler.addObject(new Firefighter(50, 50, ID.Player, handler));

        int randomX = ThreadLocalRandom.current().nextInt(100, 500);
        int randomY = ThreadLocalRandom.current().nextInt(100, 500);

        handler.addObject(new Fire (randomX, randomY, (ID.Fire)));

        //create a frame
        handler.addObject(new Frame(-2,0,ID.Frame,2,600 ));
        handler.addObject(new Frame(587,0,ID.Frame,2,600 ));
        handler.addObject(new Frame(0,-1,ID.Frame,600,1 ));
        handler.addObject(new Frame(0,564,ID.Frame,600,2 ));

        //heart
        handler.addObject(new Hearts(0,30,ID.Hearts));
        handler.addObject(new Hearts(30,30,ID.Hearts));
        handler.addObject(new Hearts(60,30,ID.Hearts));
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    private void stop() throws InterruptedException {
        isRunning = false;
        thread.join();
    }

    public static void main(String[]args){
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                // updates++;
                delta--;
            }
            draw();
            frames++;

            if(System.currentTimeMillis() > 1000){
                timer += 1000;
                frames = 0;
                // updates = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void tick(){
        handler.tick();
    }
    public void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////////////

        g.setColor(Color.black);
        g.fillRect(0, 0,600,600);

        handler.draw(g);

        ////////////////////////////////////////
        g.dispose();
        bs.show();
    }
}
