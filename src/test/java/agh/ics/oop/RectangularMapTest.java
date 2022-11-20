package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void test() {
        String[] args = {"r", "l", "f", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f",
                "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(4, 5)};
        IWorldMap map = new RectangularMap(10,10);
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(new Vector2d(3, 0), engine.positionGetter(0));
        assertEquals(MapDirection.SOUTH, engine.orientationGetter(0));
        assertEquals(new Vector2d(0, 5), engine.positionGetter(1));
        assertEquals(MapDirection.WEST, engine.orientationGetter(1));
    }

    @Test
    void OptionParser() {
        String[] args = {"balls" ,"r", "l", "f", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f",
                "f", "f", "f", "f", "f"};
        assertThrows(IllegalArgumentException.class, () -> new OptionsParser().parse(args));
    }
}