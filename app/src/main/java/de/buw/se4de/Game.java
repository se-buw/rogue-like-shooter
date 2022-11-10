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
        handler.addObject(new Firefighter(50, 50, ID.Player, handler,4));

        // create the mother of all fires
        int randomX = ThreadLocalRandom.current().nextInt(100, 500);
        int randomY = ThreadLocalRandom.current().nextInt(100, 500);
        handler.addObject(new Fire(randomX, randomY, (ID.Fire), handler));

        //create a frame
        handler.addObject(new Frame(-2, 0, ID.Frame, 2, 600));
        handler.addObject(new Frame(587, 0, ID.Frame, 2, 600));
        handler.addObject(new Frame(0, -1, ID.Frame, 600, 1));
        handler.addObject(new Frame(0, 564, ID.Frame, 600, 2));

        //create hearts
        for (int i = 0; i < handler.objects.size(); i++) {
            Object temp = handler.objects.get(i);

            if (temp.getId() == ID.Player) {
                int hearts_x = 0;
                for (int k = 0; k < temp.getHearts(); k++) {
                    handler.addObject(new Hearts(hearts_x, 30, ID.Hearts));
                    hearts_x += 30;
                }
            }
        }
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() throws InterruptedException {
        isRunning = false;
        thread.join();
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

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                handler.tick();
                delta--;
            }
            draw();
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
        g.setColor(Color.black);
        g.fillRect(0, 0,600,600);
        handler.draw(g);

        //make Player freeze and call "Game over" when there are no hearts left
        for (int i=0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if (temp.getId() == ID.Player){
                if (temp.getHearts() == 0){
                    g.setColor(Color.yellow);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                    g.drawString("Game Over", 150, 280);
                    temp.setSpeed_x(0.0f);
                    temp.setSpeed_y(0.0f);

                }
            }
        }

        g.dispose();
        bs.show();
    }
}
