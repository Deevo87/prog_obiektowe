package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{

    private final int width;
    private final int height;
    public Vector2d maks;
    public Vector2d mini;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.maks = new Vector2d(width - 1, height - 1);
        this.mini = new Vector2d(0, 0);
        this.map = new MapVisualizer(this);
    }

    @Override
    public Vector2d maks() {
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)){
            return position.follows(mini) && position.preceds(maks);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.objects.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement animal = this.objects.get(oldPosition);
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, animal);
    }

    public String toString() {
        return this.map.draw(mini, maks);
    }
}
