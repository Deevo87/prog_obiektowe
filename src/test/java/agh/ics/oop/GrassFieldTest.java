package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void test(){
        String[] args = {"r", "l", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(4,5) };
        IWorldMap map = new GrassField(10);
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());
        assertEquals(new Vector2d(2, 0), engine.positionGetter(0));
        System.out.println(engine.positionGetter(0));
        assertEquals(MapDirection.EAST, engine.orientationGetter(0));

        assertEquals(new Vector2d(3, 5), engine.positionGetter(1));
        assertEquals(MapDirection.WEST, engine.orientationGetter(1));
    }
}