package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class homeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddPerson;

    @FXML
    private Button btnExit;

    @FXML
    void btnAddPersonOnAction(ActionEvent event) throws IOException{
        showAddPersonPage(event);
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Platform.exit();
    }

    public void showAddPersonPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("addPerson.fxml"));
        Stage addPersonStage = new Stage();
        addPersonStage.setTitle("Add Person Page");
        addPersonStage.setScene(new Scene(root));
        addPersonStage.show();
    }

    @FXML
    void initialize() {
        assert btnAddPerson != null : "fx:id=\"btnAddPerson\" was not injected: check your FXML file 'home.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'home.fxml'.";

    }
}

