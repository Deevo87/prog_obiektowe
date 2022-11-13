package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldElement{

    private IWorldMap map;

    private Vector2d position;
    private List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.observers = new ArrayList<>();
    }

    private MapDirection orientation = MapDirection.NORTH;

    public String toString(){
        return switch (this.orientation) {
            case NORTH -> String.valueOf('N');
            case WEST -> String.valueOf('W');
            case SOUTH -> String.valueOf('S');
            case EAST -> String.valueOf('E');
        };
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case BACKWARD -> {
                Vector2d save = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(save)){
                    this.positionChanged(this.position, this.position.subtract(this.orientation.toUnitVector()));
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                }
            }
            case FORWARD -> {
                Vector2d save = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(save)){
                    this.positionChanged(this.position, this.position.add(this.orientation.toUnitVector()));
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
        }

    }
    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        observers.forEach(observer -> observer.positionChanged(oldPosition, newPosition));
    }
}
