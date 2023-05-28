package com.compgt01.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

/**
 *
 * @author mmo
 */
public class MenuControle implements Initializable {

    @FXML
    private TitledPane root;
    @FXML
    protected TextField textx;
    @FXML
    protected TextField texty;
    @FXML
    protected Button submit;
    @FXML
    protected Button clean;
    @FXML
    protected Slider addcolumnx;
    @FXML
    protected Slider addcolumny;

    List<String> algorithms = new ArrayList<>(
            Arrays.asList(
                    "Bresenham",
                    "Círculo",
                    "Polilinha",
                    "Preenchimento Recursivo",
                    "Varredura",
                    "Recorte",
                    "Rotação",
                    "Translação",
                    "Escala",
                    "Projeção Ortogonal",
                    "Perspectiva")
                    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
