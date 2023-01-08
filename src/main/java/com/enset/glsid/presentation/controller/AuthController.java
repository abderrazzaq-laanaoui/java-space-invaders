package com.enset.glsid.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void onLoginBtnClicked(ActionEvent actionEvent) {
    //redirect to LoginView
        changeView("LoginView",actionEvent);
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
            newRoot =FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/" + loginView + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // set the new view
        scene.setRoot(newRoot);
        // resize the stage
        stage.sizeToScene();

    }

    public void onBackButtonclicked1(ActionEvent actionEvent) {
        changeView("StartView",actionEvent);
    }

    public void onGuestBtnClicked(ActionEvent actionEvent) {
        changeView("HomeView",actionEvent);
    }

    public void onRegistreBtnClicked(ActionEvent actionEvent) {
        changeView("RegisterView",actionEvent);
    }

    public void onBackButtonclicked2(ActionEvent actionEvent) {
        changeView("StartView",actionEvent);
    }
}
