package de.buw.se4de;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler objects; //we need a better name for it. see line 15

    public KeyInput(Handler objects){
        this.objects = objects;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < objects.objects.size();i++){
            Object temp = objects.objects.get(i);

            if (temp.id == ID.Player){
                if (key == KeyEvent.VK_W) {
                    objects.setUp(true);
                }
                else if (key == KeyEvent.VK_A) {
                    objects.setLeft(true);
                }
                else if (key == KeyEvent.VK_S) {
                    objects.setDown(true);
                }
                else if (key == KeyEvent.VK_D) {
                    objects.setRight(true);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < objects.objects.size();i++){
            Object temp = objects.objects.get(i);

            if (temp.id == ID.Player){
                if (key == KeyEvent.VK_W) {
                    objects.setUp(false);
                }
                else if (key == KeyEvent.VK_A) {
                    objects.setLeft(false);
                }
                else if (key == KeyEvent.VK_S) {
                    objects.setDown(false);
                }
                else if (key == KeyEvent.VK_D) {
                    objects.setRight(false);
                }
            }
        }
    }
}
