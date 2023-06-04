package com.compgt01.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.compgt01.model.MalhaModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

public class ControlMalhaController implements Initializable {
    @FXML
    protected TitledPane controle;
    @FXML
    protected MenuController controleController;
    @FXML
    protected HBox malha;
    @FXML
    protected MalhaController malhaController;
    @FXML
    protected TextArea console;
    @FXML
    protected ConsoleController consoleController;

    private MalhaModel malhaModel;

    public MalhaModel getMalha() {
        return malhaModel;
    }

    public void setMalhaModel(MalhaModel malhaModel) {
        this.malhaModel = malhaModel;
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creation(10, 10);
    }

    public void clear() {
        malha.getChildren().clear();
    }

    public void creation(int x, int y) {
        this.setMalhaModel(new MalhaModel(x, y));
        controleController.setControlMalhaController(this);
        controleController.setMalhaController(malhaController);
        controleController.setMalhaModel(getMalha());
        controleController.setConsoleController(consoleController);

        malhaController.setMalhaModel(getMalha());
        malhaController.initializeMalha();
        malha.getChildren().add(malhaController.initializeMalha());
        controleController.initializeListeners();
    }
}
