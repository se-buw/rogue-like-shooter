package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.ID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirefighterTest {

    @Test
    void testConstructorTrivialInput(){
        Firefighter firefighter = new Firefighter(10, 10, ID.Player, new Handler(), 10);
        assertEquals(25, firefighter.getX());
        assertEquals(25, firefighter.getY());
        assertEquals(ID.Player, firefighter.getId());
        assertEquals(10, firefighter.getHealth());
    }

    @Test
    void testConstructorNegativeInput(){
        Firefighter firefighter = new Firefighter(-10, -10, ID.Player, new Handler(), -10);
        assertNotEquals(-10, firefighter.getX(), "X-Coordinate should not be -10");
        assertNotEquals(-10, firefighter.getY(),"Y-Coordinate should not be -10");
        assertEquals(ID.Player, firefighter.getId());
        assertNotEquals(-10, firefighter.getHealth(),"Hearts should not be -10");
    }

    @Test
    void testTakeDamagePositive(){
        Firefighter firefighter = new Firefighter(10, 10, ID.Player, new Handler(), 10);
        firefighter.takedamage(5);
        assertEquals(5, firefighter.getHealth());
    }

    @Test
    void testTakeDamageNegative(){
        Firefighter firefighter = new Firefighter(10, 10, ID.Player, new Handler(), 10);
        firefighter.takedamage(-1);
        assertEquals(10, firefighter.getHealth());
    }

    @Test
    void testTakeDamageNull(){
        Firefighter firefighter = new Firefighter(10, 10, ID.Player, new Handler(), 10);
        firefighter.takedamage(0);
        assertEquals(10, firefighter.getHealth());
    }

}