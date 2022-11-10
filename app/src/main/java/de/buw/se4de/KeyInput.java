package de.buw.se4de;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler objects){
        this.handler = objects;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if (temp.id == ID.Player){
                if (key == KeyEvent.VK_W) {
                    handler.setUp(true);
                }
                else if (key == KeyEvent.VK_A) {
                    handler.setLeft(true);
                }
                else if (key == KeyEvent.VK_S) {
                    handler.setDown(true);
                }
                else if (key == KeyEvent.VK_D) {
                    handler.setRight(true);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++){
            Object temp = handler.objects.get(i);

            if (temp.id == ID.Player){
                if (key == KeyEvent.VK_W) {
                    handler.setUp(false);
                }
                else if (key == KeyEvent.VK_A) {
                    handler.setLeft(false);
                }
                else if (key == KeyEvent.VK_S) {
                    handler.setDown(false);
                }
                else if (key == KeyEvent.VK_D) {
                    handler.setRight(false);
                }
            }
        }
    }
}
