package algorithm.services;

import algorithm.models.Individual;
import algorithm.models.Population;

import java.util.Random;

public class SelectionService implements ISelectionService {


    private int populationSeed;
    private Random rg;
    private Boolean debug;

    public SelectionService(Boolean debug, Random rg, int populationSeed) {
        this.debug = debug;
        this.rg = rg;
        this.populationSeed = populationSeed;
    }

    public Individual randomSelection(Population population) {
        int randomId = rg.nextInt((population.individuals.size()));
        return population.individuals.get(randomId);
    }

    // Select individuals for crossover
    public Individual[] tournamentSelection(Population pop, int tournamentSize) {
        // Create a tournament population
        Population tournament = new Population();
        tournament.initializePopulation(tournamentSize, populationSeed, true);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = rg.nextInt((pop.individuals.size()));
            tournament.individuals.add(i, pop.individuals.get(randomId));
        }
        // Return parents
        return new Individual[]{tournament.getFittest(), tournament.getSecondFittest()};
    }

    @Override
    public Individual[] fittestSelection(Population population, int i) {
        return new Individual[]{population.getFittest(), population.getSecondFittest()};
    }

}
