package com.compgt01.controller;

import java.util.ArrayList;
import java.util.List;

import com.compgt01.model.MalhaModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

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

    int pcc = 0;

    List<String> pontoscontrole = new ArrayList<>();

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

                Button insert = new Button();

                insert.setOnAction(e -> {
                    Dialog<List<String>> dialog = new Dialog<>();
                    dialog.setTitle("Login Dialog");
                    dialog.setHeaderText("Look, a Custom Login Dialog");
                    dialog.setResizable(true);
                    // Set the button types.
                    ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    GridPane grid = new GridPane();

                    TextField x0001 = new TextField();
                    TextField y0001 = new TextField();
                    x0001.setPromptText("x");
                    y0001.setPromptText("y");
                    Button insert2 = new Button();

                    grid.add(x0001, 0, 0);
                    grid.add(y0001, 1, 0);
                    grid.add(insert2, 2, 0);

                    insert2.setOnAction(e2 -> {
                        pcc += 1;
                        TextField x00001 = new TextField();
                        TextField y00001 = new TextField();
                        x0001.setPromptText("x");
                        y0001.setPromptText("y");
                        grid.add(x00001, 0, pcc);
                        grid.add(y00001, 1, pcc);

                        dialog.getDialogPane().setContent(grid);
                    });
                    dialog.getDialogPane().setContent(grid);

                    // Convert the result to a list of value pairs when the OK button is clicked.

                    Optional<List<String>> result = dialog.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        pontoscontrole.clear();
                        for (int i = 0; i <= pcc; i++) {
                            TextField xField = (TextField) grid.getChildren().get(i * 2);
                            TextField yField = (TextField) grid.getChildren().get(i * 2 + 1);
                            String x00001 = xField.getText();
                            String y00001 = yField.getText();
                            pontoscontrole.add(String.format("%s, %s", x00001, y00001));
                            System.out.println("hi");

                        }
                        return pontoscontrole;
                    }

                    result.ifPresent(valuePairs -> {
                        for (String value : valuePairs) {
                            System.out.println("Value 1: " + value);
                        }
                    });
                });

                Button b1 = new Button();

                b1.setOnAction(e -> {
                    pcc = 0;
                    Integer x01 = Integer.valueOf(x001.getText().strip());
                    Integer y01 = Integer.valueOf(y001.getText().strip());

                    Integer x02 = Integer.valueOf(x002.getText().strip());
                    Integer y02 = Integer.valueOf(y002.getText().strip());

                    // String entered = result.get();

                    Set<PontoBasier> pontosControle = new HashSet<>(0);

                    for (String pontoCo : pontoscontrole) {
                        String ponto[] = pontoCo.split(",");
                        pontosControle.add(new PontoBasier(Integer.parseInt(ponto[0]),
                                Integer.parseInt(ponto[1]),
                                pontosControle.size()));
                        consoleController.executeAlgorithm(
                                String.format("CurvaBezier %d %d %d %d",
                                        x01, y01, x02, y02));
                        Transformacoes.desenharCurvaBasier(consoleController, this, malhaController, pontosControle);

                    }
                });
                titledPane.setContent(new VBox(new HBox(x001, y001), new HBox(x002, y002), insert, b1));

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
