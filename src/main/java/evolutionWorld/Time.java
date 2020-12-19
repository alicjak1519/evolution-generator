package evolutionWorld;

import java.util.concurrent.TimeUnit;

public class Time {
    private WorldMap map;
    private int dayDuration;

    public Time(WorldMap map, int dayDuration) {
        this.map = map;
        this.dayDuration = dayDuration;
    }

    public void runDay() throws InterruptedException {
        for (Animal animal : this.map.animalsMap.values()
        ) {
            animal.move();
            animal.reduceEnergy(1);
            System.out.println(animal.getEnergy());
        }

//        for (Map.Entry<evolutionWorld.Vector2d, evolutionWorld.Animal> entry : this.map.animalsMap.entrySet()) {
//            evolutionWorld.Animal animal = entry.getValue();
//            animal.move();
//            animal.reduceEnergy(1);
//            entry.setValue(animal);
//        }

        TimeUnit.SECONDS.sleep(dayDuration);
    }
}
