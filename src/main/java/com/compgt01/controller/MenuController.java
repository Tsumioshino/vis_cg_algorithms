package com.compgt01.controller;

import java.util.HashSet;
import java.util.Set;

import com.compgt01.model.MalhaModel;
import com.compgt01.model.PontoBasier;
import com.compgt01.tools.Transformacoes;

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
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;

/**
 *
 * @author mmo
 */
public class MenuController {

    @FXML
    private TitledPane root;

    @FXML
    private TextField x1;

    @FXML
    private TextField y1;

    @FXML
    private TextField x2;

    @FXML
    private TextField y2;

    @FXML
    private TextField inputBasier;

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
    private TextField newx;

    @FXML
    private TextField newy;

    @FXML
    private Button destroy;

    @FXML
    private ConsoleController consoleController;

    public ConsoleController getConsoleController() {
        return consoleController;
    }

    public void setConsoleController(ConsoleController consoleController) {
        this.consoleController = consoleController;
    }

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
            "Curva",
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
                    Integer x01 = Integer.valueOf(x1.getText().strip());
                    Integer y01 = Integer.valueOf(y1.getText().strip());

                    Integer x02 = Integer.valueOf(y2.getText().strip());
                    Integer y02 = Integer.valueOf(y2.getText().strip());

                    Transformacoes.bresenham(this, malhaController, x01, y01, x02, y02);
                    break;
                case ("Círculo"):
                    consoleController.executeAlgorithm(
                            String.format("Círculo %s %s %s",
                                    x2.getText().strip(),
                                    x1.getText().strip(),
                                    y1.getText().strip()));
                    Transformacoes.desenharCirculo(consoleController, this, malhaController,
                            Integer.valueOf(x2.getText()
                                    .strip()),
                            Integer.valueOf(
                                    x1.getText().strip()),
                            Integer.valueOf(y1.getText().strip()));
                    break;
                case ("Curva"):
                    Set<PontoBasier> pontosControle = new HashSet<>(0);
                    for (String pontoCo : inputBasier.getText().split(";")) {
                        String ponto[] = pontoCo.split(",");
                        pontosControle.add(new PontoBasier(Integer.parseInt(ponto[0]), Integer.parseInt(ponto[1]),
                                pontosControle.size()));
                    }

                    Transformacoes.desenharCurvaBasier(this, malhaController, pontosControle);
                    break;
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

        getMalhaController().getGridPane().setOnScroll((ScrollEvent event) -> {
            if (event.getDeltaY() != 0) {
                double zoomFactor = event.getDeltaY() > 0 ? 1.25 : 0.75;
                getMalhaController().getGridPane().getTransforms()
                        .add(new Scale(zoomFactor, zoomFactor, event.getX(), event.getY()));
                event.consume();
            }
        });

    }

    @FXML
    public void onSubmit() {
        try {
            Integer x01 = Integer.valueOf(textx.getText().strip());
            Integer y01 = Integer.valueOf(texty.getText().strip());
            malhaController.getMalhaModel().getGridCheckBox()[x01 + getMalhaModel().getX()][getMalhaModel().getY()
                    - y01]
                    .setSelected(!malhaController.getMalhaModel().getGridCheckBox()[x01
                            + getMalhaModel().getX()][getMalhaModel().getY() - y01].isSelected());
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
        for (int i = 0; i < (malhaController.getMalhaModel().getX() * 2) + 1; i++) {
            for (int j = 0; j < (malhaController.getMalhaModel().getY() * 2) + 1; j++) {
                malhaController.getMalhaModel().getGridCheckBox()[i][j].setSelected(false);
            }
        }
    }

    @FXML
    public void onDestruction() {
        try {
            Integer x01 = Integer.valueOf(newx.getText().strip());
            Integer y01 = Integer.valueOf(newy.getText().strip());
            getControlMalhaController().clear();
            getControlMalhaController().creation(x01, y01);
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
