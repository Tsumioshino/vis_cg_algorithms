package com.compgt01.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.compgt01.model.MalhaModel;
import com.compgt01.model.PontoBasier;
import com.compgt01.tools.Transformacoes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
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

    int pcc = 0;

    List<String> pontoscontrole = new ArrayList<>();

    private void algorithmsPaneBox(String algoritmo) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(algoritmo);
        switch (algoritmo) {
            case ("Bresenham"): {
                TextField x001 = createTextField("x");
                TextField y001 = createTextField("y");
                TextField x002 = createTextField("x");
                TextField y002 = createTextField("y");
                Button b1 = new Button("Executar");
                b1.setOnAction(e -> {
                    Integer x1 = Integer.valueOf(x001.getText().strip());
                    Integer y1 = Integer.valueOf(y001.getText().strip());

                    Integer x2 = Integer.valueOf(x002.getText().strip());
                    Integer y2 = Integer.valueOf(y002.getText().strip());

                    consoleController.executeAlgorithm(
                            String.format("Bresenham %d %d %d %d",
                                    x1, y1, x2, y2));
                    Transformacoes.bresenham(consoleController, this, malhaController, x1, y1, x2, y2);
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(x002, y002), b1));
                break;
            }
            case ("Circulo"): {
                TextField x001 = createTextField("x");
                TextField y001 = createTextField("y");
                TextField raio = new TextField();
                raio.setPromptText("Raio");

                Button b1 = new Button("Executar");
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
                TextField x001 = createTextField("x_ini");
                TextField y001 = createTextField("y_ini");
                TextField x002 = createTextField("x_fim");
                TextField y002 = createTextField("y_fim");

                Button insert = new Button("Pontos de Controle");
                insert.setOnAction(e -> {
                    Dialog<List<String>> dialog = new Dialog<>();
                    dialog.setTitle("Pontos de Controle");
                    dialog.setHeaderText("Insira os pontos de controle para a curva de Bezier");
                    dialog.setResizable(true);
                    // Set the button types.
                    ButtonType okButton = new ButtonType("Confirmar", ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    HBox ffff = new HBox();
                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(0, 10, 10, 0)); // margins around the whole grid

                    TextField x0001 = createTextField("x");
                    TextField y0001 = createTextField("y");

                    Button insert2 = new Button("+");

                    grid.add(x0001, 0, 0);
                    grid.add(y0001, 1, 0);

                    List<String> inputList = new ArrayList<>();

                    insert2.setOnAction(e2 -> {
                        pcc += 1;
                        TextField pcx = createTextField("x");
                        TextField pcy = createTextField("y");

                        grid.add(pcx, 0, pcc);
                        grid.add(pcy, 1, pcc);
                        inputList.add(String.format("%s, %s;", pcx.getText(), pcy.getText()));
                        dialog.getDialogPane().setContent(ffff);
                    });

                    ffff.getChildren().addAll(grid, insert2);

                    dialog.getDialogPane().setContent(ffff);

                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == okButton) {
                            return inputList;
                        }
                        return null;
                    });
                    // Convert the result to a list of value pairs when the OK button is clicked.

                    Optional<List<String>> result = dialog.showAndWait();

                    result.ifPresent(dialogResult -> {
                        pontoscontrole.clear();
                        List<String> valuePairs = new ArrayList<>();
                        List<Node> children = grid.getChildren();

                        for (int i = 0; i < children.size() - 1; i += 2) {
                            Node node1 = children.get(i);
                            Node node2 = children.get(i + 1);

                            if (node1 instanceof TextField && node2 instanceof TextField) {
                                TextField textField1 = (TextField) node1;
                                TextField textField2 = (TextField) node2;
                                valuePairs.add(String.format("%s,%s", textField1.getText(), textField2.getText()));
                            }
                        }
                        pontoscontrole.addAll(valuePairs);
                    });
                });

                Button b1 = new Button("Executar");

                b1.setOnAction(e -> {
                    pcc = 0;
                    Integer x1 = Integer.valueOf(x001.getText().strip());
                    Integer y1 = Integer.valueOf(y001.getText().strip());

                    Integer x2 = Integer.valueOf(x002.getText().strip());
                    Integer y2 = Integer.valueOf(y002.getText().strip());

                    Set<PontoBasier> pontosControle = new HashSet<>(0);

                    pontosControle.add(new PontoBasier(x1, y1, 0));
                    for (String pontoCo : pontoscontrole) {
                        String[] ponto = pontoCo.split(",");
                        pontosControle.add(new PontoBasier(
                                Integer.parseInt(ponto[0]),
                                Integer.parseInt(ponto[1]),
                                pontosControle.size()));
                    }
                    pontosControle.add(new PontoBasier(x2, y2, pontosControle.size()));
                    consoleController.executeAlgorithm(
                            String.format("CurvaBezier %d %d %d %d",
                                    x1, y1, x2, y2));
                    Transformacoes.desenharCurvaBasier(consoleController, this, malhaController, pontosControle);
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(x002, y002), insert, b1));

                break;
            }
            case ("Polilinha"): {
                break;
            }

            case ("Preenchimento Recursivo"): {
                TextField x001 = createTextField("x");
                TextField y001 = createTextField("y");
                Button b1 = new Button("Executar");

                titledPane.setContent(new VBox(new HBox(x001, y001), b1));
                break;
            }
            case ("Varredura"): {
                break;
            }

            case ("Rotação"): {
                TextField angulo = new TextField();
                TextField pontopivox = createTextField("Pivô X");
                TextField pontopivoy = createTextField("Pivô Y");

                angulo.setPromptText("e.g: 30º");
                Button b1 = new Button("Executar");

                b1.setOnAction(e -> {
                    Double x1 = Double.valueOf(angulo.getText().strip());
                    Double[] y1 = { Double.valueOf(pontopivox.getText().strip()),
                            Double.valueOf(pontopivoy.getText().strip()) };

                    consoleController.executeAlgorithm(
                            String.format("Rotacao %.2f %.2f %.2f",
                                    x1, y1[0], y1[1]));
                    new Thread(() -> Transformacoes.rotacao(consoleController, this, malhaController, -x1, y1)).start();
                });

                titledPane.setContent(new VBox(new HBox(angulo), new HBox(pontopivox, pontopivoy), b1));
                break;
            }

            case ("Translacao"): {
                TextField x001 = createTextField("x");
                TextField y001 = createTextField("y");
                Button b1 = new Button("Executar");

                b1.setOnAction(e -> {
                    Integer x1 = Integer.valueOf(x001.getText().strip());
                    Integer y1 = Integer.valueOf(y001.getText().strip());

                    consoleController.executeAlgorithm(
                            String.format("Translacao %d %d",
                                    x1, y1));
                    Transformacoes.translacao(consoleController, this, malhaController, x1, y1);
                });

                titledPane.setContent(new VBox(new HBox(x001, y001), b1));
                break;
            }
            case ("Escala"): {
                TextField x001 = createTextField("x");
                TextField y001 = createTextField("y");
                TextField pontofixo = new TextField();
                pontofixo.setPromptText("Ponto fixo");
                Button b1 = new Button("Executar");

                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(pontofixo), b1));
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

    private TextField createTextField(String nome) {
        TextField input = new TextField();
        input.setPromptText(nome);
        input.setPrefColumnCount(3);
        input.getStyleClass().add("genericInput");
        return input;
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
