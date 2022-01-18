package constrainSatisfactionProblem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void start() throws IOException{
        System.out.println("Enter the number of genomes: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfGenomes = scanner.nextInt();
        System.out.println();
        System.out.println("Enter the epochs: ");
        int epochs = scanner.nextInt();
        int cullingLimit = numberOfGenomes / 2;

        Cargo[] cargos = Cargo.getFirstCargos();
        System.out.println();
        System.out.println("Enter the filename: ");
        String filename = scanner.next();
        int bestValue = Cargo.sumValues(filename);
        int weightLimit = Cargo.sumWeights(filename);
        test(numberOfGenomes, epochs, cullingLimit, cargos, bestValue, weightLimit);
    }

    private static void test(int numberOfGenomes, int epochs, int cullingLimit, Cargo[] cargos, int bestValue, int weightLimit) throws IOException {
        Population population = new Population(numberOfGenomes, cargos, weightLimit);

        for(int i = 0; i < epochs; i++){
            System.out.println(population);
            population.nextGeneration(cullingLimit);

            if(population.stop(bestValue)){
                System.out.printf("\nEpochs needed: %d\n", i);
                System.out.println("Generation:");
                System.out.println(population);
                System.out.printf("Answer: %s\n", population.getCurrBest());
                return;
            }
        }
        System.out.printf("Value found: %s", population.getCurrBest());
        System.out.printf("Couldn't reach an optimal value for %d epochs", epochs);
        System.out.println("Last generation:");
        System.out.println(population);
    }

    public static void main(String[] args) throws IOException{
        start();
    }
}
