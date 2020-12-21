package evolutionWorld;

public class Time {
    private WorldMap map;
    private int moveEnergy;
    private int plantEnergy;

    public Time(WorldMap map, int moveEnergy, int plantEnergy) {
        this.map = map;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
    }

    public void runDay() {
        for (Animal animal : map.animalsLinkedList
        ) {
            animal.move(MoveDirection.FORWARD);
            animal.reduceEnergy(moveEnergy);
            System.out.println(animal.getEnergy());
        }
        map.eat(plantEnergy);
        map.plantNewGrasses();
        map.breed(5);
        map.removeDeadAnimals();
    }
}
