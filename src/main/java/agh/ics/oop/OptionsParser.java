package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        MoveDirection[] list = new MoveDirection[args.length];
        int i = 0;
        for(String s : args) {
            switch (s) {
                case "f", "forward" -> list[i++] = MoveDirection.FORWARD;
                case "b", "backward" -> list[i++] = MoveDirection.BACKWARD;
                case "r", "right" -> list[i++] = MoveDirection.RIGHT;
                case "l", "left" -> list[i++] = MoveDirection.LEFT;
            }
        }
        return Arrays.copyOfRange(list, 0, i);
    }
}
