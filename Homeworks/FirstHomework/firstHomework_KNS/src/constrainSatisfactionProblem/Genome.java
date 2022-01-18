package constrainSatisfactionProblem;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Genome {
    private ArrayList<Integer> places;
    public Genome(int length){
        setPlaces(null);
        Random random = new Random();

        while(length > 0){
            places.add(random.nextBoolean() ? 1 : 0);
            --length;
        }
    }

    public Genome(ArrayList<Integer> places){
        setPlaces(places);
    }

    public Genome(List<Integer> firstParentFirstPlaces, List<Integer> secondParentPlaces, List<Integer> firstParentSecondPlaces){
        setPlaces(null);
        Stream.of(firstParentFirstPlaces, secondParentPlaces, firstParentSecondPlaces).forEach(this.places::addAll);
    }

    public void setPlaces(ArrayList<Integer> places) {
        this.places = (places == null ? new ArrayList<>() : places);
    }

    public int getFitness(Cargo[] cargos, int weightLimit){
        if(cargos.length != places.size()){
            throw new InvalidParameterException("The length of the cargos and the places must be equal.");
        }

        int value = 0;
        int weight = 0;

        for(int i = 0; i < cargos.length; i++){
            if(places.get(i) == 1){
                weight += cargos[i].getWeight();
                value += cargos[i].getValue();

                if(weight > weightLimit){
                    return 0;
                }
            }
        }
        return value;
    }

    public Genome[] twoPointCrossover(Genome partner){
        if(partner == null){
            try {
                throw new IOException("The genome must not be null.");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        Genome[] result = new Genome[]{this, partner};
        Random random = new Random();

        int firstCrossOverIndex = random.nextInt(this.places.size());
        int secondCrossOverIndex = random.nextInt(this.places.size());

        if(firstCrossOverIndex == secondCrossOverIndex){
            if(firstCrossOverIndex == 0){
                secondCrossOverIndex++;
            }else{
                firstCrossOverIndex--;
            }
        }

        if(secondCrossOverIndex < firstCrossOverIndex){
            int temp = firstCrossOverIndex;
            firstCrossOverIndex = secondCrossOverIndex;
            secondCrossOverIndex = temp;
        }

        result[0] = new Genome(this.places.subList(0, firstCrossOverIndex), partner.places.subList(firstCrossOverIndex, secondCrossOverIndex),
                this.places.subList(secondCrossOverIndex, this.places.size()));
        result[1] = new Genome(partner.places.subList(0, firstCrossOverIndex), this.places.subList(firstCrossOverIndex, secondCrossOverIndex),
                partner.places.subList(secondCrossOverIndex, partner.places.size()));

        return result;
    }

    public void mutate(){
        Random random = new Random();
        //Not to do mutation if the probability is less or equal to 5%
        if(random.nextInt(100) > 95){
            return;
        }

        for(int i = 0; i < places.size(); i++){
            if(random.nextBoolean()){
                places.set(i, (places.get(i) == 0 ? 1 : 0));
            }
        }
    }

    @Override
    public String toString() {
        return places.toString();
    }
}
