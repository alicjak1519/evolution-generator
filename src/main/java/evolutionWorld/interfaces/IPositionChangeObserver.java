package evolutionWorld.interfaces;

import evolutionWorld.classes.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object object);
}
