package de.buw.se4de.input;
import de.buw.se4de.gameflow.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    public Handler handler;

    public KeyInput(Handler objects){
        this.handler = objects;
    }

    public void keyPressed(KeyEvent e){
        System.out.println(e);
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_W -> handler.setUp(true);
                case KeyEvent.VK_A -> handler.setLeft(true);
                case KeyEvent.VK_S -> handler.setDown(true);
                case KeyEvent.VK_D -> handler.setRight(true);
            }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> handler.setUp(false);
            case KeyEvent.VK_A -> handler.setLeft(false);
            case KeyEvent.VK_S -> handler.setDown(false);
            case KeyEvent.VK_D -> handler.setRight(false);
        }
    }
}
