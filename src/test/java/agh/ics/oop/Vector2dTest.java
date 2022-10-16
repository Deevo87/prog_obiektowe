package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    Object other = new Vector2d(3, 3);
    Vector2d vec1 = new Vector2d(3, 3);
    Vector2d vec2 = new Vector2d(-3, -3);


    @Test
    void testToString() {
        assertEquals(vec1.toString(), "(3,3)");
    }

    @Test
    void preceds() {
        assertTrue(vec1.preceds(vec2));
        assertFalse(vec2.preceds(vec1));
    }

    @Test
    void follows() {
        assertTrue(vec2.follows(vec1));
        assertFalse(vec1.follows(vec2));
    }

    @Test
    void add() {
        assertEquals(vec1.add(vec2), new Vector2d(3 + (-3), 3 + (-3)));
    }

    @Test
    void subtract() {
        assertEquals(vec1.subtract(vec2), new Vector2d(-3 - 3, -3 - 3));
    }

    @Test
    void upperRight() {
        assertEquals(vec1.upperRight(vec2), new Vector2d(3, 3));
    }

    @Test
    void lowerLeft() {
        assertEquals(vec1.lowerLeft(vec2), new Vector2d(-3, -3));
    }

    @Test
    void opposite() {
        assertEquals(vec1.opposite(), new Vector2d(-3, -3));
    }

    @Test
    void testEquals() {
        assertTrue(vec1.equals(other)); //other jest typu Object
    }
}