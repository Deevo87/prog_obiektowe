package agh.ics.oop;


public class Animal {
    private MapDirection orientation = MapDirection.NORTH;

    private Vector2d position = new Vector2d(2,2);

    public String toString(){
        return this.orientation.toString() + ' ' + this.position.toString();
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
        Vector2d maks = new Vector2d(4, 4);
        Vector2d mini = new Vector2d(0, 0);
        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case BACKWARD -> {
                Vector2d save = this.position.subtract(this.orientation.toUnitVector());
                if (save.preceds(maks) && save.follows(mini)){
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                }
            }
            case FORWARD -> {
                Vector2d save = this.position.add(this.orientation.toUnitVector());
                if (save.preceds(maks) && save.follows(mini)){
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
        }
    }
}
