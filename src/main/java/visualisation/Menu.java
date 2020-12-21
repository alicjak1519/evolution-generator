package visualisation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Menu extends ToolBar {

    private MainView mainView;

    public Menu(MainView mainView) {
        this.mainView = mainView;
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);

        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);

        Button save = new Button("Save");
        save.setOnAction(actionEvent -> {
            try {
                handleSave(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.getItems().addAll(start, stop, save);
    }

    private void handleStart(ActionEvent actionEvent) {
        System.out.println("Start pressed");
        this.mainView.getSimulator().start();
    }

    private void handleStop(ActionEvent actionEvent) {
        System.out.println("Stop pressed");
        this.mainView.getSimulator().stop();
    }

    private void handleSave(ActionEvent actionEvent) throws IOException {
        String data = this.mainView.getStatistics().getText();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("evolution_generator_stats_%s", timestamp.toInstant())));
        writer.write(data);
        writer.close();

    }
}