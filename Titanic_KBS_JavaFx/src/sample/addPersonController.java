package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class addPersonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtShipClass;

    @FXML
    private TextField txtAge;

    @FXML
    private RadioButton rdoMale;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private TextField txtFare;

    @FXML
    private Button btnAddPerson;

    @FXML
    private TextField txtYesOrNo;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnAccuracy;

    @FXML
    private TextField txtAccuracy;

    @FXML
    void btnAccuracyOnAction(ActionEvent event) {

    }

    private boolean isShipClassValid(String shipClass){
        return shipClass.equals("1") || shipClass.equals("2") || shipClass.equals("3");
    }

    private boolean isNumberStringValid(String ageString){
        if (ageString == null) {
            return false;
        }
        try {
            double age = Double.parseDouble(ageString);
            if(age < 0){
                return false;
            }
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    @FXML
    void btnAddPersonOnAction(ActionEvent event) {
        String newPersonName = txtName.getText().toString();
        String newPersonShipClassString = txtShipClass.getText();
        int newPersonShipClass = -1;
        if(!isShipClassValid(newPersonShipClassString)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Important element");
            alert.setContentText("You have to fill your ship class with number between 1 and 3.");
            alert.show();
        }else{
            newPersonShipClass = Integer.parseInt(newPersonShipClassString);
        }
        String newPersonAgeString = txtAge.getText();
        int newPersonAge = -1;
        if(!isNumberStringValid(newPersonAgeString)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Important element");
            alert.setContentText("You have to fill your age with a number > 0.");
            alert.show();
        }else{
            newPersonAge = Integer.parseInt(newPersonAgeString);
        }
        String newPersonFareString = txtFare.getText();
        int newPersonFare = -1;
        if(!isNumberStringValid(newPersonFareString)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Important element");
            alert.setContentText("You have to fill your fare with a number > 0.");
            alert.show();
        }else{
            newPersonFare = Integer.parseInt(newPersonFareString);
        }
        int newPersonGender = -1;
        if(rdoFemale.isSelected()) {
            newPersonGender = 1;
        } else if(rdoMale.isSelected()){
            newPersonGender = 0;
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Important element");
            alert.setContentText("You have to fill your gender.");
            alert.show();
        }
        if(isShipClassValid(newPersonShipClassString) && isNumberStringValid(newPersonAgeString) &&
                isNumberStringValid(newPersonFareString) && (rdoFemale.isSelected() || rdoMale.isSelected())) {
            List<List<Integer>> gatheredEntities = new ArrayList<>();
            
            List<Integer> gatheredBackToEntities = new ArrayList<>();

            gatheredBackToEntities.add(newPersonShipClass);
            gatheredBackToEntities.add(newPersonAge);
            gatheredBackToEntities.add(newPersonGender);
            gatheredBackToEntities.add(newPersonFare);

            gatheredEntities.add(gatheredBackToEntities);
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert txtShipClass != null : "fx:id=\"txtShipClass\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert txtAge != null : "fx:id=\"txtAge\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert rdoMale != null : "fx:id=\"rdoMale\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert rdoFemale != null : "fx:id=\"rdoFemale\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert txtFare != null : "fx:id=\"txtFare\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert btnAddPerson != null : "fx:id=\"btnAddPerson\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert txtYesOrNo != null : "fx:id=\"txtYesOrNo\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert btnAccuracy != null : "fx:id=\"btnAccuracy\" was not injected: check your FXML file 'addPerson.fxml'.";
        assert txtAccuracy != null : "fx:id=\"txtAccuracy\" was not injected: check your FXML file 'addPerson.fxml'.";
    }
}

