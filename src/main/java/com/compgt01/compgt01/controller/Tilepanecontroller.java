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
import javafx.scene.Node;
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
    List<List<CheckBox>> coordinates = new ArrayList<>();

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
        private int total = 100;


    // Representa as coordenadas para manipulação matemática.
    // -> 0, caso pixel não pintado. 1, caso pintado.
    List<List<Integer>> matrixpixel = new ArrayList<>();

    static int[] calculate(String coordenada) { 

        int[] result = new int[2];
        
        int commaLoc = coordenada.strip().indexOf(",");

        result[0] = Integer.parseInt(coordenada.substring(commaLoc + 2, coordenada.length()-1));
        result[1] = Integer.parseInt(coordenada.substring(1,commaLoc));

        return result;
    }

    public void initialize_base() {
        // acho que vai dar ruim pq e objeto
        for (int i = 0; i < tiles_q; i++) {
            ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(new Integer[tiles_q]));
            Collections.fill(myList, 0);//fills all 10 entries with 0"
            matrixpixel.add(myList);
            ArrayList<CheckBox> myList2 = new ArrayList<>(tiles_q);

            for (int j = 0; j < tiles_q; j++) {

                myList2.add(new CheckBox());
            }
            coordinates.add(myList2);
        }
        System.out.println(matrixpixel);

        System.out.println(coordinates);
    }

    private void reajusteMatrizBase(int x, int y) {
        if (x > 0) {
            int basej = matrixpixel.get(0).size();
            for (int i = 0; i < matrixpixel.size(); i++) {
                for (int j = basej; j < x; j++) {
                    matrixpixel.get(i).add(j, 0);
                    coordinates.get(i).add(j, new CheckBox());

                }
            }
        }
        //todo

        if (y > 0) {
            int basei = matrixpixel.size();
            for (int i = basei; i < y; i++) {

                ArrayList<Integer> myList = new ArrayList<>(
                        Arrays.asList(new Integer[matrixpixel.get(0).size()])
                );
                Collections.fill(myList, 0);//fills all 10 entries with 0"
                matrixpixel.add(basei, myList);
            }
        }

    }

    private void reajusteMatrizBase2(int x, int y) {
        if (x > 0) {
            int basej = matrixpixel.get(0).size() - (matrixpixel.get(0).size() - x);
            for (int i = 0; i < matrixpixel.size(); i++) {
                for (int j = basej; j < x; j++) {
                    matrixpixel.get(i).remove(j);

                }
            }
        }
        //todo
        if (y > 0) {
            int basei = matrixpixel.size();
            for (int i = basei; i < y; i++) {

                ArrayList<Integer> myList = new ArrayList<>(
                        Arrays.asList(new Integer[matrixpixel.get(0).size()])
                );
                Collections.fill(myList, 0);//fills all 10 entries with 0"
                matrixpixel.add(basei, myList);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.initialize_base();

        BorderPane p1 = new BorderPane();

        TextField textx = new TextField();
        TextField texty = new TextField();

        Button submit = new Button("Submit");
        Button clean = new Button("Clean");

        submit.setOnAction(e -> {
            //Retrieving data
            Integer x1 = Integer.valueOf(textx.getText().strip());
            Integer y1 = Integer.valueOf(texty.getText().strip());

            coordinates.
                    get(y1).
                    get(x1).
                    setSelected(true);
        });

        clean.setOnAction(e -> {
            coordinates.forEach(row -> {
                row.forEach(value -> {
                    value.setSelected(false);
                });
            });
        });

        textx.setPrefColumnCount(3);
        texty.setPrefColumnCount(3);

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(algorithms);

        choiceBox.setOnAction(e -> {
            // should call some method to choose which algorithm
            System.out.println(choiceBox.getValue());
        });

        VBox rrr = new VBox(textx, texty, submit, clean, choiceBox, p1);
        HBox raand = this.tudoReferenteAosBlocos();

        TitledPane tt1 = new TitledPane("", rrr);

        StackPane sp = new StackPane(raand, tt1);

        root.getChildren().add(sp);
    }

    private double startX;
    private double startY;

    public void makeAnythingDraggable(Node elemento) {
        elemento.setOnMousePressed(e -> {
            startX = e.getSceneX();
            startY = e.getSceneY();
        });

        elemento.setOnMouseDragged(e -> {
            elemento.setTranslateX(e.getSceneX() - startX);
            elemento.setTranslateY(e.getSceneY() - startY);
        });
    }

    public CheckBox createTile(int x, int y, boolean n) {

        CheckBox tile = new CheckBox();
        tile.setId(String.format("(%d, %d)", x, y));

        tile.getStyleClass().add("selectedCheckBox");

        tile.setSelected(matrixpixel.get(y).get(x) == 1);
        tile.selectedProperty().addListener((observable, oldValue, newValue) -> {
            int[] coord = calculate(tile.getId());
            System.out.println(tile.getId());

            matrixpixel.get(coord[0]).set(coord[1], newValue ? 1 : 0);
                    System.out.println(matrixpixel);

        });
        
        if (n) {
        coordinates.get(y).add(x, tile);
        }
        return tile;
    }

    private int getAbsoluteTilePosition(int x, int y) {
        int acumulo = 0;
        int rowsize = matrixpixel.get(0).size();
        // (10, 9) should return 10
        for (int i = 0; i <= y; i++) {
            if (!(i == y)) {
                acumulo += rowsize;
                continue;
            }
            for (int j = 0; j < x; j++) {
                acumulo += 1;
            }
        }
        return acumulo;

    }
    
        private int getAbsoluteTilePosition2(int x, int y) {
        int acumulo = 0;
        int rowsize = matrixpixel.get(0).size();
        // (10, 9) should return 10// 10x11 -- 11, 10
        for (int i = matrixpixel.size()-1; i >= y; i--) {
            if (!(i == y)) {
                acumulo += rowsize;
                continue;
            }
            for (int j = 0; j < x; j++) {
                acumulo += 1;
            }
        }
        return acumulo;

    }
        
    private void reajusteTP(TilePane tP, int ov, int nv) {
                for (int i = 0; i < matrixpixel.size(); i++) {
                    for (int j = ov; j < nv; j++) {
                        CheckBox tile2 = this.createTile(j, i, false);
                        tile2.setId(String.format("(%d, %d)", j, i));
                        
                        System.out.println(this.getAbsoluteTilePosition(j, i));

                        //xxx.add(this.getAbsoluteTilePosition(j, i));
                        tP.getChildren().add(this.getAbsoluteTilePosition(j, i), tile2);
                        
                        // tilePane.getChildren().add(n+m, tile2);
                    }
                }

    }

    public HBox tudoReferenteAosBlocos() {

        // cria a malha
        TilePane tilePane = new TilePane();
        tilePane.setId("basePontos");

        for (int i = tiles_q - 1; i >= 0; i--) {
            for (int j = 0; j < tiles_q; j++) {
                CheckBox tile = createTile(j, i, true);
                tile.setId(String.format("(%d, %d)", j, i));
                tilePane.getChildren().add(tile);
            }
        }

        this.makeAnythingDraggable(tilePane);

        // caixa dos controles
        Slider addcolumnx = new Slider(1, 12, 10);
        Slider addrowy = new Slider(1, 12, 10);

        VBox sizingVBox = new VBox(
                new Label("x"),
                addcolumnx,
                new Label("y"),
                addrowy);

        Scale scaledown = new Scale(0.75, 0.75, 0, 0);
        Scale scale = new Scale(1.25, 1.25, 0, 0);

        addcolumnx.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                if (tiles_q >= nv) {
                    return;
                }

                tiles_q = nv;
                this.reajusteMatrizBase(nv, 0);
                this.reajusteTP(tilePane, ov, nv);

                total = nv*this.matrixpixel.size();
                tilePane.setPrefColumns(nv);
                
                for (int i = matrixpixel.size()-1; i >= 0; i--) {
                    for (int j = ov; j < nv; j++) {
                        CheckBox tile2 = this.createTile(j, i, true);
                        tile2.setId(String.format("(%d, %d)", j, i));
                        
                        System.out.println(this.getAbsoluteTilePosition(j, i));
                        System.out.println(total-this.getAbsoluteTilePosition(j, i));

                        //xxx.add(this.getAbsoluteTilePosition(j, i));
                        tilePane.getChildren().set(
                                this.getAbsoluteTilePosition2(j, i),
                                tile2);
                        
                        // tilePane.getChildren().add(n+m, tile2);
                    }
                }

            } else if (nv < ov) {
                tiles_q = nv;

                this.reajusteMatrizBase2(nv, 0);

                for (int i = ov-1; i >= 0; i--) {
                    for (int j = ov-1; j > nv-1; j--) {
                                                System.err.println(j);
                                                 System.err.println(i);


                        tilePane.getChildren().remove(
                                this.getAbsoluteTilePosition(j, i)
                        );
                        System.err.println("f1");
                        coordinates.get(i).remove(j);
                        System.err.println("f2");

                    }
                }
            }
        });

        addrowy.valueProperty().addListener((observable, oldValue, newValue) -> {
            Double ov = oldValue.doubleValue();
            Double nv = newValue.doubleValue();
            if (nv > ov) {
                tilePane.getTransforms().add(scale);
            } else if (nv < ov) {
                tilePane.getTransforms().add(scaledown);
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

        Slider scalezoom = new Slider(1, 5, 3);
        scalezoom.setShowTickLabels​(true);
        scalezoom.valueProperty().addListener((observable, oldValue, newValue) -> {
            Integer ov = oldValue.intValue();
            Integer nv = newValue.intValue();
            if (nv > ov) {
                tilePane.getTransforms().add(scale);
            } else if (nv < ov) {
                tilePane.getTransforms().add(scaledown);
            }
        });

        VBox redimensionVBox = new VBox(
                new Label("Zoom"),
                scalezoom
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
