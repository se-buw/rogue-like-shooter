package de.buw.se4de.input;

import org.junit.jupiter.api.Test;
import de.buw.se4de.gameflow.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static org.junit.jupiter.api.Assertions.*;

class KeyInputTest {

    @Test
    void testKeyInput(){
        KeyInput keyInput = new KeyInput(new Handler());
        //keyInput.keyPressed();
    }
}