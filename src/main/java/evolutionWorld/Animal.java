package evolutionWorld;

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

    public Animal(WorldMap map, ArrayList<MoveDirection> genes) {
        this.map = map;
        this.genes = genes;
        this.moveDirection = genes.get((new Random()).nextInt(this.genes.size()));
        int randX = ThreadLocalRandom.current().nextInt(0, this.map.getMapWidth() + 1);
        int randY = ThreadLocalRandom.current().nextInt(0, this.map.getMapHeight() + 1);
        this.position = new Vector2d(randX, randY);
        this.energy = ThreadLocalRandom.current().nextInt(0, this.map.getMaxAnimalEnergy() + 1);
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public void eatJungleGrass() {
        this.energy++;
    }

    public void eatRegularGrass(RegularGrass regularGrass) {
        this.map.removeRegularGrass(regularGrass);
        this.energy++;
    }

    public void reduceEnergy(int reducedEnergy){
        this.energy = this.energy - reducedEnergy;
    }

    public void move() {
        Vector2d oldPosition = this.position;
        switch (this.moveDirection) {
            case NORTH:
                this.position = position.add(new Vector2d(0, 1));
                break;
            case NORTHEAST:
                this.position = position.add(new Vector2d(1, 1));
            case EAST:
                this.position = position.add(new Vector2d(1, 0));
                break;
            case SOUTHEAST:
                this.position = position.add(new Vector2d(1, -1));
            case SOUTH:
                this.position = position.add(new Vector2d(0, -1));
                break;
            case SOUTHWEST:
                this.position = position.add(new Vector2d(-1, -1));
            case WEST:
                this.position = position.add(new Vector2d(-1, 0));
                break;
            case NORTHWEST:
                this.position = position.add(new Vector2d(-1, 1));
                break;
        }

        this.position = new Vector2d(this.position.x % this.map.getMapWidth(), this.position.y % this.map.getMapWidth());

        if (this.map.regularGrassesMap.containsKey(this.position)){
            this.eatRegularGrass(this.map.regularGrassesMap.get(this.position));
        }

        if (this.map.jungleGrassMap.containsKey(this.position)){
            this.eatJungleGrass();
        }

        this.moveDirection = genes.get((new Random()).nextInt(this.genes.size()));

        this.map.animalsMap.remove(oldPosition);
        this.map.animalsMap.put(this.position, this);
    }

    public String toString() {
        return "o";
    }
}
