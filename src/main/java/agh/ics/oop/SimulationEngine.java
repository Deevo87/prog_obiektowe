package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SimulationEngine implements IEngine {

    private List<MoveDirection> moves;
    private IWorldMap map;
    private List<Vector2d> starting_pos;
    private List<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map,Vector2d[] starting_pos) {
        this.moves = List.of(moves);
        this.starting_pos = List.of(starting_pos);
        this.map = map;
        this.animals = new ArrayList<>();
        for (Vector2d pos : starting_pos) {
            Animal animal = new Animal(map, pos);
            this.animals.add(animal);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < this.moves.size(); i++){
            this.animals.get(i % this.animals.size()).move(this.moves.get(i));
        }
    }
    @Override
    public Vector2d positionGetter(int i) {
        return this.animals.get(i).getPosition();
    }

    @Override
    public MapDirection orientationGetter(int i){
        return this.animals.get(i).getOrientation();
    }
}
