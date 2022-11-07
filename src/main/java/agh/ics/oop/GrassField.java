package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;


public class GrassField extends AbstractWorldMap{

    private final MapVisualizer map;
    private int quantity;
    public List<Animal> animals;
    public List<Grass> grass;

    public GrassField(int quantity) {
        this.quantity = quantity;
        this.grass = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.map = new MapVisualizer(this);

        Random rand = new Random();
        int bound = (int) sqrt((quantity*10)) + 1;
        int random_x;
        int random_y;
        int i = 0;
        boolean flag;

        while (i < this.quantity) {
            random_x = rand.nextInt(bound);
            random_y = rand.nextInt(bound);
            Vector2d random = new Vector2d(random_x, random_y);
            if (!isOccupied(random)){
                flag = false;
                for (Grass g : this.grass) {
                    if (g.getPosition().equals(random)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Grass g = new Grass(random);
                    this.grass.add(g);
                    i += 1;
                }
            }
        }
    }

    @Override
    public boolean canMoveToG(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean placeG(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupiedG(Vector2d position) {
        Object object = objectAt(position);
        if (object instanceof Animal){
            for (Animal a : this.animals){
                if (a.isAt(position)){
                    return true;
                }
            }
        }
        if (object instanceof Grass){
            for (Grass g : this.grass) {
                if (g.getPosition().equals(position)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object objectAtG(Vector2d position) {
        for (Animal a : this.animals){
            if (a.isAt(position)){
                return a;
            }
        }
        for (Grass g : this.grass){
            if (g.getPosition().equals(position)){
                return g;
            }
        }
        return null;
    }

    @Override
    public Vector2d maks() {
        int maks_x = 0;
        int maks_y = 0;

        for (Grass g : this.grass) {
            if (g.getPosition().x > maks_x) {
                maks_x = g.getPosition().x;
            }
            if (g.getPosition().y > maks_y) {
                maks_y = g.getPosition().y;
            }
        }
        return new Vector2d(maks_x, maks_y);
    }

    public String toString() {
        return this.map.draw(new Vector2d(0, 0) , maks());
    }
}
