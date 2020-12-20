package visualisation;

import evolutionWorld.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainView extends VBox {

    private GridPane gridPane;
    private WorldMap worldMap;
    private Time worldTime;
    private Simulator simulator;

    public MainView() {
        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Menu menu = new Menu(this);
        this.getChildren().addAll(gridPane, menu);

        worldMap = new WorldMap(20, 20, 10, 100, 10);

        for (int i = 0; i < 50; i++) {
            Animal newAnimal = new Animal(worldMap);
            worldMap.place(newAnimal);
        }

        worldTime = new Time(worldMap, 1);
        simulator = new Simulator(this, worldTime);
    }

    public Simulator getSimulator(){
        return simulator;
    }

    public void draw() {
        drawActualMap(worldMap);
        System.out.println("Printed!");
    }

    public void drawActualMap(WorldMap worldMap){
        for (int i = 0; i < worldMap.getMapWidth(); i++) {
            for (int j = 0; j < worldMap.getMapHeight(); j++) {
                if (worldMap.getAnimalsMap().containsKey(new Vector2d(i, j))) {
                    gridPane.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Paint.valueOf("red")), i, j);
                } else if (worldMap.getGrassesMap().containsKey(new Vector2d(i, j))) {
                    gridPane.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Paint.valueOf("green")), i, j);
                } else {
                    gridPane.add(new Rectangle(10, 10, Paint.valueOf("yellow")), i, j);
                }
            }
        }
    }
}
