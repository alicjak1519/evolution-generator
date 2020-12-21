package visualisation;

import evolutionWorld.classes.Animal;
import evolutionWorld.world.WorldMap;
import javafx.scene.text.Text;


public class AnimalDetails extends Text {

    private MainView mainView;
    private WorldMap worldMap;

    public AnimalDetails(MainView mainView, WorldMap worldMap) {
        this.mainView = mainView;
        this.worldMap = worldMap;
    }

    public void update(Animal animal) {
        this.setText(String.format(
                "------------------\n" +
                        "Selected animal's genotype: \n%s\n" +
                        "Selected animal's children number: %d\n" +
                        "Selected animal's descendants number: %d\n" +
                        animal.getGenotype(),
                animal.getChildrenNumber(),
                animal.getDescendantsNumber()));
    }

}
