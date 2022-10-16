package agh.ics.oop;

public class World {
    static void run(Direction[] options) {
        for(Direction option : options) {
            String message = switch (option){
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwirzak idze do tyłu";
                case RIGHT -> "zwierzak skreca w prawo";
                case LEFT -> "zwierzak skreca w lewo";
            };
            System.out.println(message);
        }
    }

    static Direction[] convert_to_enum(String[] args) {
        Direction[] list = new Direction[args.length];
        int i = 0;
        for(String s : args) {
            switch (s) {
                case "f" -> list[i++] = Direction.FORWARD;
                case "b" -> list[i++] = Direction.BACKWARD;
                case "r" -> list[i++] = Direction.RIGHT;
                case "l" -> list[i++] = Direction.LEFT;
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        LAB1
//        System.out.println("##system rozpoczol dzialanie##");
//        Direction[] list;
//        list = convert_to_enum(args);
//        run(list);
//        System.out.println("##system zakonczyl dzialanie##");

//        LAB2
        Vector2d position1 = new Vector2d(1,2);
        System.out.println((position1));
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection d = MapDirection.NORTH;
        System.out.println(d.toString());
        System.out.println(d.next());
        System.out.println(d.previous());
        System.out.println(d.toUnitVector());
    }
}

