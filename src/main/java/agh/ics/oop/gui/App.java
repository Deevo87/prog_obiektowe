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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application implements IMapChangeObserver{
    private GridPane grid;
    private AbstractWorldMap map;
    public void start(Stage primaryStage) throws FileNotFoundException {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(8, 4)};
        IEngine engine = new SimulationEngine(directions, this.map, positions);
        System.out.println(this.map.toString());
        engine.run();
        System.out.println(this.map.toString());

        this.grid = new GridPane();
        Scene scene = new Scene(this.grid, 600, 600);

        placeElements();

        this.grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void placeElements() throws FileNotFoundException {
        Vector2d lowerLeft = this.map.getLowerLeft();
        Vector2d upperRight = this.map.getUpperRight();

        int width = 40;
        int height = 40;
        int minCol = lowerLeft.y;
        int maxCol = upperRight.y;
        int minRow = lowerLeft.x;
        int maxRow = upperRight.x;

        this.grid.setAlignment(Pos.CENTER);
        Label first = new Label("y/x");
        GridPane.setHalignment(first, HPos.CENTER);
        this.grid.add(first, 0, 0);

        for (int i = 0; i <= maxCol - minCol + 1; ++i) {
            this.grid.getRowConstraints().add(new RowConstraints(height));
        }

        for (int i = 0; i <= maxRow - minRow + 1; ++i) {
            this.grid.getColumnConstraints().add(new ColumnConstraints(width));
        }

        for (int e = minCol; e < maxCol+1; e++) {
            Label label = new Label(Integer.toString(e));
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, 0, maxCol - e +1,1,1);
        }

        for (int e = maxRow; e >= minRow; e--) {
            Label label = new Label(Integer.toString(e));
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, e-minRow+1, 0,1,1);
        }

        for (int x = maxRow; x >= minRow; --x) {
            for (int y = minCol; y <= maxCol; ++y) {
                Vector2d position = new Vector2d(x, y);
                if (this.map.isOccupied(position)){
                    IMapElement IMapElement = (IMapElement) this.map.objectAt(position);
                    GuiElementBox elementBox = new GuiElementBox(IMapElement);
                    VBox vbox = elementBox.boxGetter();
                    GridPane.setHalignment(vbox, HPos.CENTER);
                    this.grid.add(vbox, position.x - minRow + 1, maxCol - position.y + 1);
                }
            }
        }
    }
    @Override
    public void reset() {
        this.grid.getChildren().clear();
    }
}
