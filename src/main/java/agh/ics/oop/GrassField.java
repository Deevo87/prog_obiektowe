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
        this.maks = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.lower = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        makeGrass(quantity);
        calBounds();
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
            this.makeGrass(1);
            this.mapBoundary.removeFromSet(object);
        } else {
            this.objects.put(newPosition, Animal);
            this.mapBoundary.positionChanged(oldPosition, newPosition);
            this.objects.remove(oldPosition);
        }
    }

    public Vector2d upRight() {
        this.maks = this.mapBoundary.getUpperRight();
        return this.mapBoundary.getUpperRight();
    }

    public Vector2d lowLeft() {
        this.lower =  this.mapBoundary.getLowerLeft();
        return this.mapBoundary.getLowerLeft();
    }

    public void calBounds() {
        int maxX = mapBoundary.getUpperRight().x;
        int maxY = mapBoundary.getUpperRight().y;
        int minX = mapBoundary.getLowerLeft().x;
        int minY = mapBoundary.getLowerLeft().y;
        this.lower = new Vector2d(minX, minY);
        this.maks = new Vector2d(maxX, maxY);
        System.out.println(this.lower);
        System.out.println(this.maks);
        Vector2d test1 = getLowerLeft();
    }

    public String toString() {
        calBounds();
        return mapVis.draw(this.lower, this.maks);
    }
}
