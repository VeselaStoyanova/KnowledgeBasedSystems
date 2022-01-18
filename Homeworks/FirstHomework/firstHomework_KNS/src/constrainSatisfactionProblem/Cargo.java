package constrainSatisfactionProblem;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Cargo {
    private final int value;
    private final int weight;

    public Cargo(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public static Cargo[] getFirstCargos() {
        String filename = "cargos.json";
        return getCargos(filename);
    }

    public static Cargo[] getSecondCargos(){
        String filename = "moreCargos.json";
        return getCargos(filename);
    }

    private static Cargo[] getCargos(String filename) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Data data = gson.fromJson(reader, Data.class);
        Cargo[] cargoArray = new Cargo[data.getData().size()];
        for (int i = 0; i < data.getData().size(); i++) {
            cargoArray[i] = new Cargo(data.getData().get(i).getS(),data.getData().get(i).getT());
        }
        return cargoArray;
    }

    @Override
    public String toString() {
        return String.format("Value: " + value + "\nWeight: " + weight);
    }

    public static int sumValues(String filename){
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Data data = gson.fromJson(reader, Data.class);
        int value = 0;
        for (int i = 0; i < data.getData().size(); i++) {
            value += data.getData().get(i).getS();
        }
        return value;
    }

    public static int sumWeights(String filename){
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Data data = gson.fromJson(reader, Data.class);
        int weight = 0;
        for (int i = 0; i < data.getData().size(); i++) {
            weight += data.getData().get(i).getT();
        }
        return weight;
    }
}
