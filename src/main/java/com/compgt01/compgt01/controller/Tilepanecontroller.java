package com.compgt01.compgt01.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.util.Pair;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;

/**
 *
 * @author mmo
 */
public class Tilepanecontroller implements Initializable {

    @FXML
    private HBox root;

    //  Map<String, CheckBox> cut_coordinates = new HashMap<>();
    Map<String, CheckBox> coordinates = new HashMap<>();

    List<String> algorithms = new ArrayList<>(
            Arrays.asList("Bresenham",
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
    private int tiles_q = 10;

    // Representa as coordenadas para manipulação matemática.
    // -> 0, caso pixel não pintado. 1, caso pintado.
    List<List<Integer>> coordinates0 = new ArrayList<>();
    
    static int[] calculate(String coordenada){
        char[] chars = coordenada.toCharArray();
        int[] result = new int[2];
        
        int x = Character.getNumericValue(chars[1]);
        int y = Character.getNumericValue(chars[4]);
        
        result[0] = x;
        result[1] = y;
        
        return result;
   }
    
    public void initialize_base() {
        // acho que vai dar ruim pq e objeto
        for (int i = 0; i < tiles_q; i++) {
            ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(new Integer[tiles_q]));
            Collections.fill(myList, 0);//fills all 10 entries with 0"
            coordinates0.add(myList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.initialize_base();
        BorderPane p1 = new BorderPane();

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        Button submit = new Button("Submit");
        Button clean = new Button("Clean");

        submit.setOnAction(e -> {
            //Retrieving data
            String x1 = t1.getText().strip();
            String y1 = t2.getText().strip();
            coordinates.
                    get(String.format("(%s, %s)", x1, y1)).
                    setSelected(true);
        });

        clean.setOnAction(e -> {
            coordinates.forEach((key, value) -> {
                value.setSelected(false);
            });
        });

        t1.setPrefColumnCount(3);
        t2.setPrefColumnCount(3);

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(algorithms);

        choiceBox.setOnAction(e -> {
            // should call some method to choose which algorithm
            System.out.println(choiceBox.getValue());
        });

        VBox rrr = new VBox(t1, t2, submit, clean, choiceBox, p1);
        HBox raand = this.tudoReferenteAosBlocos();

        TitledPane tt1 = new TitledPane("", rrr);

        StackPane sp = new StackPane(raand, tt1);

        root.getChildren().add(sp);
    }

    private double startX;
    private double startY;

    public void moveTilePane(TilePane tilePane) {
        tilePane.setOnMousePressed(e -> {
            startX = e.getSceneX();
            startY = e.getSceneY();
        });

        tilePane.setOnMouseDragged(e -> {
            tilePane.setTranslateX(e.getSceneX() - startX);
            tilePane.setTranslateY(e.getSceneY() - startY);
        });
    }

    public HBox tudoReferenteAosBlocos() {

        // cria a malha
        TilePane tilePane = new TilePane();
        tilePane.setId("basePontos");

        List<CheckBox> tiles = new ArrayList<>();

        for (int i = tiles_q - 1; i >= 0; i--) {
            for (int j = 0; j < tiles_q; j++) {
                String coordenada = String.format("(%d, %d)", j, i);
                
                CheckBox tile = new CheckBox();
                tile.setId(coordenada);
                tile.getStyleClass().add("selectedCheckBox");
                
                
                tile.setSelected(coordinates0.get(j).get(i) == 1);
                               
                tile.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    int[] coord = calculate(tile.getId());
                    coordinates0.get(coord[0]).set(coord[1], newValue? 1 : 0); 
                    System.out.println(coordinates0);
                });
                
                tiles.add(tile);
                coordinates.put(coordenada, tile);
            }
        }
        tilePane.getChildren().addAll(
                tiles
        );

        this.moveTilePane(tilePane);

        // caixa dos controles
        Slider a = new Slider(1, 30, 10);
        Slider b = new Slider(1, 30, 10);
                
        VBox sizingVBox = new VBox(
                new Label("x"),
                a,
                new Label("y"),
                b);

        Scale scale_down = new Scale(0.75, 0.75, 0, 0);
        Scale scale = new Scale(1.25, 1.25, 0, 0);

        a.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                List<CheckBox> tiles2 = new ArrayList<>();
                for (int i = ov; i < nv; i++) {
                    for (int j = ov; j < nv; j++) {
                        String coordenada = String.format("(%d, %d)", j, i);
                        tilePane.setPrefColumns(nv);
                        CheckBox tile = new CheckBox();
                        tile.setId(coordenada);
                        tile.getStyleClass().add("selectedCheckBox");
                        tiles2.add(tile);
                        coordinates.put(coordenada, tile);
                    }
                }
                tilePane.getChildren().addAll(
                        tiles2
                );
            } else if (nv < ov) {
                for (int i = ov; i > nv; i--) {
                    for (int j = ov; j > nv; j--) {
                        String coordenada = String.format("(%d, %d)", j, i);
                        CheckBox tile = new CheckBox();
                        tile.setId(coordenada);
                        tilePane.getChildren().remove(tile);
                        coordinates.remove(coordenada);
                    }
                }
            }
        });

        b.valueProperty().addListener((observable, oldValue, newValue) -> {
            Double ov = oldValue.doubleValue();
            Double nv = newValue.doubleValue();
            if (nv > ov) {
                tilePane.getTransforms().add(scale);
            } else if (nv < ov) {
                tilePane.getTransforms().add(scale_down);
            }
        });
        /*ZoomEvent zoom = new ZoomEvent(
                ZoomEvent.ANY,
                200, // !
                200,
                200,
                200,
                false,
                false,
                false,
                false,
                false,
                false,
                1.5,
                8,
                null);*/

        Slider x = new Slider(1, 5, 3);
        x.setShowTickLabels​(true);
        x.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                tilePane.getTransforms().add(scale);
            } else if (nv < ov) {
                tilePane.getTransforms().add(scale_down);
            }
        });

        VBox redimensionVBox = new VBox(
                new Label("Zoom"),
                x
        );

        TitledPane sizecontrol = new TitledPane(
                "Controle de Tamanho",
                sizingVBox
        );

        TitledPane redimensioncontrol = new TitledPane(
                "Controle de Zoom",
                redimensionVBox
        );

        sizecontrol.setExpanded(false);
        redimensioncontrol.setExpanded(true);

        VBox controlVBox = new VBox(sizecontrol, redimensioncontrol);
        //VBox controlVBox = new VBox();

        tilePane.setPrefColumns(tiles_q);
        tilePane.setPrefRows(tiles_q);

        HBox rand1 = new HBox();

        rand1.getChildren().add(tilePane);

        SubScene sub1 = new SubScene(rand1, 1000, 1000);

        HBox rand = new HBox();

        rand.getChildren().addAll(controlVBox, sub1);

        return rand;
    }
}
