package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal animal = new Animal();

// sprawdzam czy zwierzę ma dobrą orientacje
    @Test
    void properOrientation(){
        assertTrue(animal.isAt(new Vector2d(2, 2)));

        String[] orient = {"r", "right", "l", "left", "right", "right"};
        MoveDirection[] moves = new OptionsParser().parse(orient);
        for (MoveDirection move : moves){
            animal.move(move);
        }
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }

// sprawdzam czy zwierzę porusza się na odpowiednie pola
    @Test
    void properPosition(){
        String[] pos = {"forward", "f", "r", "b", "backward", "l", "left",
                "l", "left", "f", "forward", "essa", "forward", "F", "F"};
        MoveDirection[] moves = new OptionsParser().parse(pos);
        for (MoveDirection move : moves){
            animal.move(move);
        }
        assertTrue(animal.isAt(new Vector2d(3, 4)));
    }

// sprawdzam czy zwierzę nie wychodzi poza granice
    @Test
    void borders(){
        String[] pos = {"f", "f", "f", "f", "fast", "french"};
        MoveDirection[] moves = new OptionsParser().parse(pos);
        for (MoveDirection move : moves){
            animal.move(move);
        }
        assertFalse(animal.isAt(new Vector2d(2, 6)));
        assertTrue(animal.isAt(new Vector2d(2, 4)));
        assertTrue(animal.getPosition().preceds(new Vector2d(5, 5)));
    }
// sprawdzam czy dane wejściowe podane jako łańcuch znaków są poprawnie interpretowane (w kazdym powyższym teście to robiłem)

    @Test
    void character_string(){
        String[] pos = {"f", "flaming", "l", "icecream", "backward", "b", "right", "r", "forward", "f"};
        MoveDirection[] moves = new OptionsParser().parse(pos);
        for (MoveDirection move : moves){
            animal.move(move);
        }
        assertTrue(animal.isAt(new Vector2d(4, 3)));
    }
}