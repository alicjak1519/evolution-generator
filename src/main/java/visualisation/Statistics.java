package visualisation;

import evolutionWorld.classes.Animal;
import evolutionWorld.classes.Genotype;
import evolutionWorld.world.WorldMap;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Statistics extends Text {
    private MainView mainView;
    private WorldMap worldMap;

    private int epochNumber;
    private int aliveAnimalsNumber;
    private int grassesNumber;
    private Genotype dominatingGenotype;
    private double avgEnergy;
    private double avgLifeLength;
    private double avgChildrenNumber;


    public Statistics(MainView mainView, WorldMap worldMap) {
        this.mainView = mainView;
        this.worldMap = worldMap;
    }

    public void update() {
        epochNumber = worldMap.getEpochNumber();
        aliveAnimalsNumber = worldMap.getAliveAnimalsNumber();
        grassesNumber = worldMap.getGrassesMap().size();
        dominatingGenotype = getDominatingGenotype();
        avgEnergy = calcAvgEnergy();
        avgLifeLength = worldMap.getEpochOfAnimalsDeath().stream().mapToDouble(e->e).average().orElse(0.0);
        avgChildrenNumber = calcAvgChildrenNumber();

        this.setText(String.format(
                "Epoch number: %d\n" +
                        "Alive animals number: %d\n" +
                        "Grasses number: %d\n" +
                        "Dominating genotype: \n%s\n" +
                        "Average energy: %.2f\n" +
                        "Average life length: %.2f\n" +
                        "Average children number: %.2f",
                epochNumber,
                aliveAnimalsNumber,
                grassesNumber,
                dominatingGenotype,
                avgEnergy,
                avgLifeLength,
                avgChildrenNumber));
    }

    public double calcAvgEnergy() {
        return worldMap.animalsLinkedList
                .stream()
                .map(Animal::getEnergy)
                .mapToDouble(e -> e)
                .average().orElse(0.0);
    }

    public double calcAvgChildrenNumber() {
        return worldMap.animalsLinkedList
                .stream()
                .map(Animal::getChildrenNumber)
                .mapToDouble(e -> e)
                .average().orElse(0.0);
    }

    public Genotype getDominatingGenotype() {
        List<Genotype> genotypesList = worldMap.animalsLinkedList.stream().map(Animal::getGenotype).collect(Collectors.toList());
        return mostCommon(genotypesList);
    }

    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        if (max == null) {
            return null;
        }
        return max.getKey();
    }

}
