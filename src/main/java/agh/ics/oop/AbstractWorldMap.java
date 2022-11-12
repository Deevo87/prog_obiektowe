package agh.ics.oop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected MapVisualizer map;
    Map<Vector2d, AbstractWorldElement> objects = new HashMap<Vector2d, AbstractWorldElement>();

    @Override
    public boolean isOccupied(Vector2d position) {
        if (this.objects.get(position) != null){
//            return this.objects.get(position).getPosition().equals(position);
            return this.objects.containsKey(position);
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.objects.get(position);
    }

    public String toString() {
        return this.map.draw(new Vector2d(0, 0), maks());
    }
}

