package com.enset.glsid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //homeView locared in ressources/views/HomeView.fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getClassLoader().getResource("view/StartView.fxml"))
        );
        primaryStage.setTitle("N7Invaders");
        primaryStage.setScene(new javafx.scene.Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
