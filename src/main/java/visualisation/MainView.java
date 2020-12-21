package visualisation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import evolutionWorld.classes.Animal;
import evolutionWorld.classes.Vector2d;
import evolutionWorld.world.Time;
import evolutionWorld.world.WorldMap;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainView extends VBox {

    private GridPane gridPane;
    private Menu menu;
    private Statistics statistics;
    private AnimalDetails animalDetails;

    private WorldMap worldMap;
    private Time worldTime;
    private Simulator simulator;
//    private int epochNumber = 0;

    public MainView() throws FileNotFoundException {
        WorldParameters parameters = getWorldParameters();
        worldMap = createWorldMap(parameters);
        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        menu = new Menu(this);
        statistics = new Statistics(this, worldMap);
        animalDetails = new AnimalDetails(this, worldMap);
        this.getChildren().addAll(gridPane, menu, statistics, animalDetails);

        worldTime = new Time(worldMap, parameters.getMoveEnergy(), parameters.getPlantEnergy());
        simulator = new Simulator(this, worldTime);
    }

    private WorldMap createWorldMap(WorldParameters parameters) {
        WorldMap worldMap = new WorldMap(parameters.getHeight(), parameters.getWidth(), parameters.getJungleRatio(), parameters.getStartEnergy());

        for (int i = 0; i < Math.max(parameters.getHeight(), parameters.getWidth()) * 10; i++) {
            Animal newAnimal = new Animal(worldMap);
            worldMap.place(newAnimal);
        }
        return worldMap;
    }

    private WorldParameters getWorldParameters() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        return gson.fromJson(reader, WorldParameters.class);
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void draw() {
        worldMap.incrementEpochNumber();
        drawActualMap(worldMap);
        statistics.update();
    }

    public void drawActualMap(WorldMap worldMap) {
        for (int i = 0; i < worldMap.getMapWidth(); i++) {
            for (int j = 0; j < worldMap.getMapHeight(); j++) {
                if (worldMap.getAnimalsMap().containsKey(new Vector2d(i, j))) {
                    Animal animal = worldMap.getAnimalsMap().get(new Vector2d(i, j)).getFirst();
                    Rectangle animalRect = new javafx.scene.shape.Rectangle(10, 10, javafx.scene.paint.Paint.valueOf(colorAnimalEnergy(animal.getEnergy())));
                    gridPane.add(animalRect, i, j);
                    animalRect.setOnMouseClicked(e -> animalDetails.update(animal));
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
            return "370617";
        } else if (energy >= 0.5 * maxEnergy) {
            return "d00000";
        } else if (energy >= 0.3 * maxEnergy) {
            return "f48c06";
        } else {
            return "ffba08";
        }
    }
}
