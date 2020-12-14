import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private WorldMap map;
    private List<MoveDirection> genes;
    private MoveDirection moveDirection;
    private Vector2d position;
    private Integer energy;

    public Animal(WorldMap map, List<MoveDirection> genes){
        this.map = map;
        this.genes = genes;
        this.moveDirection = genes.get((new Random()).nextInt(this.genes.size()));

        int randX = ThreadLocalRandom.current().nextInt(0, this.map.getMapWidth() + 1);
        int randY = ThreadLocalRandom.current().nextInt(0, this.map.getMapHeight() + 1);
        this.position = new Vector2d(randX, randY);

        this.energy = ThreadLocalRandom.current().nextInt(0, this.map.getMaxAnimalEnergy() + 1);
    }
}
