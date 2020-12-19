package evolutionWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap implements IWorldMap {
    private int mapHeight;
    private int mapWidth;
    private int jungleSize;
    private int maxAnimalEnergy;
    private int grassPercentage;

    protected Map<Vector2d, Animal> animalsMap = new HashMap<>();
    protected Map<Vector2d, RegularGrass> regularGrassesMap = new HashMap<>();
    protected Map<Vector2d, JungleGrass> jungleGrassMap = new HashMap<>();


    public WorldMap(int height, int width, int jungleSize, int maxAnimalEnergy, int grassPercentage) {
        this.mapHeight = height;
        this.mapWidth = width;
        this.jungleSize = jungleSize;
        this.maxAnimalEnergy = maxAnimalEnergy;
        this.grassPercentage = grassPercentage;

        for (int i = 0; i < Math.round(this.grassPercentage * this.mapHeight * this.mapWidth / 100); i++) {
            int randX = ThreadLocalRandom.current().nextInt(0, this.mapWidth + 1);
            int randY = ThreadLocalRandom.current().nextInt(0, this.mapHeight + 1);
            RegularGrass newGrass = new RegularGrass(new Vector2d(randX, randY));
            regularGrassesMap.put(newGrass.getPosition(), newGrass);
        }

        for (int i = 0; i < this.jungleSize; i++) {
            for (int j = 0; j < this.jungleSize; j++) {
                int jungleGrassX = Math.round((this.mapWidth - this.jungleSize) / 2) + i + 1;
                int jungleGrassY = Math.round((this.mapHeight - this.jungleSize) / 2) + j + 1;
                JungleGrass newJungleGrass = new JungleGrass(new Vector2d(jungleGrassX, jungleGrassY));
                jungleGrassMap.put(newJungleGrass.getPosition(), newJungleGrass);
            }
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMaxAnimalEnergy() {
        return maxAnimalEnergy;
    }

    public Map<Vector2d, Animal> getAnimalsMap(){
        return animalsMap;
    }

    public Map<Vector2d, JungleGrass> getJungleGrassMap(){
        return jungleGrassMap;
    }

    public Map<Vector2d, RegularGrass> getRegularGrassesMap(){
        return regularGrassesMap;
    }

    public int getJungleSize() {
        return this.jungleSize;
    }

    public void removeRegularGrass(RegularGrass regularGrass){
        this.regularGrassesMap.remove(regularGrass);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        this.animalsMap.put(animal.getPosition(), animal);
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Map.Entry<Vector2d, Animal> entry : this.animalsMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return true;
            }
        }

        for (Map.Entry<Vector2d, RegularGrass> entry : this.regularGrassesMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return true;
            }
        }

        for (Map.Entry<Vector2d, JungleGrass> entry : this.jungleGrassMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Map.Entry<Vector2d, Animal> entry : this.animalsMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return entry.getValue();
            }
        }
        for (Map.Entry<Vector2d, RegularGrass> entry : this.regularGrassesMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return entry.getValue();
            }
        }
        for (Map.Entry<Vector2d, JungleGrass> entry : this.jungleGrassMap.entrySet()) {
            Vector2d key = entry.getKey();
            if (key.x == position.x && key.y == position.y) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.mapWidth, this.mapHeight));
    }

}
