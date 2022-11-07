package agh.ics.oop;

import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected MapVisualizer map;
    protected List<Animal> animals;

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }
//    @Override
//    public Object objectAt(Vector2d position) {
//        for (Animal a : this.animals){
//            if (a.isAt(position)){
//                return a;
//            }
//        }
//        return null;
//    }

    public String toString() {
        return this.map.draw(new Vector2d(0, 0), maks());
    }
}

