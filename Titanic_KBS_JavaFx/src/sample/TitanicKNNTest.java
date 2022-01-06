package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TitanicKNNTest {

    private static Map<String, Double> genderEncoder = new HashMap<>(){{
        put("F", 1.0);
        put("M", 0.0);
    }};

//    public static void main(String[] args) {
//        List<List<String>> X = new ArrayList<>();
//        List<String> columns = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(".\\src\\titanic.csv"))) {
//            String line;
//            columns = Collections.singletonList(br.readLine());
//
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                X.add(Arrays.asList(values));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        List<Double> classes = X.stream()
//                .map(s -> Double.parseDouble(s.get(0)))
//                .collect(Collectors.toList());
//
//        List<Double> ages = X.stream()
//                .map(s -> Double.parseDouble(s.get(1)))
//                .collect(Collectors.toList());
//
//        List<Double> genders = X.stream()
//                .map(s -> genderEncoder.get(s.get(2)))
//                .collect(Collectors.toList());
//
//        List<Double> fares = X.stream()
//                .map(s -> Double.parseDouble(s.get(3)))
//                .collect(Collectors.toList());
//
//        List<Integer> survived = X.stream()
//                .map(s -> Integer.parseInt(s.get(4)))
//                .collect(Collectors.toList());
//
//        List<List<Double>> gatheredEntities = new ArrayList<>();
//
//        int size = classes.size();
//        for (int i = 0; i < size; i++) {
//            List<Double> gatheredBackToEntities = new ArrayList<>();
//
//            gatheredBackToEntities.add(classes.get(i));
//            gatheredBackToEntities.add(ages.get(i));
//            gatheredBackToEntities.add(genders.get(i));
//            gatheredBackToEntities.add(fares.get(i));
//
//            gatheredEntities.add(i, gatheredBackToEntities);
//        }
//
//        List<List<Double>> X_train = new ArrayList<>();
//        List<List<Double>> X_test = new ArrayList<>();
//
//        List<Integer> y_train = new ArrayList<>();
//        List<Integer> y_test = new ArrayList<>();
//
//        trainTestSplit(gatheredEntities, survived, X_train, X_test, y_train, y_test);
//
//        TitanicKNN model = new TitanicKNN(1);
//        model.fit(X_train, y_train);
//
//        List<Integer> y_pred = model.predict(X_test);
//
//        int correct = 0;
//        size = y_pred.size();
//
//        for (int i = 0; i < size; i++) {
//            if(y_pred.get(i).equals(y_test.get(i))) {
//                ++correct;
//            }
//        }
//
//        System.out.printf("Predicted: %s\n", y_pred);
//        System.out.printf("Actual: %s\n", y_test);
//
//        System.out.println("Correct predictions: " + correct);
//        System.out.println("From all: " + size);
//        System.out.printf("Accuracy: %f", (double) correct / (double) size);
//    }


    private static void trainTestSplit(List< List<Double>> entities, List<Integer> y,
                                       List< List<Double>> X_train,
                                       List< List<Double>> X_test,
                                       List<Integer> y_train,
                                       List<Integer> y_test){

        int size = entities.size();
        //percent data for train
        int limit = (int) Math.round(size * 0.7);

        List<Integer> range = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(range);

        for (int i = 0; i <= limit; i++) {
            X_train.add( entities.get(range.get(i)) );
            y_train.add( y.get(range.get(i)) );
        }

        for (int i = limit + 1; i < size; i++) {
            X_test.add( entities.get(range.get(i)) );
            y_test.add( y.get(range.get(i)) );
        }
    }
}

