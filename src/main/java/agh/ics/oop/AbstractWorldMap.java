package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d lower;
    protected Vector2d maks;
    Map<Vector2d, AbstractWorldElement> objects = new HashMap<Vector2d, AbstractWorldElement>();

    @Override
    public boolean isOccupied(Vector2d position) {
        if (this.objects.get(position) != null){
            return this.objects.containsKey(position);
        }
        return false;
    }

    @Override
    public AbstractWorldElement objectAt(Vector2d position) {
        return this.objects.get(position);
    }


}

