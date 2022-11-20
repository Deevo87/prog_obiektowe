package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            System.out.println(map.toString());
            engine.run();
            System.out.println(map.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println("Change input :/" + '\n' + ex.getMessage());
            System.exit(0);
        }
    }
}

