package algorithm;

import algorithm.models.Individual;
import algorithm.models.Population;
import algorithm.models.outputs.GAOutput;
import algorithm.models.outputs.GenerationInfo;
import algorithm.services.SelectionService;
import com.github.javafaker.Faker;
import helper.FileHelper;

import java.util.Random;

public class GeneticEngine implements IGeneticEngine {
    public Population population = new Population();
    public int generationCount = 0;
    public int engineSeed = 1;
    private Random rg;
    private Boolean debug;
    private SelectionService selectionService;
    private int maxLifespan;
    private int minLifespan;

    public GeneticEngine(Boolean debug) {
        this.debug = debug;
    }

    public void init(int size, int engineSeed, int maxLifespan, int minLifespan) {
        rg = new Random(engineSeed);
        this.maxLifespan = maxLifespan;
        this.minLifespan = minLifespan;
        if (minLifespan > maxLifespan) {
            throw new IllegalArgumentException();
        }
        selectionService = new SelectionService(debug, rg, engineSeed);
        //Initialize population
        initializePopulation(10, engineSeed);
        //Calculate fitness of each individual
        population.calculateFitness();
        System.out.println("Generation: " + generationCount + " Fittest: " + population.fittest);
    }

    public void evolve() {

        ++generationCount;

        //Do selection
        //Individual[] parents = selectionService.tournamentSelection(population, population.individuals.size() / 2);
        Individual[] parents = selectionService.fittestSelection(population, population.individuals.size() / 2);

        //Do crossover
        Individual offspring = conceiveOffspring(parents[0], parents[1]);

        //Do mutation under a random probability
        if (rg.nextInt() % 7 < 5) {
            mutation(selectionService.randomSelection(population));
        }

        //Add the fittest offspring to population
        addIndividualToOffspring(offspring);

        // kill old individuals
        int numberDeaths = Aging();
        //Calculate new fitness value
        population.calculateFitness();
        dumpGAStats(numberDeaths, 1);
        System.out.println("Generation: " + generationCount + " Fittest: " + population.fittest);


    }

    private void initializePopulation(int size, int populationSeed) {
        this.engineSeed = populationSeed;
        population.initializePopulation(size, populationSeed, false);
    }

    @Override
    public int Aging() {
        System.out.println("##Reaper collecting lives##");
        int deaths = 0;
        for (int i = 0; i < population.individuals.size(); i++) {

            population.individuals.get(i).age++;
            int randomNum = rg.nextInt((maxLifespan - minLifespan) + 1) + minLifespan;
            if (population.individuals.get(i).age > randomNum) {
                System.out.println("id:" + i + " " + population.individuals.get(i).name + " died of old age at " + population.individuals.get(i).age);
                population.individuals.remove(i);
                deaths++;
                System.out.println("population remaining: " + population.individuals.size());
            }
        }
        System.out.println("##Reaper Goes away##");


        return deaths;
    }


    //Crossover
    public Individual conceiveOffspring(Individual parentOne, Individual parentTwo) {

        //Select a random crossover point
        int crossOverPoint = rg.nextInt(population.individuals.get(0).genes.length);
        Individual offspring = new Individual(new Faker().name().fullName(), engineSeed);
        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            offspring.genes[i] = parentOne.genes[i];
        }
        for (int i = crossOverPoint; i < offspring.genes.length; i++) {
            offspring.genes[i] = parentTwo.genes[i];
        }
        return offspring;
    }


    public void addIndividualToOffspring(Individual offspring) {
        population.individuals.add(offspring);
    }

    //Mutation
    public void mutation(Individual individual) {
        //Select a random mutation point
        int mutationPoint = rg.nextInt(population.individuals.get(0).genes.length);

        //Flip values at the mutation point
        if (individual.genes[mutationPoint] == 0) {
            individual.genes[mutationPoint] = 1;
        } else {
            individual.genes[mutationPoint] = 0;
        }
    }


    private void dumpGAStats(int deaths, int newOffspringNumber) {
        if (debug) {
            GAOutput output = new GAOutput();
            output.population = population;
            output.fittest = population.getFittest();
            output.generationInfo = new GenerationInfo();
            output.generationInfo.generationCount = generationCount;
            output.generationInfo.populationSeed = engineSeed;
            output.generationInfo.oldAgeDeaths = deaths;
            output.generationInfo.newOffsprings = newOffspringNumber;
            FileHelper.WriteGeneration(output);
        }
    }
}
