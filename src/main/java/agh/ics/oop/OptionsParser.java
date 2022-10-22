package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        int len = 0;
        for (String arg : args){
            if (arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward") ||
                    arg.equals("r") || arg.equals("right") || arg.equals("l") || arg.equals("left"))
                len += 1;
        }
        MoveDirection[] list = new MoveDirection[len];
        int i = 0;
        for(String s : args) {
            switch (s) {
                case "f", "forward" -> list[i++] = MoveDirection.FORWARD;
                case "b", "backward" -> list[i++] = MoveDirection.BACKWARD;
                case "r", "right" -> list[i++] = MoveDirection.RIGHT;
                case "l", "left" -> list[i++] = MoveDirection.LEFT;
                default -> {
                }
            }
        }
        return list;
    }
}
