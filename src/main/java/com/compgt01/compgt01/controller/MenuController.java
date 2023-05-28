package com.compgt01.compgt01.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.compgt01.compgt01.model.MalhaModel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.transform.Scale;

/**
 *
 * @author mmo
 */
public class MenuController {

    @FXML
    private TitledPane root;

    @FXML
    private TextField textx;

    @FXML
    private TextField texty;

    @FXML
    private Button submit;

    @FXML
    private Button clean;

    @FXML
    private Slider addcolumnx;

    @FXML
    private Slider addrowy;

    @FXML
    private Slider zoom;

    @FXML
    private MalhaController malhaController;

    private MalhaModel malhaModel;

    public MalhaModel getMalhaModel() {
        return malhaModel;
    }

    public void setMalhaModel(MalhaModel malhaModel) {
        if (this.malhaModel == null) {
            this.malhaModel = malhaModel;
        }
    }

    public MalhaController getMalhaController() {
        return malhaController;
    }

    public void setMalhaController(MalhaController malhaController) {
        this.malhaController = malhaController;
    }

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
                    "Perspectiva"));

    @FXML
    private void initialize() {
        // Your code goes here
    }

    @FXML
    public void initializeListeners() {
        addcolumnx.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                if (getMalhaModel().getX() >= nv) {
                    return;
                }
                getMalhaController().addColumnX(ov, nv);

            } else if (nv < ov) {
                getMalhaController().removeColumnX(ov, nv);

            }
        });

        addrowy.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                if (getMalhaModel().getX() >= nv) {
                    return;
                }
                getMalhaController().addRowY(ov, nv);

            } else if (nv < ov) {
                getMalhaController().removeRowY(ov, nv);

            }
        });

        zoom.valueProperty().addListener((observable, oldValue, newValue) ->

        {
            Double ov = oldValue.doubleValue();
            Double nv = newValue.doubleValue();
            if (nv > ov) {
                getMalhaController().getTilepane().getTransforms().add(new Scale(1.25, 1.25,
                        0, 0));
            } else if (nv < ov) {
                getMalhaController().getTilepane().getTransforms().add(new Scale(0.75, 0.75,
                        0, 0));
            }
        });

    }

    @FXML
    public void onSubmit() {
        try {
            Integer x1 = Integer.valueOf(textx.getText().strip());
            Integer y1 = Integer.valueOf(texty.getText().strip());
            malhaController.getMalhaModel().getCoordinates()
                    .get((y1 + getMalhaModel().getY() / 2) - 1)
                    .get((x1 + getMalhaModel().getX() / 2) - 1)
                    .setSelected(true);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input1");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid integer values for x and y.");
            alert.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input2");
            alert.setHeaderText(null);
            alert.setContentText("Erro no backend" + e.toString());
            alert.showAndWait();
        } catch (IndexOutOfBoundsException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input3");
            alert.setHeaderText(null);
            alert.setContentText("Malha pequena demais" + e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    public void onClear() {
        malhaController.getMalhaModel().getCoordinates().forEach(
                row -> row.forEach(value -> value.setSelected(false)));
    }

}
