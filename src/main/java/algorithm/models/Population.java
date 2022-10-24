package algorithm.models;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Population {

    public List<Individual> individuals;
    public int fittest = 0;

    //Initialize population
    public void initializePopulation(int size, int individualRandomSeed, boolean empty) {
        individuals= new ArrayList<>();
        if(!empty){
            Faker faker = new Faker();
            for (int i = 0; i < size; i++) {
                individuals.add(new Individual(faker.name().fullName(),individualRandomSeed));
            }
        }

    }

    //Get the fittest individual
    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.size(); i++) {
            if (maxFit <= individuals.get(i).fitness) {
                maxFit = individuals.get(i).fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals.get(maxFitIndex).fitness;
        return individuals.get(maxFitIndex);
    }

    //Get the second-fittest individual
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.size(); i++) {
            if (individuals.get(i).fitness > individuals.get(maxFit1).fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals.get(i).fitness > individuals.get(maxFit2).fitness) {
                maxFit2 = i;
            }
        }
        return individuals.get(maxFit2);
    }

    //Get index of the least fit individual
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.size(); i++) {
            if (minFitVal >= individuals.get(i).fitness) {
                minFitVal = individuals.get(i).fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < individuals.size(); i++) {
            individuals.get(i).calcFitness();
        }
        getFittest();
    }
}
