import java.util.Iterator;
import java.util.Map;
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

//        for (Map.Entry<Vector2d, Animal> entry : this.map.animalsMap.entrySet()) {
//            Animal animal = entry.getValue();
//            animal.move();
//            animal.reduceEnergy(1);
//            entry.setValue(animal);
//        }

        TimeUnit.SECONDS.sleep(dayDuration);
    }
}
