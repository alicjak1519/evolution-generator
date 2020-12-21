package evolutionWorld.world;

import evolutionWorld.classes.MoveDirection;

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
        map.animalsLinkedList.forEach(a->a.move(MoveDirection.FORWARD));
        map.animalsLinkedList.forEach(a->a.reduceEnergy(moveEnergy));
        map.eat(plantEnergy);
        map.plantNewGrasses();
        map.breed(5);
        map.removeDeadAnimals();
    }
}
