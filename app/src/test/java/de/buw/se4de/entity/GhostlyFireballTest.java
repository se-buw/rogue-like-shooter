package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostlyFireballTest {

    @Test
    void testConstructor(){
        GhostlyFireball ghostlyfireball = new GhostlyFireball(10, 10, 1,1, new Handler());
        assertEquals(0, ghostlyfireball.getX());
        assertEquals(0, ghostlyfireball.getY());
        assertEquals(30, ghostlyfireball.getSizex());
        assertEquals(30, ghostlyfireball.getSizey());
        assertEquals(ID.PROJECTILE, ghostlyfireball.getId());
    }

}