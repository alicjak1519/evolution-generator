package visualisation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        MainView mainView1 = new MainView();
        MainView mainView2 = new MainView();
        HBox parent = new HBox();
        parent.getChildren().addAll(mainView1, mainView2);
        Scene scene = new Scene(parent, 800, 600);
        stage.setScene(scene);
        stage.show();

        mainView1.draw();
        mainView2.draw();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
