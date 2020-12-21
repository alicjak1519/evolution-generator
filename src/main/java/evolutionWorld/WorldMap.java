package evolutionWorld;

import evolutionWorld.interfaces.IPositionChangeObserver;
import evolutionWorld.interfaces.IWorldMap;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver {
    private int mapHeight;
    private int mapWidth;
    private int jungleSize;
    private int jungleRatio;
    private int startAnimalEnergy;
    private int grassPercentage;

    protected Map<Vector2d, LinkedList<Animal>> animalsMap = new HashMap<>();
    protected Map<Vector2d, Grass> grassesMap = new HashMap<>();
    public LinkedList<Animal> animalsLinkedList = new LinkedList<>();

    public WorldMap(int height, int width, int jungleRatio, int startAnimalEnergy) {
        this.mapHeight = height;
        this.mapWidth = width;
        this.jungleSize = Math.min(height, width) / 3;
        this.jungleRatio = jungleRatio;
        this.startAnimalEnergy = startAnimalEnergy;
        this.grassPercentage = 20;

        for (int i = 0; i < Math.round(this.grassPercentage * this.mapHeight * this.mapWidth / 100); i++) {
            for (int j = 0; j < this.jungleRatio; j++) {
                Grass newJungleGrass = new Grass(getRandomJunglePosition());
                grassesMap.put(newJungleGrass.getPosition(), newJungleGrass);
            }
            Grass newNonJungleGrass = new Grass(getRandomNonJunglePosition());
            grassesMap.put(newNonJungleGrass.getPosition(), newNonJungleGrass);
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getStartAnimalEnergy() {
        return startAnimalEnergy;
    }

    public int getAliveAnimalsNumber() {
        return animalsLinkedList.size();
    }

    public Map<Vector2d, LinkedList<Animal>> getAnimalsMap() {
        return animalsMap;
    }

    public Map<Vector2d, Grass> getGrassesMap() {
        return grassesMap;
    }

    public Vector2d getRandomJunglePosition() {
        Random random = new Random();
        int randX = Math.round(random.nextInt(jungleSize) + (mapWidth - jungleSize) / 2f);
        int randY = Math.round(random.nextInt(jungleSize) + (mapHeight - jungleSize) / 2f);
        return new Vector2d(randX, randY);
    }

    public Vector2d getRandomNonJunglePosition() {
        Random random = new Random();
        int randX = mapWidth / 2;
        int randY = mapHeight / 2;
        while (randX > ((mapWidth - jungleSize) / 2f) &&
                randX < (mapWidth + jungleSize) / 2f &&
                randY > ((mapHeight - jungleSize) / 2f) &&
                randY < (mapHeight + jungleSize) / 2f) {
            randX = Math.round(random.nextInt(mapWidth));
            randY = Math.round(random.nextInt(mapHeight));
        }

        return new Vector2d(randX, randY);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        addAnimal(animal);
        animalsLinkedList.add(animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for (int i = 0; i < directions.length; i++) {
            animalsLinkedList.get(i % animalsLinkedList.size()).move(directions[i]);
        }
    }

    public boolean place(Grass grass) {
        grassesMap.put(grass.getPosition(), grass);
        return true;
    }

    public void plantNewGrasses() {
        Grass jungleGrass = new Grass(getRandomJunglePosition());
        place(jungleGrass);
        Grass nonJungleGrass = new Grass(getRandomNonJunglePosition());
        place(nonJungleGrass);
    }

    public void eat(int plantEnergy) {
        LinkedList<Grass> grassesToEat = new LinkedList<>();

        for (Grass grass : grassesMap.values()) {
            LinkedList<Animal> animalsReadyToEatGrass = animalsMap.get(grass.getPosition());
            if (animalsReadyToEatGrass != null) {
                if (animalsReadyToEatGrass.size() > 0) {
                    Animal theStrongestAnimal = Collections.max(animalsReadyToEatGrass, Comparator.comparing(Animal::getEnergy));
                    animalsReadyToEatGrass.removeIf(animal -> animal.getEnergy() < theStrongestAnimal.getEnergy());
                    for (Animal animal : animalsReadyToEatGrass) {
                        animal.reduceEnergy(-plantEnergy / animalsReadyToEatGrass.size());
                        grassesToEat.add(grass);
                    }
                }
            }
        }
        for (Grass grass : grassesToEat) {
            grassesMap.remove(grass.getPosition());
        }
    }

    public void breed(int energyThreshold) {
        LinkedList<Animal> children = new LinkedList<>();
        for (Vector2d position : animalsMap.keySet()
        ) {
            LinkedList<Animal> parents = animalsMap.get(position);
            if (parents.size() > 1) {
                parents.sort((o1, o2) -> o2.getEnergy() - o1.getEnergy());
                Animal mum = parents.get(0);
                Animal dad = parents.get(1);
                if (dad.getEnergy() >= energyThreshold) {
                    Vector2d childPosition = findFreeAdjacentPosition(dad.getPosition());
                    createChild(children, mum, dad, childPosition);
                }
            }
        }
        children.forEach(this::addAnimal);
    }

    private void createChild(LinkedList<Animal> children, Animal mum, Animal dad, Vector2d childPosition) {
        if (childPosition != null) {
            Animal child = new Animal(this, childPosition, mum.getEnergy() / 4 + dad.getEnergy() / 4);
            mum.reduceEnergy(mum.getEnergy() / 4);
            dad.reduceEnergy(dad.getEnergy() / 4);
            children.add(child);
            mum.incrementChildrenNumber();
            dad.incrementChildrenNumber();
        }
    }

    public Vector2d findFreeAdjacentPosition(Vector2d parentPosition) {
        for (int i = parentPosition.x - 1; i < parentPosition.x + 1; i++) {
            for (int j = parentPosition.y - 1; j < parentPosition.y + 1; j++) {
                if (i != parentPosition.x || j != parentPosition.y) {
                    if (!isOccupied(new Vector2d(i, j))) {
                        return new Vector2d(i, j);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        LinkedList<Animal> animalsList = animalsMap.get(position);
        if (animalsList == null) {
            return grassesMap.get(position);
        } else if (animalsList.isEmpty()) {
            return grassesMap.get(position);
        } else {
            return animalsList.getFirst();
        }
    }

    private void addAnimal(Animal animal) {
        LinkedList<Animal> animalsList = animalsMap.get(animal.getPosition());
        if (animalsList == null) {
            LinkedList<Animal> newAnimalsList = new LinkedList<>();
            newAnimalsList.add(animal);
            animalsMap.put(animal.getPosition(), newAnimalsList);
        } else {
            animalsList.add(animal);
        }
    }

    private void removeAnimal(Animal animal, Vector2d position) {
        LinkedList<Animal> animalsList = animalsMap.get(position);
        if (animalsList == null) {
            throw new IllegalArgumentException();
        } else {
            animalsList.remove(animal);
            if (animalsList.size() == 0) {
                animalsMap.remove(position);
            }
        }
    }

    public void removeDeadAnimals() {
        Map<Vector2d, LinkedList<Animal>> newAnimalsMap = new HashMap<>();
        LinkedList<Animal> newAnimalsLinkedList = new LinkedList<>();

        for (Animal animal : animalsLinkedList) {
            Vector2d position = animal.getPosition();
            if (!animal.isDead()) {
                LinkedList<Animal> animalsList = newAnimalsMap.get(position);
                if (animalsList == null) {
                    LinkedList<Animal> newAnimalsList = new LinkedList<>();
                    newAnimalsList.add(animal);
                    newAnimalsMap.put(position, newAnimalsList);
                } else if (animalsList != null) {
                    animalsList.add(animal);
                }
                newAnimalsLinkedList.add(animal);
            }
        }
        animalsMap = newAnimalsMap;
        animalsLinkedList = newAnimalsLinkedList;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object object) {
        if (canMoveTo(newPosition)) {
            removeAnimal((Animal) object, oldPosition);
            addAnimal((Animal) object);
        }
    }
}
