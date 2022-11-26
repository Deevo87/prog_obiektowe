package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

import static agh.ics.oop.Comperators.comparatorX;
import static agh.ics.oop.Comperators.comparatorY;

public class MapBoundary implements IPositionChangeObserver{

    private TreeSet<AbstractWorldElement> setX = new TreeSet<>(comparatorX);
    private TreeSet<AbstractWorldElement> setY = new TreeSet<>(comparatorY);
    private AbstractWorldMap map;

    public MapBoundary(AbstractWorldMap map) {
        this.map = map;
    }

    public void addToSet(AbstractWorldElement object) {
        this.setX.add(object);
        this.setY.add(object);
    }

    public void removeFromSet(AbstractWorldElement object) {
        this.setX.remove(object);
        this.setY.remove(object);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement oldObject = this.map.objectAt(oldPosition);
        removeFromSet(oldObject);

        AbstractWorldElement newObject = this.map.objectAt(newPosition);
        addToSet(newObject);
    }

    public Vector2d getLowerLeft() {
        if (setX.size() == 0) {
            return new Vector2d(0, 0);
        }
            return new Vector2d(this.setX.first().getPosition().x, this.setY.first().getPosition().y);
    }

    public Vector2d getUpperRight() {
        if (setY.size() == 0) {
            return new Vector2d(0, 0);
        }
        return new Vector2d(this.setX.last().getPosition().x, this.setY.last().getPosition().y);
    }

}
