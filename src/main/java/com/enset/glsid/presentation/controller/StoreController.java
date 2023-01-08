package com.enset.glsid.presentation.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // StoreController$1 inner class

     static class CellsCallback implements Callback<ListView<String>, ListCell<String>> {
        @Override
        public ListCell<String> call(ListView<String> param) {
            // Custom code for creating cells goes here...
            return new ListCell<String>();
        }
    }

}
