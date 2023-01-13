package de.buw.se4de.entity;

import de.buw.se4de.gameflow.Handler;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import de.buw.se4de.gameflow.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

class FireballTest {

    @Test
    void testConstructor(){
        Fireball fireball = new Fireball(10, 10, 1,1,new Handler(), 10);
        assertEquals(25, fireball.getX());
        assertEquals(25, fireball.getY());
        assertEquals(30, fireball.getSizex());
        assertEquals(30, fireball.getSizey());
        // a fireball should have 0 health, as it is not a living entity
        assertEquals(0, fireball.getHealth());
    }

    @Test
    void testDrawRange(){
        Fireball fireball = new Fireball(10, 10, 1,1,new Handler(), 10);
    }

    @Test
    void testDraw(){
        Fireball fireball = new Fireball(10, 10, 1,1,new Handler(), 10);
    }
}