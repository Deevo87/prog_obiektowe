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
        Vector2d maks = new Vector2d(5, 5);
        Vector2d mini = new Vector2d(-1, -1);
        switch (direction){
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case BACKWARD -> {
                switch (this.orientation) {
                    case NORTH -> {
                        this.position = this.position.add(new Vector2d(0, -1));{
                            if (this.position.precedsWithAlt(mini)){
                                System.out.println("kurwa mać");
                                this.position = this.position.add(new Vector2d(0, 1));
                            }
                        }
                    }
                    case SOUTH -> {
                        this.position = this.position.add(new Vector2d(0, 1));{
                            if (this.position.precedsWithAlt(mini)){
                                this.position = this.position.add(new Vector2d(0, -1));
                            }
                        }
                    }
                    case EAST -> {
                        this.position = this.position.add(new Vector2d(-1, 0));{
                            if (this.position.precedsWithAlt(mini)){
                                this.position = this.position.add(new Vector2d(1, 0));
                            }
                        }
                    }
                    case WEST -> {
                        this.position = this.position.add(new Vector2d(1, 0));{
                            if (this.position.precedsWithAlt(mini)){
                                this.position = this.position.add(new Vector2d(-1, 0));
                            }
                        }
                    }
                }
            }
            case FORWARD -> {
                switch (this.orientation) {
                    case NORTH -> {
                        this.position = this.position.add(new Vector2d(0, 1));
                        if (this.position.followsWithAlt(maks)){
                            this.position = this.position.add(new Vector2d(0, -1));
                        }
                    }
                    case SOUTH -> {
                        this.position = this.position.add(new Vector2d(0, -1));
                        if (this.position.followsWithAlt(mini)){
                            this.position = this.position.add(new Vector2d(0, 1));
                        }
                    }
                    case EAST -> {
                        this.position = this.position.add(new Vector2d(1, 0));
                        if (this.position.followsWithAlt(maks)){
                            this.position = this.position.add(new Vector2d(-1, 0));
                        }
                    }
                    case WEST -> {
                        this.position = this.position.add(new Vector2d(-1, 0));
                        if (this.position.followsWithAlt(mini)){
                            this.position = this.position.add(new Vector2d(1, 0));
                        }
                    }
                }
            }
            default -> {
                break;
            }
        }
    }
}
