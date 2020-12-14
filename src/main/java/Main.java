import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            WorldMap map = new WorldMap(10, 10, 5, 10);

            List<MoveDirection> genes = new ArrayList<>();
            map.place(new Animal(map, {0, 0, 1}));
            map.place(new Animal(map, new Vector2d(0, 9)));


            System.out.println(map);
            map.run(directions);
            System.out.println(map);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
