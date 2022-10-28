import algorithm.GeneticEngine;

public class main {

    public static void main(String args[]) {

        int seed = 5;
        int populationSize = 10;
        int maxLifespan = 10;
        int minLifespan = 5;
        GeneticEngine engine = new GeneticEngine(true);
        engine.init(populationSize, seed, maxLifespan, minLifespan);
        while (engine.population.fittest < 5) {
            engine.evolve();
        }
        System.out.println("\nSolution found in generation " + engine.generationCount);
        System.out.println("Fitness: " + engine.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(engine.population.getFittest().genes[i]);
        }

    }


}
