package evolutionWorld.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public int getDirectionId() {
        return Arrays.asList(MapDirection.values()).indexOf(this);
    }

    public static MapDirection getDirectionById(int id) {
        return Arrays.asList(MapDirection.values()).get(id);
    }

    private static final List<MapDirection> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MapDirection randomMapDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public MapDirection next() {
        int nextIndex = (this.getDirectionId() + 1) % MapDirection.values().length;
        return MapDirection.values()[nextIndex];
    }

    public MapDirection previous() {
        int nextIndex = (this.getDirectionId() - 1) % MapDirection.values().length;
        return MapDirection.values()[nextIndex];
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case EAST:
                return new Vector2d(1, 0);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            case SOUTH:
                return new Vector2d(0, -1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            default:
                throw new RuntimeException("This direction doesn't exist!");
        }
    }
}

