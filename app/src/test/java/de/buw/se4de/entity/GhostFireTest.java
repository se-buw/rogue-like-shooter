package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostFireTest {

    @Test
    void testConstructor(){
        GhostFire ghostfire = new GhostFire(10, 10, new Handler());
        assertEquals(25, ghostfire.getX());
        assertEquals(25, ghostfire.getY());
        assertEquals(30, ghostfire.getSizex());
        assertEquals(30, ghostfire.getSizey());
    }

}