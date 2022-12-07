package de.buw.se4de.entity;

import de.buw.se4de.ID;
import org.junit.jupiter.api.Test;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FireTest {

    @Test
    void testConstructor(){
        Fire fire = new Fire(10, 10, new Handler());
        assertEquals(25, fire.getX());
        assertEquals(25, fire.getY());
        assertEquals(30, fire.getSizex());
        assertEquals(30, fire.getSizey());
        assertEquals(1, fire.attackdamage);
        assertEquals(4, fire.movementspeed);
        assertEquals(2, fire.health);
    }

}