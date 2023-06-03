package com.compgt01.controller;

import java.util.HashSet;
import java.util.Set;

import com.compgt01.model.MalhaModel;
import com.compgt01.model.PontoBasier;
import com.compgt01.tools.Transformacoes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

/**
 *
 * @author mmo
 */
public class MenuController {

    @FXML
    private TitledPane root;

    @FXML
    private TitledPane algorithmspane;

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
            "Circulo",
            "Curva",
            "Polilinha",
            "Preenchimento Recursivo",
            "Varredura",
            // "Recorte",
            "Rotação",
            "Translacao",
            "Escala",
            "Projecao Ortogonal",
            "Perspectiva");

    Accordion stalgorithm = new Accordion();

    private void algorithmsPaneBox(String algoritmo) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(algoritmo);
        switch (algoritmo) {
            case ("Bresenham"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                TextField x002 = new TextField();
                TextField y002 = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                x002.setPromptText("x");
                y002.setPromptText("y");
                Button b1 = new Button();
                b1.setOnAction(e -> {
                    Integer x01 = Integer.valueOf(x001.getText().strip());
                    Integer y01 = Integer.valueOf(y001.getText().strip());

                    Integer x02 = Integer.valueOf(x002.getText().strip());
                    Integer y02 = Integer.valueOf(y002.getText().strip());

                    consoleController.executeAlgorithm(
                            String.format("Bresenham %d %d %d %d",
                                    x01, y01, x02, y02));
                    Transformacoes.bresenham(consoleController, this, malhaController, x01, y01, x02, y02);
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(x002, y002), b1));
                break;
            }
            case ("Circulo"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                TextField raio = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                raio.setPromptText("Raio");
                Button b1 = new Button();
                b1.setOnAction(e -> {
                    consoleController.executeAlgorithm(
                            String.format("Círculo %s %s %s",
                                    raio.getText().strip(),
                                    x001.getText().strip(),
                                    y001.getText().strip()));
                    Transformacoes.desenharCirculo(consoleController, this, malhaController,
                            Integer.valueOf(raio.getText()
                                    .strip()),
                            Integer.valueOf(
                                    x001.getText().strip()),
                            Integer.valueOf(y001.getText().strip()));
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(raio), b1));
                break;
            }
            case ("Curva"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                TextField x002 = new TextField();
                TextField y002 = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                x002.setPromptText("x");
                y002.setPromptText("y");
                Button b1 = new Button();
                b1.setOnAction(e -> {
                    Integer x01 = Integer.valueOf(x001.getText().strip());
                    Integer y01 = Integer.valueOf(y001.getText().strip());

                    Integer x02 = Integer.valueOf(x002.getText().strip());
                    Integer y02 = Integer.valueOf(y002.getText().strip());

                    Set<PontoBasier> pontosControle = new HashSet<>(0);
                    for (String pontoCo : inputBasier.getText().split(";")) {
                        String ponto[] = pontoCo.split(",");
                        pontosControle.add(new PontoBasier(Integer.parseInt(ponto[0]),
                                Integer.parseInt(ponto[1]),
                                pontosControle.size()));
                        Transformacoes.desenharCurvaBasier(this, malhaController, pontosControle);

                    }
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(x002, y002), new Button()));

                break;
            }

            case ("Polilinha"): {
                break;
            }

            case ("Preenchimento Recursivo"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                titledPane.setContent(new VBox(new HBox(x001, y001), new Button()));
                break;
            }
            case ("Varredura"): {
                break;
            }

            case ("Rotação"): {
                TextField angulo = new TextField();
                TextField pontopivo = new TextField();
                angulo.setPromptText("e.g: 30º");
                pontopivo.setPromptText("Pivô");
                titledPane.setContent(new VBox(new HBox(angulo), new HBox(pontopivo), new Button()));
                break;
            }

            case ("Translacao"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                titledPane.setContent(new VBox(new HBox(x001, y001), new Button()));
                break;
            }
            case ("Escala"): {
                TextField x001 = new TextField();
                TextField y001 = new TextField();
                TextField pontofixo = new TextField();
                x001.setPromptText("x");
                y001.setPromptText("y");
                pontofixo.setPromptText("Ponto fixo");
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(pontofixo), new Button()));
                break;
            }
            case ("Projecao Ortogonal"): {
                break;
            }
            case ("Perspectiva"): {
                break;
            }

            default: {
                throw new IllegalStateException();
            }
        }
        stalgorithm.getPanes().add(titledPane);
    }

    @FXML
    public void initializeListeners() {
        for (String item : algorithms) {
            algorithmsPaneBox(item);
        }

        algorithmspane.setContent(stalgorithm);

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
