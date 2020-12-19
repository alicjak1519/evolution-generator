package evolutionWorld;

import java.util.Arrays;

public enum MoveDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public int getDirectionId() {
        return Arrays.asList(MoveDirection.values()).indexOf(this);
    }

    public MoveDirection next() {
        int nextIndex = (this.getDirectionId() + 1) % MoveDirection.values().length;
        return MoveDirection.values()[nextIndex];
    }

    public MoveDirection previous() {
        int nextIndex = (this.getDirectionId() - 1) % MoveDirection.values().length;
        return MoveDirection.values()[nextIndex];
    }
}

