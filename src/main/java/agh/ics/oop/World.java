package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class World {
    static void run(Direction[] options) {
        for(Direction option : options) {
            String message = switch (option){
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwirzak idze do tyłu";
                case RIGHT -> "zwierzak skreca w prawo";
                case LEFT -> "zwierzak skreca w lewo";
            };
            System.out.println(message);
        }
    }

    static Direction[] convert_to_enum(String[] args) {
        Direction[] list = new Direction[args.length];
        int i = 0;
        for(String s : args) {
            switch (s) {
                case "f" -> list[i++] = Direction.FORWARD;
                case "b" -> list[i++] = Direction.BACKWARD;
                case "r" -> list[i++] = Direction.RIGHT;
                case "l" -> list[i++] = Direction.LEFT;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());
    }
}

