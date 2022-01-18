package constrainSatisfactionProblem;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Population {
    private ArrayList<Genome> genomes;
    private Cargo[] cargos;
    private int weightLimit;

    public Population(int numberOfGenomes, Cargo[] cargos, int weightLimit){
        setGenomes(null);
        setCargos(cargos);
        setWeightLimit(weightLimit);

        while(numberOfGenomes > 0){
            genomes.add(new Genome(cargos.length));
            --numberOfGenomes;
        }
    }

    public void setGenomes(ArrayList<Genome> genomes)  {
        this.genomes = new ArrayList<>();

        if(genomes == null){
            return;
        }

        this.genomes.addAll(genomes);
    }

    public void setCargos(Cargo[] cargos) {
       this.cargos = cargos;
    }

    public void setWeightLimit(int weightLimit){
        this.weightLimit = weightLimit;
    }

    public void selection(int cullingLimit) throws IOException {
        if(cullingLimit <= 0){
            throw new IOException("The culling limit must be a positive number.");
        }
        //The multiply by -1 is to sort in decreasing order.
        genomes.sort(Comparator.comparingInt(obj -> obj.getFitness(cargos, weightLimit) * -1));

        //We want the best first cullingLimit genomes
        List<Genome> list = genomes.subList(0, cullingLimit);
        setGenomes(null);
        genomes.addAll(list);
    }

    public void nextGeneration(int cullingLimit) throws IOException {
        if(cullingLimit > genomes.size()){
            throw new IOException("The culling limit must be smaller or equal to the number of genomes.");
        }

        ArrayList<Genome> nextGenomes = new ArrayList<>();
        selection(cullingLimit);

        Random random = new Random();
        while(cullingLimit * 2 > 0){
            int firstParentIndex = 0;
            int secondParentIndex = 0;

            while(firstParentIndex == secondParentIndex){
                int genomesSize = genomes.size();
                firstParentIndex = random.nextInt(genomesSize);
                secondParentIndex = random.nextInt(genomesSize);
            }

            nextGenomes.addAll(Arrays.asList(genomes.get(firstParentIndex).twoPointCrossover(genomes.get(secondParentIndex))));
            --cullingLimit;
        }
        setGenomes(nextGenomes);
    }

    public Genome getCurrBest(){
        genomes.sort(Comparator.comparingInt(obj -> obj.getFitness(cargos, weightLimit) * -1));
        return genomes.get(0);
    }

    public boolean stop(Integer bestFitness){
        if(bestFitness != null){
            List<Genome> bestGenomes = genomes.stream().filter((g) -> g.getFitness(cargos, weightLimit) == bestFitness).collect(Collectors.toList());
            if(bestGenomes.size() != 0){
                return true;
            }
        }

        int currFitness = genomes.get(0).getFitness(cargos, weightLimit);
        for(int i = 1; i < genomes.size(); i++){
            if (currFitness != genomes.get(i).getFitness(cargos, weightLimit)) {
                return false;
            }
        }
        System.out.printf("All individuals have the same fitness of %d\n", currFitness);

        return true;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n",
                genomes,
                Arrays.toString(genomes.stream().map((g) -> g.getFitness(cargos, weightLimit)).toArray())
        );
    }
}
