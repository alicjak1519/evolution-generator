public class WorldMap implements IWorldMap {
    private int mapHeight;
    private int mapWidth;
    private int jungleSize;
    private int maxAnimalEnergy;

    public WorldMap(int height, int width, int jungleSize, int maxAnimalEnergy) {
        this.mapHeight = height;
        this.mapWidth = width;
        this.jungleSize = jungleSize;
        this.maxAnimalEnergy = maxAnimalEnergy;
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public int getMapHeight(){
        return mapHeight;
    }

    public int getMaxAnimalEnergy(){
        return maxAnimalEnergy;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
