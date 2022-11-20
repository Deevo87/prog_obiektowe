package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{
    private final MapVisualizer mapVis;

    private final int width;
    private final int height;
    public Vector2d maks;
    public Vector2d mini;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.maks = new Vector2d(width - 1, height - 1);
        this.mini = new Vector2d(0, 0);
        this.mapVis = new MapVisualizer(this);
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
        throw new IllegalArgumentException(animal.getPosition().toString() + "is occupied, you can't place there another animal. :(");
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement animal = this.objects.get(oldPosition);
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, animal);
    }

    public String toString() {
        return this.mapVis.draw(mini, maks);
    }
}
