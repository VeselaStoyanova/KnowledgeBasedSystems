package knnAlgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KNN {
    private final int k;
    private List<List<Double>> X;
    private List<String> y;

    public KNN(int k){
        this.k = Math.max(k, 1);
    }

    public static Double euclideanDistance(List<Double>firstSample, List<Double> secondSample){
        int size = firstSample.size();
        double sum = 0;

        for(int i = 0; i < size; i++){
            sum += Math.pow(firstSample.get(i) - secondSample.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    public void fit(List<List<Double>>X, List<String> y){
        this.X = X;
        this.y = y;
    }

    //Find euclidean distance to all points and get the nearest k
    private String predictHelper(List<Double> x){

        List<String> nearestNeighbours = X.stream()
                .sorted(Comparator.comparingDouble(s -> euclideanDistance(s, x)))
                .limit(k)
                .map(s -> y.get(X.indexOf(s)))
                .collect(Collectors.toList());

        //Majority vote
        List<String> distinct = nearestNeighbours.stream()
                .distinct()
                .sorted(Comparator.comparingInt(s -> Collections.frequency(nearestNeighbours, s)))
                .collect(Collectors.toList());
        return distinct.get(distinct.size() - 1);
    }

    public List<String> predict(List<List<Double>> X){
        return X.stream().map(this::predictHelper).collect(Collectors.toList());
    }
}
