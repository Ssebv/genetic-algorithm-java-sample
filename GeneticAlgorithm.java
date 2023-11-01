import java.util.*;
import java.util.function.Function;

public class GeneticAlgorithm {
    public static void main(String[] args) {
        List<Thing> things = new ArrayList<>();
        things.add(new Thing("Laptop", 500, 2200));
        things.add(new Thing("Headphones", 150, 160));
        things.add(new Thing("Coffee Mug", 60, 350));
        things.add(new Thing("Notepad", 40, 333));
        things.add(new Thing("Water Bottle", 30, 192));

        List<Thing> moreThings = new ArrayList<>();
        moreThings.add(new Thing("Mints", 5, 25));
        moreThings.add(new Thing("Socks", 10, 38));
        moreThings.add(new Thing("Tissues", 15, 80));
        moreThings.add(new Thing("Phone", 500, 200));
        moreThings.add(new Thing("Baseball Cap", 100, 70));
        moreThings.addAll(things);

        int genemeLength = moreThings.size(); // Adjust for "things" or "moreThings"
        int populationSize = 10;
        int weightLimit = 3000;
        int fitnessLimit = 740;
        int generationLimit = 100;

        long start = System.currentTimeMillis();
        Population population = generatePopulation(populationSize, genemeLength);
        int generations = runEvolution(
            population,
            genome -> fitness(genome, things, weightLimit),
            fitnessLimit,
            population1 -> selectionPair(population1, genome -> fitness(genome, moreThings, weightLimit)),
            genome -> singlePointCrossover(List.of(genome.get(0)), List.of(genome.get(1))),

            genome -> mutation(genome, 1, 0.05),
            generationLimit
        );
        long end = System.currentTimeMillis();

        System.out.println("Number of generations: " + generations);
        System.out.println("Time: " + (end - start) + "ms");
        System.out.println("Best solution: " + genomeToThings(population.get(0), moreThings));
    }

    static class Thing {
        String name;
        int value;
        int weight;

        Thing(String name, int value, int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
        }
    }

    static class Genome extends ArrayList<Integer> {
        // Custom methods can be added if needed
    }

    static class Population extends ArrayList<Genome> {
        // Custom methods can be added if needed
    }

    static Genome generateGenome(int length) {
        Genome genome = new Genome();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            genome.add(random.nextInt(2));
        }
        return genome;
    }

    static Population generatePopulation(int size, int genomeLength) {
        Population population = new Population();
        for (int i = 0; i < size; i++) {
            population.add(generateGenome(genomeLength));
        }
        return population;
    }

    static int fitness(Genome genome, List<Thing> things, int weightLimit) {
        if (genome.size() != things.size()) {
            throw new IllegalArgumentException("Genome and things must be of the same length");
        }

        int weight = 0;
        int value = 0;

        for (int i = 0; i < genome.size(); i++) {
            if (genome.get(i) == 1) {
                Thing thing = things.get(i);
                weight += thing.weight;
                value += thing.value;

                if (weight > weightLimit) {
                    return 0;
                }
            }
        }

        return value;
    }

    static Population selectionPair(Population population, Function<Genome, Integer> fitnessFunc) {
        Population selected = new Population();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int totalFitness = population.stream().mapToInt(fitnessFunc::apply).sum();
            int randomValue = random.nextInt(totalFitness);
            int sum = 0;
            int j = 0;
            while (sum < randomValue) {
                sum += fitnessFunc.apply(population.get(j++));
            }
            selected.add(population.get(j - 1));
        }
        return selected;
    }

    static Genome singlePointCrossover(List<Integer> genome1, List<Integer> genome2) {
        if (genome1 == null || genome2 == null) {
            throw new IllegalArgumentException("Genomes cannot be null");
        }
        Random random = new Random();
        int index = random.nextInt(genome1.size());
        Genome newGenome = new Genome();
    
        for (int i = 0; i < index; i++) {
            newGenome.add(genome1.get(i));
        }
    
        for (int i = index; i < genome2.size(); i++) {
            newGenome.add(genome2.get(i));
        }
    
        return newGenome;
    }
    

    static Genome mutation(Genome genome, int num, double probability) {
        Random random = new Random();
        Genome mutatedGenome = new Genome();
    
        for (int i = 0; i < genome.size(); i++) {
            if (random.nextDouble() < probability) {
                mutatedGenome.add(Math.abs(genome.get(i) - 1));
            } else {
                mutatedGenome.add(genome.get(i));
            }
        }
    
        return mutatedGenome;
    }

    static int runEvolution(Population population, Function<Genome, Integer> fitnessFunc, int fitnessLimit,
                            Function<Population, Population> selectionFunc, 
                            Function<Genome, Genome> crossoverFunc,
                            Function<Genome, Genome> mutationFunc, int generationLimit) {
        for (int i = 0; i < generationLimit; i++) {
            population.sort((g1, g2) -> fitnessFunc.apply(g2) - fitnessFunc.apply(g1));
            if (fitnessFunc.apply(population.get(0)) >= fitnessLimit) {
                return i;
            }
            Population nextGeneration = new Population();
            nextGeneration.add(population.get(0));
            for (int j = 0; j < population.size() / 2 - 1; j++) {
                Population parents = selectionFunc.apply(population);
                Genome offspringA = crossoverFunc.apply(parents.get(0));
                Genome offspringB = crossoverFunc.apply(parents.get(1));
                nextGeneration.add(mutationFunc.apply(offspringA));
                nextGeneration.add(mutationFunc.apply(offspringB));
            }
            population = nextGeneration;
                

        }
        population.sort((g1, g2) -> fitnessFunc.apply(g2) - fitnessFunc.apply(g1));
        return generationLimit;
    }

    static List<Thing> genomeToThings(Genome genome, List<Thing> things) {
        List<Thing> result = new ArrayList<>();
        for (int i = 0; i < genome.size(); i++) {
            if (genome.get(i) == 1) {
                result.add(things.get(i));
            }
        }
        return result;
    }
}
