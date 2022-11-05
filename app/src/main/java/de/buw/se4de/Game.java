package de.buw.se4de;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private Boolean isRunning = false;
    private Thread thread;
    private ObjectManagement objects;

    public Game(){
        new Window(600,600,"Firefighter", this);
        start();

        objects = new ObjectManagement();
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
        objects.tick();
    }
    public void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////////////

        g.setColor(Color.red);
        g.fillRect(50, 50,500,500);

        objects.draw(g);

        ////////////////////////////////////////
        g.dispose();
        bs.show();
    }


}
