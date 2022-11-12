package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;


public class GrassField extends AbstractWorldMap {

    private final MapVisualizer map;
    private final int quantity;

    public GrassField(int quantity) {
        this.quantity = quantity;
        this.objects = new HashMap<Vector2d, AbstractWorldElement>();
        this.map = new MapVisualizer(this);

        makeGrass(quantity);
    }
    void makeGrass(int quantity){
        Random rand = new Random();
        int bound = (int) sqrt((quantity * 10)) + 1;
        int random_x;
        int random_y;
        int i = 0;

        while (i < this.quantity) {
            random_x = rand.nextInt(bound);
            random_y = rand.nextInt(bound);
            Vector2d random = new Vector2d(random_x, random_y);
            if (!isOccupied(random)) {
                Grass g = new Grass(random);
                this.objects.put(random, g);
                i += 1;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position)) {
            return true;
        } else if (objectAt(position) instanceof Grass) {
            this.objects.remove(position);
            this.makeGrass(1);
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            this.objects.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldElement Animal = this.objects.get(oldPosition);
        AbstractWorldElement object = this.objects.get(newPosition);
        if (object instanceof Grass) {
            this.objects.remove(newPosition);
            this.makeGrass(1);
        }
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, Animal);
    }

    @Override
    public Vector2d maks() {
        int maks_x = 0;
        int maks_y = 0;

        for (Vector2d g : this.objects.keySet()) {
            if (g.x > maks_x) {
                maks_x = g.x;
            }
            if (g.y > maks_y) {
                maks_y = g.y;
            }
        }
        return new Vector2d(maks_x, maks_y);
    }

    public String toString() {
        return this.map.draw(new Vector2d(0, 0), maks());
    }
}
