import evolutionWorld.classes.MapDirection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MapDirectionTest {

    @Test
    public void nextTest() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.NORTHEAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.SOUTHWEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTHWEST);
    }

    @Test
    public void previousTest() {
        assertEquals(MapDirection.NORTH.previous(), MapDirection.NORTHWEST);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTHEAST);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTHWEST);
    }
}
