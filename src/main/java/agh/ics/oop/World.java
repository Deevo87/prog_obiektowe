package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
//            MoveDirection[] directions = new OptionsParser().parse(args);
//            AbstractWorldMap map = new GrassField(10);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(8, 4)};
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            System.out.println(map.toString());
//            engine.run();
//            System.out.println(map.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println("Change input :/" + '\n' + ex.getMessage());
            System.exit(0);
        }
    }
}

