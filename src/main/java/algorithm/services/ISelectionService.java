package algorithm.services;

import algorithm.models.Individual;
import algorithm.models.Population;

public interface ISelectionService {

    Individual randomSelection(Population population);

    // Select individuals for crossover
    Individual[] tournamentSelection(Population pop, int tournamentSize);

    Individual[] fittestSelection(Population population, int i);
}
