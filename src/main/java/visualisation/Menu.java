package visualisation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Menu extends ToolBar {

    private MainView mainView;

    public Menu(MainView mainView) {
        this.mainView = mainView;
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);

        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);

        this.getItems().addAll(start, stop);
    }

    private void handleStart(ActionEvent actionEvent) {
        System.out.println("Start pressed");
        this.mainView.getSimulator().start();
    }

    private void handleStop(ActionEvent actionEvent) {
        System.out.println("Stop pressed");
        this.mainView.getSimulator().stop();
    }
}