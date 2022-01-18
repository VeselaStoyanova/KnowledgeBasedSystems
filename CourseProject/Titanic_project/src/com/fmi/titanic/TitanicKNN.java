package com.fmi.titanic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TitanicKNN {

    private final int k;
    private List<List<Double>> X;
    private List<Integer> y; //vector from X`s classes

    public TitanicKNN(int k) {
        this.k = Math.max(k, 1);
    }
                                           //our rows
    public static Double euclideanDistance(List<Double> s1, List<Double> s2){
        int size = s1.size();
        double sum = 0;

        for (int i = 0; i < size; i++) {
            sum += Math.pow(s1.get(i) - s2.get(i), 2);
        }

        return Math.sqrt(sum);
    }

    public void fit(List<List<Double>> X, List<Integer> y){
        this.X = X;
        this.y = y;
    }

    public List<Integer> predict (List<List<Double>> X) {
        return X.stream().map(this::predictHelper).collect(Collectors.toList());
    }

    private Integer predictHelper(List<Double> x){
        //sort x depending on euclideanDistance
           List<Integer> nearestN = X.stream().sorted(Comparator.comparingDouble(s -> euclideanDistance(s, x )))
            .limit(k) //Get k nearest samples
            .map(s -> y.get(X.indexOf(s))) // get the class of this vector
            .collect(Collectors.toList());

        //Majority vote
        List<Integer> distinct = nearestN.stream()
                .distinct()
                .sorted(Comparator.comparingInt(s -> Collections.frequency(nearestN, s)))
                .collect(Collectors.toList());

        return distinct.get(distinct.size() - 1);
    }
}


