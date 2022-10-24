package algorithm;

import algorithm.models.Individual;

public interface IGeneticEngine {
    void init(int size, int populationSeed,int maxLifespan,int minLifespan);
    void mutation(Individual individual);

    Individual conceiveOffspring(Individual parentOne, Individual parentTwo);

    void addIndividualToOffspring(Individual offspring);

    int Aging();
}
