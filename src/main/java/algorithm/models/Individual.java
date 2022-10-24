package algorithm.models;

import java.util.Random;

public class Individual implements IIndividual {

    public int fitness;
    public int[] genes = new int[5];
    public int age;
    public String name;

    public Individual(String name,int seed ) {
        Random rn = new Random(seed);
        this.name=name;
        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        age=0;
        fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        fitness = 0;
        for (int i = 0; i < 5; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }
}
