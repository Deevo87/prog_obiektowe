package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    public void start(Stage primaryStage){
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        AbstractWorldMap map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(8, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());

        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 400, 400);

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int width = 30;
        int height = 30;
        int minCol = lowerLeft.y;
        int maxCol = upperRight.y;
        int minRow = lowerLeft.x;
        int maxRow = upperRight.x;

        grid.setAlignment(Pos.CENTER);
        Label first = new Label("y/x");
        GridPane.setHalignment(first, HPos.CENTER);
        grid.add(first, 0, 0);

        for (int i = 0; i <= maxCol - minCol + 1; ++i) {
            grid.getRowConstraints().add(new RowConstraints(height));
        }

        for (int i = 0; i <= maxRow - minRow + 1; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(width));
        }

        for (int e = minCol; e < maxCol+1; e++) {
            Label label = new Label(Integer.toString(e));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, maxCol - e +1,1,1);
        }

        for (int e = maxRow; e >= minRow; e--) {
            Label label = new Label(Integer.toString(e));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, e-minRow+1, 0,1,1);
        }

        for (int x = maxRow; x >= minRow; --x) {
            for (int y = minCol; y <= maxCol; ++y) {
                Vector2d position = new Vector2d(x, y);
                String text;
                if (map.isOccupied(position)){
                    text = map.objectAt(position).toString();
                } else {
                    text = "";
                }
                Label label = new Label(text);
                GridPane.setHalignment(label, HPos.CENTER);
                grid.add(label, position.x - minRow + 1, maxCol - position.y + 1);
            }
        }

        grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
