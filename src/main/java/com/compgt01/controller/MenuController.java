package com.compgt01.controller;

import com.compgt01.model.MalhaModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<String> choiceBox;

    @FXML
    private Slider addcolumnx;

    @FXML
    private Slider addrowy;

    @FXML
    private Slider zoom;

    @FXML
    private TextField newx;

    @FXML
    private TextField newy;

    @FXML
    private Button destroy;

    @FXML
    private MalhaController malhaController;

    @FXML
    private ControlMalhaController controlMalhaController;

    public ControlMalhaController getControlMalhaController() {
        return controlMalhaController;
    }

    public void setControlMalhaController(ControlMalhaController controlMalhaController) {
        this.controlMalhaController = controlMalhaController;
    }

    private MalhaModel malhaModel;

    public MalhaModel getMalhaModel() {
        return malhaModel;
    }

    public void setMalhaModel(MalhaModel malhaModel) {
        this.malhaModel = malhaModel;
    }

    public MalhaController getMalhaController() {
        return malhaController;
    }

    public void setMalhaController(MalhaController malhaController) {
        this.malhaController = malhaController;
    }

    ObservableList<String> algorithms = FXCollections.observableArrayList(
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
            "Perspectiva");

    @FXML
    private void initialize() {
        // Your code goes here
    }

    @FXML
    public void initializeListeners() {
        choiceBox.setItems(algorithms);

        choiceBox.setOnAction(event -> {
            switch (choiceBox.getValue()) {
                case ("Bresenham"):
                    // Integer x1 = Integer.valueOf(textx.getText().strip());
                    // Integer y1 = Integer.valueOf(texty.getText().strip());
                    // Transformacoes.bresenham(x1, y1, x2, y2);
                    break;
                // case ("Círculo"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Polilinha"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Preenchimento Recursivo"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Recorte"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Varredura"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Rotação"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Translação"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Escala"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Projeção Ortogonal"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                // case ("Perspectiva"):
                // Integer x1 = Integer.valueOf(textx.getText().strip());
                // Integer y1 = Integer.valueOf(texty.getText().strip());
                // Transformacoes.bresenham(x1, y1, x2, y2);
                // break;
                default:
                    break;
            }
        });

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
                if (getMalhaModel().getY() >= nv) {
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
                    .get(y1 + getMalhaModel().getY())
                    .get(x1 + getMalhaModel().getX())
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

    @FXML
    public void onDestruction() {
        try {
            Integer x1 = Integer.valueOf(newx.getText().strip());
            Integer y1 = Integer.valueOf(newy.getText().strip());
            getControlMalhaController().clear();
            getControlMalhaController().creation(x1, y1);
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

}