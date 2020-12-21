package evolutionWorld;

public class MainWorld {
    public static void main(String[] args) {
        try {
            WorldMap map = new WorldMap(10, 10, 5, 10);
            Time worldTime = new Time(map, 1, 1);

            Animal squirrel = new Animal(map);
            map.place(squirrel);
            Animal fox = new Animal(map);
            map.place(fox);

            System.out.println(map);
            worldTime.runDay();
            System.out.println(map);
            worldTime.runDay();
            System.out.println(map);
            worldTime.runDay();
            System.out.println(map);
            worldTime.runDay();
            System.out.println(map);

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}