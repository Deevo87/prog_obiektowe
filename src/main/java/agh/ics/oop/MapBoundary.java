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
//        System.out.println("removed");
//        System.out.println(object);
//        System.out.println(object.getPosition());
//        System.out.println("-------------------");
//        for (AbstractWorldElement el : setX) {
//            System.out.println(el.getPosition());
//        }
//        System.out.println("XXXXXXXXXXXXXXXX   " + setX.size() );
//        for (AbstractWorldElement el : setY) {
//            System.out.println(el.getPosition());
//        }
//        System.out.println("YYYYYYYYYYYYYYYYY   " + setY.size());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement oldObject = this.map.objectAt(oldPosition);
//        System.out.println("old position");
//        System.out.println(oldPosition);
        removeFromSet(oldObject);

        AbstractWorldElement newObject = this.map.objectAt(newPosition);
//        System.out.println(newPosition);
        addToSet(newObject);
//        System.out.println("new position");
    }

    public Vector2d getLowerLeft() {
//        System.out.println(new Vector2d(this.setX.first().getPosition().x, this.setY.first().getPosition().y));
        if (setX.size() == 0) {
            return new Vector2d(0, 0);
        }
            return new Vector2d(this.setX.first().getPosition().x, this.setY.first().getPosition().y);
    }

    public Vector2d getUpperRight() {
//        System.out.println(new Vector2d(this.setX.last().getPosition().x, this.setY.last().getPosition().y));
        if (setY.size() == 0) {
            return new Vector2d(0, 0);
        }
        return new Vector2d(this.setX.last().getPosition().x, this.setY.last().getPosition().y);
    }
}
