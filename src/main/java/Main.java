import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            WorldMap map = new WorldMap(10, 10, 5, 10, 20);
            Time worldTime = new Time(map, 5);

            ArrayList<MoveDirection> squirrelGenes = new ArrayList<MoveDirection>();
            squirrelGenes.add(MoveDirection.NORTH);
            squirrelGenes.add(MoveDirection.NORTH);
            squirrelGenes.add(MoveDirection.NORTHEAST);
            Animal squirrel = new Animal(map, squirrelGenes);
            map.place(squirrel);

            ArrayList<MoveDirection> foxGenes = new ArrayList<MoveDirection>();
            foxGenes.add(MoveDirection.SOUTH);
            foxGenes.add(MoveDirection.SOUTH);
            foxGenes.add(MoveDirection.SOUTHWEST);
            Animal fox = new Animal(map, foxGenes);
            map.place(fox);

            System.out.println(map);
            worldTime.runDay();
            System.out.println(map);

        } catch (IllegalArgumentException | InterruptedException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}