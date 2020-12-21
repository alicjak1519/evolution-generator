package visualisation;

import evolutionWorld.world.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;


public class Simulator {

    private Timeline timeline;
    private MainView mainView;
    private Time time;

    public Simulator(MainView mainView, Time time) {
        this.mainView = mainView;
        this.time = time;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void doStep(ActionEvent actionEvent) {
        this.time.runDay();
        this.mainView.draw();
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

}
