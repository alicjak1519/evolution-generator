import evolutionWorld.Vector2d;
import org.junit.Test;

import static org.junit.Assert.*;


public class Vector2dTest {
    Vector2d testVectorA = new Vector2d(1, 1);
    Vector2d testVectorB = new Vector2d(2, 2);
    Vector2d testVectorC = new Vector2d(1, 1);
    Vector2d testVectorD = new Vector2d(-1, -1);

    @Test
    public void equalsTest() {
        assertTrue(testVectorA.equals(testVectorC));
    }

    @Test
    public void toStringTest() {
        assertEquals(testVectorA.toString(), "(1,1)");
    }

    @Test
    public void precedesTest() {
        assertFalse(testVectorA.precedes(testVectorD));
    }

    @Test
    public void followsTest() {
        assertTrue(testVectorA.follows(testVectorD));
    }

    @Test
    public void upperRightTest() {
        assertEquals(testVectorA.upperRight(testVectorB), testVectorB);
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(testVectorA.lowerLeft(testVectorB), testVectorA);
    }

    @Test
    public void addTest() {
        assertEquals(testVectorA.add(testVectorC), testVectorB);
    }

    @Test
    public void subtractTest() {
        assertEquals(testVectorA.subtract(testVectorB), testVectorD);
    }

    @Test
    public void oppositeTest() {
        assertEquals(testVectorA.opposite(), testVectorD);
    }
}
