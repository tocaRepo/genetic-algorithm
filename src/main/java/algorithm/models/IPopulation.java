package algorithm.models;

public interface IPopulation {

    public void initializePopulation(int size,int individualRandomSeed);

    //Get the fittest individual
    public Individual getFittest();

    //Get the second-fittest individual
    public Individual getSecondFittest();

    //Get index of the least fit individual
    public int getLeastFittestIndex();

    //Calculate fitness of each individual
    public void calculateFitness();
}
