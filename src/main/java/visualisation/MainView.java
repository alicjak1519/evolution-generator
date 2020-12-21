package visualisation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import evolutionWorld.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainView extends VBox {

    private GridPane gridPane;
    private WorldMap worldMap;
    private Time worldTime;
    private Simulator simulator;

    public MainView() throws FileNotFoundException {
        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Menu menu = new Menu(this);
        this.getChildren().addAll(gridPane, menu);

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        WorldParameters parameters = gson.fromJson(reader, WorldParameters.class);

        worldMap = new WorldMap(parameters.getHeight(), parameters.getWidth(), parameters.getJungleRatio(), parameters.getStartEnergy());

        for (int i = 0; i < 50; i++) {
            Animal newAnimal = new Animal(worldMap);
            worldMap.place(newAnimal);
        }

        worldTime = new Time(worldMap, parameters.getMoveEnergy(), parameters.getPlantEnergy());
        simulator = new Simulator(this, worldTime);
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public void draw() {
        drawActualMap(worldMap);
    }

    public void drawActualMap(WorldMap worldMap) {
        for (int i = 0; i < worldMap.getMapWidth(); i++) {
            for (int j = 0; j < worldMap.getMapHeight(); j++) {
                if (worldMap.getAnimalsMap().containsKey(new Vector2d(i, j))) {
                    int animalEnergy = worldMap.getAnimalsMap().get(new Vector2d(i, j)).getFirst().getEnergy();
                    gridPane.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Paint.valueOf(colorAnimalEnergy(animalEnergy))), i, j);
                } else if (worldMap.getGrassesMap().containsKey(new Vector2d(i, j))) {
                    gridPane.add(new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Paint.valueOf("658d1b")), i, j);
                } else {
                    gridPane.add(new Rectangle(10, 10, Paint.valueOf("f2e9e4")), i, j);
                }
            }
        }
    }

    public String colorAnimalEnergy(int energy) {
        int maxEnergy = worldMap.getStartAnimalEnergy();
        if (energy >= 0.8 * maxEnergy) {
            return "ff4800";
        } else if (energy >= 0.5 * maxEnergy) {
            return "ff6d00";
        } else if (energy >= 0.3 * maxEnergy) {
            return "ff9e00";
        } else {
            return "ffb133";
        }
    }
}
