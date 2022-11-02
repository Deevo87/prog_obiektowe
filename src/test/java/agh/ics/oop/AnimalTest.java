package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    String[] args1 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f"};
    String[] args2 = {"l", "l", "l", "l", "l"};
    IWorldMap map = new RectangularMap(10, 5);
    Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };


    @Test
    void borders(){ // sprawdzam czy zwierzęta nie wychodzą za granice, czy mogą
        //wchodzić na pola zajęte przez inne zwierzęta, czy zmiana kierunków działa poprawnie
        MoveDirection[] directions = new OptionsParser().parse(args1);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertEquals(new Vector2d(2, 0), engine.positionGetter(0));
        assertEquals(MapDirection.SOUTH, engine.orientationGetter(0));

        assertEquals(new Vector2d(3, 4), engine.positionGetter(1));
        assertEquals(MapDirection.NORTH, engine.orientationGetter(1));
    }

    @Test
    void directions(){ //sprawdzam poprawność zmiany kierunku
        MoveDirection[] directions = new OptionsParser().parse(args2);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(MapDirection.EAST, engine.orientationGetter(0));
        assertEquals(MapDirection.SOUTH, engine.orientationGetter(1));
    }
}