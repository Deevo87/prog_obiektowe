package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;
    public List<Animal> animals;

    public Vector2d maks;
    public Vector2d mini;

    public MapVisualizer map;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
        this.maks = new Vector2d(width - 1, height - 1);
        this.mini = new Vector2d(0, 0);
        this.map = new MapVisualizer(this);
    }

    @Override
    public Vector2d maks() {
        return null;
    }

    @Override
    public boolean canMoveToG(Vector2d position) {
        if (!isOccupied(position)){
            return position.follows(mini) && position.preceds(maks);
        }
        return false;
    }

    @Override
    public boolean placeG(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupiedG(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAtG(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return a;
            }
        }
        return null;
    }

    public String toString() {
        return this.map.draw(mini, maks);
    }
}
