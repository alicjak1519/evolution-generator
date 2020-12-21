//import evolutionWorld.classes.Animal;
//import evolutionWorld.world.WorldMap;
//import org.junit.Test;
//
//
//public class AnimalTest {
//    WorldMap map = new WorldMap(4, 4, 2,10,10);
//    Animal squirrel = new Animal(map);
//
//    @Test
//    public void animalOrientationTest() {
//        OptionsParser optionsParser = new OptionsParser();
//        String[] actionAsString = {"f", "b", "x", "r"};
//        MoveDirection[] actions = optionsParser.parse(actionAsString);
//
//        for (MoveDirection action : actions) {
//            squirrel.move(action);
//        }
//        assertEquals(squirrel.getMapDirection(), MapDirection.EAST);
//    }
//
//    @Test
//    public void animalPositionTest() {
//        OptionsParser optionsParser = new OptionsParser();
//        String[] actionAsString = {"f", "b", "x", "r"};
//        MoveDirection[] actions = optionsParser.parse(actionAsString);
//
//        for (MoveDirection action : actions) {
//            squirrel.move(action);
//        }
//        assertEquals(squirrel.getPosition(), new Vector2d(2, 2));
//    }
//
//    @Test
//    public void animalAreaBorderTest() {
//        OptionsParser optionsParser = new OptionsParser();
//        String[] actionAsString = {"f", "f", "f", "f", "f", "f"};
//        MoveDirection[] actions = optionsParser.parse(actionAsString);
//
//        for (MoveDirection action : actions) {
//            squirrel.move(action);
//        }
//        assertEquals(squirrel.getPosition(), new Vector2d(2, 4));
//    }
//}
