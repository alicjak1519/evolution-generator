package visualisation;

import evolutionWorld.Animal;
import evolutionWorld.MoveDirection;
import evolutionWorld.Vector2d;
import evolutionWorld.WorldMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        WorldMap worldMap = new WorldMap(20, 20, 10, 10, 10);
        ArrayList<MoveDirection> squirrelGenes = new ArrayList<MoveDirection>();
        squirrelGenes.add(MoveDirection.NORTH);
        squirrelGenes.add(MoveDirection.NORTH);
        squirrelGenes.add(MoveDirection.NORTHEAST);
        Animal squirrel = new Animal(worldMap, squirrelGenes);
        worldMap.place(squirrel);

        int windowScale = 40;

        Scene scene = new Scene(root, worldMap.getMapWidth() * windowScale, worldMap.getMapHeight() * windowScale);
        final Canvas canvas = new Canvas(worldMap.getMapWidth() * windowScale, worldMap.getMapHeight() * windowScale);

        Button reset = new Button("Reset");
        Button step = new Button("Step");
        Button run = new Button("Run");
        Button stop = new Button("Stop");

        GridPane mainGrid = new GridPane();
        mainGrid.setHgap(5);
        mainGrid.setVgap(5);

        Pane mainBoard = (Pane) scene.lookup("#mainBoard");
        mainBoard.getChildren().add(mainGrid);

        for (int i = 0; i < worldMap.getMapWidth(); i++) {
            for (int j = 0; j < worldMap.getMapHeight(); j++) {
                if (worldMap.getAnimalsMap().containsKey(new Vector2d(i, j))){
                    mainGrid.add(new Rectangle(10, 10, Paint.valueOf("red")), i, j);
                }
                else if (worldMap.getRegularGrassesMap().containsKey(new Vector2d(i, j))) {
                    mainGrid.add(new Rectangle(10, 10, Paint.valueOf("green")), i, j);
                }
                else if (worldMap.getJungleGrassMap().containsKey(new Vector2d(i, j))) {
                    mainGrid.add(new Rectangle(10, 10, Paint.valueOf("blue")), i, j);
                }
                else {
                    mainGrid.add(new Rectangle(10, 10, Paint.valueOf("yellow")), i, j);
                }

//                mainGrid.add(new Rectangle(mainGrid.getHeight() / worldMap.getMapHeight(), mainGrid.getWidth() / worldMap.getMapWidth(), Paint.valueOf("red")), i, j);
            }
        }

//        root.getChildren().addAll(canvas, new HBox(10, reset, step, run, stop));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
