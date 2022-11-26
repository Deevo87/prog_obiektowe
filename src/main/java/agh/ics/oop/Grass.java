package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass extends AbstractWorldElement implements IMapElement{

    private Vector2d grass_pos;

    public Grass(Vector2d grass_pos){
        this.grass_pos = grass_pos;
    }

    public Vector2d getPosition() {
        return this.grass_pos;
    }

    @Override
    public Image getMapElImage() throws FileNotFoundException {
        return new Image(new FileInputStream("src/main/resources/grass.png"));
    }

    public String toString() {
        return "*";
    }

}
