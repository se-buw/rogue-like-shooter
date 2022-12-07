package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuddleTest {

    @Test
    void testConstructor(){
        Puddle puddle = new Puddle(10, 10, new Handler());
        assertEquals(25, puddle.getX());
        assertEquals(25, puddle.getY());
        assertEquals(30, puddle.getSizex());
        assertEquals(30, puddle.getSizey());
        assertEquals(ID.PROJECTILE, puddle.getId());
        assertEquals(1, puddle.damage);
        assertEquals(0, puddle.active);
    }
}