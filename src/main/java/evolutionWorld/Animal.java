package evolutionWorld;

import evolutionWorld.interfaces.IPositionChangeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IPositionChangeObserver {
    private WorldMap map;
    private List<MapDirection> genes;
    private MapDirection mapDirection;
    public ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    private Integer energy;

    public Animal(WorldMap map) {
        this.map = map;
        this.genes = generateGenes();
        this.mapDirection = genes.get((new Random()).nextInt(this.genes.size()));
        int randX = ThreadLocalRandom.current().nextInt(0, this.map.getMapWidth() + 1);
        int randY = ThreadLocalRandom.current().nextInt(0, this.map.getMapHeight() + 1);
        this.position = new Vector2d(randX, randY);
        this.energy = ThreadLocalRandom.current().nextInt(0, this.map.getMaxAnimalEnergy() + 1);
    }

    public Animal(WorldMap map, Vector2d position, int energy){
        this.map = map;
        this.genes = generateGenes();;
        this.mapDirection = genes.get((new Random()).nextInt(this.genes.size()));
        this.position = position;
        this.energy = energy;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public void reduceEnergy(int reducedEnergy) {
        this.energy = this.energy - reducedEnergy;
    }

    public boolean isDead(){
        return energy <= 0;
    }

    public void move(MoveDirection moveDirection) {
        switch (moveDirection) {
            case RIGHT:
                mapDirection = mapDirection.next();
                break;
            case LEFT:
                mapDirection = mapDirection.previous();
                break;
            case FORWARD:
                if (map.canMoveTo(position.add(mapDirection.toUnitVector()))) {
                    Vector2d old = position;
                    position = position.add(mapDirection.toUnitVector());
                    position = new Vector2d(position.x % map.getMapWidth(), position.y % map.getMapWidth());
                    positionChanged(old, position, this);
                }
                break;
            case BACKWARD:
                if (map.canMoveTo(position.subtract(mapDirection.toUnitVector()))) {
                    Vector2d old = position;
                    position = position.subtract(mapDirection.toUnitVector());
                    position = new Vector2d(position.x % map.getMapWidth(), position.y % map.getMapWidth());
                    positionChanged(old, position, this);
                }
                break;
        }
        mapDirection = genes.get((new Random()).nextInt(genes.size()));
    }

    private ArrayList<MapDirection> generateGenes(){
        ArrayList<MapDirection> animalGenes = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            animalGenes.add(MapDirection.randomMapDirection());
        }
        return animalGenes;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object object) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition, object);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(map, animal.map) &&
                Objects.equals(genes, animal.genes) &&
                mapDirection == animal.mapDirection &&
                Objects.equals(observers, animal.observers) &&
                Objects.equals(position, animal.position) &&
                Objects.equals(energy, animal.energy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, genes, mapDirection, observers, position, energy);
    }

    public String toString() {
        return "o";
    }

}
