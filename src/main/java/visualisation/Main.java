package visualisation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 500, 500);
        stage.setScene(scene);
        stage.show();

        mainView.draw();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
