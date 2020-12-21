package evolutionWorld;

import java.util.*;

public class Genotype {
    private List<Integer> genes;

    public Genotype() {
        this.genes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            this.genes.add(random.nextInt(8));
        }
        Collections.sort(this.genes);
    }

    public int getRandomGen(){
        return genes.get((new Random()).nextInt(genes.size()));
    }

    @Override
    public String toString() {
        return genes.toString();
    }
}
