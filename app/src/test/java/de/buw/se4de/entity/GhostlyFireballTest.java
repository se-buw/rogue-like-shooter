package de.buw.se4de.entity;

import de.buw.se4de.ID;
import de.buw.se4de.gameflow.Handler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostlyFireballTest {

    @Test
    void testConstructor(){
        GhostlyFireball ghostlyfireball = new GhostlyFireball(10, 10, 1,1, new Handler());
        // GhostlyFireballs are offset by -15 (half of GhostFires size) on both axes to center their spawn point
        // getX/Y returns the x/y coordinate with an offset of sizeX/Y / 2
        assertEquals(10, ghostlyfireball.getX());
        assertEquals(10, ghostlyfireball.getY());
        assertEquals(30, ghostlyfireball.getSizex());
        assertEquals(30, ghostlyfireball.getSizey());
        assertEquals(ID.PROJECTILE, ghostlyfireball.getId());
    }

}