package agh.ics.oop;

public class Grass {

    private Vector2d grass_pos;

    public Grass(Vector2d grass_pos){
        this.grass_pos = grass_pos;
    }

    public Vector2d getPosition() {
        return this.grass_pos;
    }

    public String toString() {
        return "*";
    }


}
