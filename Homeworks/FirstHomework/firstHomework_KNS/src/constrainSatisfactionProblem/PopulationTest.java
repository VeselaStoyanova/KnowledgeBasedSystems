package constrainSatisfactionProblem;

import java.io.IOException;

public class PopulationTest {
    public static void testRandomInit(){
        Population population = new Population(10, Cargo.getFirstCargos(), 5000);
        System.out.println(population);
    }

    public static void testCulling() throws IOException {
        System.out.println("The population before culling: ");
        Population population = new Population(10, Cargo.getFirstCargos(), 5000);
        System.out.println(population);
        System.out.println();
        System.out.println("The population after culling: ");
        population.selection(5);
        System.out.println(population);
    }

    public static void testCreatingNewGeneration() throws IOException{
        System.out.println("Old population: ");
        Population population = new Population(10, Cargo.getFirstCargos(), 5000);
        System.out.println(population);

        System.out.println();
        System.out.println("The population after culling: ");
        population.selection(5);
        System.out.println(population);

        System.out.println();
        System.out.println("New population: ");
        population.nextGeneration(5);
        System.out.println(population);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Testing the random initializing: ");
        testRandomInit();
        System.out.println("Testing the culling: ");
        testCulling();
        System.out.println("Testing creating new population: ");
        testCreatingNewGeneration();
    }
}
