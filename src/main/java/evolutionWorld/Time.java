package evolutionWorld;

public class Time {
    private WorldMap map;
    private int dayDuration;

    public Time(WorldMap map, int dayDuration) {
        this.map = map;
        this.dayDuration = dayDuration;
    }

    public void runDay() {
        map.removeDeadAnimals();
        for (Animal animal : map.animalsLinkedList
        ) {
            animal.move(MoveDirection.FORWARD);
            animal.reduceEnergy(1);
            System.out.println(animal.getEnergy());
        }
        map.eat();
        map.plantNewGrasses();
        map.breed(5);
    }
}
