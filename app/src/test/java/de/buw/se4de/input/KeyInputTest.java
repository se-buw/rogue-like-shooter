package de.buw.se4de.input;

import org.junit.jupiter.api.Test;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static org.junit.jupiter.api.Assertions.*;

class KeyInputTest {

    @Test
    void testKeyPressedW(){
        Handler h = new Handler();
        KeyInput k = new KeyInput(h);
        Component component = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };

        KeyEvent KeyEvent = new KeyEvent(component, 401, 1670522242, 0, 87, 'w', 1);
        k.keyPressed(KeyEvent);
        assertEquals(true, k.handler.isUp());

        k.keyPressed(KeyEvent);
    }

    @Test
    void testKeyPressedA(){
        Handler h = new Handler();
        KeyInput k = new KeyInput(h);
        Component component = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };

        KeyEvent KeyEvent = new KeyEvent(component, 401, 1670522242, 0, 65, 'a', 1);
        k.keyPressed(KeyEvent);
        assertEquals(true, k.handler.isLeft());

        k.keyPressed(KeyEvent);
    }

    @Test
    void testKeyPressedS(){
        Handler h = new Handler();
        KeyInput k = new KeyInput(h);
        Component component = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };

        KeyEvent KeyEvent = new KeyEvent(component, 401, 1670522242, 0, 83, 's', 1);
        k.keyPressed(KeyEvent);
        assertEquals(true, k.handler.isDown());

        k.keyPressed(KeyEvent);
    }

    @Test
    void testKeyPressedD(){
        Handler h = new Handler();
        KeyInput k = new KeyInput(h);
        Component component = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };

        KeyEvent KeyEvent = new KeyEvent(component, 401, 1670522242, 0, 68, 'd', 1);
        k.keyPressed(KeyEvent);
        assertEquals(true, k.handler.isRight());

        k.keyPressed(KeyEvent);
    }
}