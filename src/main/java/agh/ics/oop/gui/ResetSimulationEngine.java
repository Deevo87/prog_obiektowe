package agh.ics.oop.gui;

import agh.ics.oop.*;

import java.util.ArrayList;
import java.util.List;

public class ResetSimulationEngine implements IEngine, Runnable{
    private List<MoveDirection> moves;
    private IWorldMap map;
    private List<Vector2d> starting_pos;
    private List<Animal> animals;
    private int moveDelay;
    private List<IMapChangeObserver> observers;

    public ResetSimulationEngine(IWorldMap map,Vector2d[] starting_pos) {
        this.starting_pos = List.of(starting_pos);
        this.map = map;
        this.animals = new ArrayList<>();
        this.moveDelay = 500;
        this.observers = new ArrayList<>();
        for (Vector2d pos : starting_pos) {
            Animal animal = new Animal(map, pos);
            this.animals.add(animal);
            this.map.place(animal);
        }
    }

    public ResetSimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] starting_pos) {
        this(map, starting_pos);
        setMoves(moves);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.moves.size(); i++){
                this.animals.get(i % this.animals.size()).move(this.moves.get(i));
//                System.out.println(this.map.toString());
                this.map.calBounds();
                mapReset();
                Thread.sleep(moveDelay);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted animal move!");
            System.exit(1);
        }
    }

    public void mapReset() {
        for (IMapChangeObserver observer : this.observers) {
            observer.reset();
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

    public void addObserver(IMapChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IMapChangeObserver observer) {
        this.observers.remove(observer);
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = List.of(moves);
    }
}
