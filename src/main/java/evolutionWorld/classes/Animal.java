package evolutionWorld.classes;

import evolutionWorld.interfaces.IPositionChangeObserver;
import evolutionWorld.world.WorldMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IPositionChangeObserver {
    private WorldMap map;
    private Genotype genotype;
    private MapDirection mapDirection;
    public ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    private Integer energy;
    private Integer childrenNumber = 0;
    private LinkedList<Animal> childrenList = new LinkedList<>();

    public Animal(WorldMap map) {
        this.map = map;
        this.genotype = new Genotype();
        this.mapDirection = MapDirection.getDirectionById(genotype.getRandomGen());
        int randX = ThreadLocalRandom.current().nextInt(0, this.map.getMapWidth() + 1);
        int randY = ThreadLocalRandom.current().nextInt(0, this.map.getMapHeight() + 1);
        this.position = new Vector2d(randX, randY);
        this.energy = ThreadLocalRandom.current().nextInt(0, this.map.getStartAnimalEnergy() + 1);
    }

    public Animal(WorldMap map, Vector2d position, int energy) {
        this.map = map;
        this.genotype = new Genotype();
        this.mapDirection = MapDirection.getDirectionById(genotype.getRandomGen());
        this.position = position;
        this.energy = energy;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void reduceEnergy(int reducedEnergy) {
        this.energy = this.energy - reducedEnergy;
    }

    public boolean isDead() {
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
        mapDirection = MapDirection.getDirectionById(genotype.getRandomGen());
    }

    public void incrementChildrenNumber() {
        childrenNumber++;
    }

    public void addChild(Animal child) {
        childrenList.add(child);
    }

    public int getDescendantsNumber() {
        if (childrenList.isEmpty()) {
            return 0;
        } else {
            return childrenList.size() + childrenList
                    .stream()
                    .map(Animal::getDescendantsNumber)
                    .reduce(0, Integer::sum);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
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
                Objects.equals(genotype, animal.genotype) &&
                mapDirection == animal.mapDirection &&
                Objects.equals(observers, animal.observers) &&
                Objects.equals(position, animal.position) &&
                Objects.equals(energy, animal.energy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, genotype, mapDirection, observers, position, energy);
    }

    public String toString() {
        return "o";
    }

}
