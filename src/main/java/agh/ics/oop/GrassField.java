package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;


public class GrassField extends AbstractWorldMap {

    private final MapVisualizer mapVis;
    private final int quantity;
    private final MapBoundary mapBoundary;

    public GrassField(int quantity) {
        this.mapBoundary = new MapBoundary(this);
        this.quantity = quantity;
        this.objects = new HashMap<Vector2d, AbstractWorldElement>();
        this.mapVis = new MapVisualizer(this);
        this.lower = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.maks = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        makeGrass(quantity);
    }
    void makeGrass(int quantity){
        Random rand = new Random();
        int bound = (int) sqrt((quantity * 10)) + 1;
        int random_x;
        int random_y;
        int i = 0;

        while (i < quantity) {
            random_x = rand.nextInt(bound);
            random_y = rand.nextInt(bound);
            Vector2d random = new Vector2d(random_x, random_y);
            if (!isOccupied(random)) {
                Grass g = new Grass(random);
                this.objects.put(random, g);
                this.mapBoundary.addToSet(g);
                i += 1;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)) {
            return true;
        } else if (objectAt(position) instanceof Grass) {
            this.mapBoundary.removeFromSet(objectAt(position));
            this.objects.remove(position);
            this.makeGrass(1);
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.objects.put(animal.getPosition(), animal);
            animal.addObserver(this);
            this.mapBoundary.addToSet(animal);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition().toString() + "is occupied, you can't place there another animal. :(");
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement Animal = this.objects.get(oldPosition);
        AbstractWorldElement object = this.objects.get(newPosition);
        if (object instanceof Grass) {
            this.objects.remove(newPosition);
            this.mapBoundary.removeFromSet(object);
            this.makeGrass(1);
        }
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, Animal);
        this.mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public void maks() {
        this.maks = this.mapBoundary.getUpperRight();
    }

    public void lower() {
        this.lower =  this.mapBoundary.getLowerLeft();
    }

    public String toString() {
        maks();
        lower();
        return mapVis.draw(this.lower, this.maks);
    }
}
