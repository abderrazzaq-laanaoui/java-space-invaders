package com.enset.glsid.presentation.controller;

import com.enset.glsid.N7Invaders;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.var;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onExitClicked(ActionEvent actionEvent) {

        //create a confirmation dialog
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            // TODO : save data to the server via the service
             System.exit(0);
        }

    }

    public void onPlayBtnClicked(ActionEvent actionEvent) {
        System.out.println("start game");

        // get the scene fr
        var btn = (Button) actionEvent.getTarget();
        Stage sc = (Stage) btn.getScene().getWindow();
        new N7Invaders(sc);


    }
    private void changeView(String loginView, ActionEvent actionEvent) {
        // get the source of the event
        Parent root = (Parent) actionEvent.getSource();
        // get the scene of the source
        javafx.scene.Scene scene = root.getScene();
        // get the window of the scene
        javafx.stage.Window window = scene.getWindow();
        // get the stage of the window
        javafx.stage.Stage stage = (javafx.stage.Stage) window;
        // load the new view
        Parent newRoot = null;

        try {
            newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/" + loginView + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // set the new view
        scene.setRoot(newRoot);
        // resize the stage
        stage.sizeToScene();

    }

    public void on1vs1BtnClicked(ActionEvent actionEvent) {
        changeView("RoomView",actionEvent);
    }
}
