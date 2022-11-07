package agh.ics.oop;

import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    protected MapVisualizer map;
    protected List<Animal> animals;

    public abstract Vector2d maks();
    abstract boolean canMoveToG(Vector2d position);
    abstract boolean placeG(Animal animal);
    abstract boolean isOccupiedG(Vector2d position);
    abstract Object objectAtG(Vector2d position);
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)){
            return position.follows(new Vector2d(0, 0)) && position.preceds(maks());
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return a;
            }
        }
        return null;
    }

    public String toString() {
        return this.map.draw(new Vector2d(0, 0), maks());
    }
}

