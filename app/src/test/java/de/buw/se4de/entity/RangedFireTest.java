package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedFireTest {

    @Test
    void testConstructor(){
        RangedFire rangedfire = new RangedFire(10, 10, new Handler());
        assertEquals(25, rangedfire.getX());
        assertEquals(25, rangedfire.getY());
        assertEquals(30, rangedfire.getSizex());
        assertEquals(30, rangedfire.getSizey());
        assertEquals(ID.Enemy, rangedfire.getId());
        assertEquals(2, rangedfire.oncooldown);
        assertEquals(0, rangedfire.getHealth());
    }
}