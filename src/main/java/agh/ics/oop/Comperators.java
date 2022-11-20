package agh.ics.oop;
import java.util.Comparator;

public class Comperators {
    static Comparator<AbstractWorldElement> comparatorX = new Comparator<AbstractWorldElement>() {
        @Override
        public int compare(AbstractWorldElement o1, AbstractWorldElement o2) {
            if (o1 != null && o2 != null) {
                Vector2d pos1 = o1.getPosition();
                Vector2d pos2 = o2.getPosition();

                if (pos1.x < pos2.x) return -1;
                else if (pos1.x > pos2.x) return 1;
                else {
                    if (pos1.y < pos2.y) return -1;
                    else if (pos1.y > pos2.y) return 1;
                }
            }
            return 0;
        }
    };

    static Comparator<AbstractWorldElement> comparatorY = new Comparator<AbstractWorldElement>() {
        @Override
        public int compare(AbstractWorldElement o1, AbstractWorldElement o2) {
            if (o1 != null && o2 != null) {
                Vector2d pos1 = o1.getPosition();
                Vector2d pos2 = o2.getPosition();

                if (pos1.y < pos2.y) return -1;
                else if (pos1.y > pos2.y) return 1;
                else {
                    if (pos1.x < pos2.x) return -1;
                    else if (pos1.x > pos2.x) return 1;
                }
            }
            return 0;
        }
    };
}
