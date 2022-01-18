package constrainSatisfactionProblem;

import java.util.ArrayList;

public class GenomeTest {
    //Test are the values randomize
    public static void testRandomInit() {
        System.out.println(new Genome(10));
    }

    public static void testCrossover() {
        Genome firstGenome = new Genome(new ArrayList<>() {{
            add(0);
            add(0);
            add(0);
            add(1);
            add(1);
            add(0);
            add(1);
        }});

        Genome secondGenome = new Genome(new ArrayList<>() {{
            add(1);
            add(1);
            add(0);
            add(0);
            add(1);
            add(1);
            add(0);
        }});

        System.out.println(firstGenome);
        System.out.println(secondGenome);

        Genome[] children = firstGenome.twoPointCrossover(secondGenome);

        System.out.println(children[0]);
        System.out.println(children[1]);
    }

    public static void testGetFitness(Cargo[] cargos) {
        Genome firstGenome = new Genome(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
            add(0);
            add(1);
            add(0);
            add(1);
        }});

        Genome secondGenome = new Genome(new ArrayList<>() {{
            add(0);
            add(0);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
        }});

        testFitness(cargos, firstGenome, secondGenome);
    }

    private static void testFitness(Cargo[] cargos, Genome firstGenome, Genome secondGenome) {
        System.out.println(firstGenome.getFitness(cargos, 60));
        System.out.println(firstGenome.getFitness(cargos, 721));

        System.out.println(secondGenome.getFitness(cargos, 20));
        System.out.println(secondGenome.getFitness(cargos, 1200));
        System.out.println(secondGenome.getFitness(cargos, 901));
    }

    public static void testGetFitnessForMoreCargos(Cargo[] cargos) {
        Genome firstGenome = new Genome(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
            add(0);
            add(1);
            add(0);
            add(1);
            add(0);
            add(1);
            add(0);
            add(0);
        }});

        Genome secondGenome = new Genome(new ArrayList<>() {{
            add(0);
            add(0);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
            add(0);
            add(0);
            add(0);
        }});

        testFitness(cargos, firstGenome, secondGenome);
    }

    public static void testMutation() {
        Genome genome = new Genome(10);
        System.out.printf("The Genome before mutation: %s\n", genome);

        genome.mutate();
        System.out.printf("The Genome after mutation: %s\n", genome);
    }

    public static void main(String[] args) {
        System.out.println("Testing the random initializing: ");
        testRandomInit();
        System.out.println("Testing the two-point crossover method: ");
        testCrossover();
        System.out.println("Testing the method getFitness:");
        testGetFitness(Cargo.getFirstCargos());
        System.out.println("Testing the method getFitness for more cargos:");
        testGetFitnessForMoreCargos(Cargo.getSecondCargos());
        System.out.println("Testing the mutation:");
        testMutation();
    }
}
