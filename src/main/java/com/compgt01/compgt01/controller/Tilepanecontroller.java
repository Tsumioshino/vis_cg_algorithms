package com.compgt01.compgt01.controller;



import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 *
 * @author mmo
 */
public class Tilepanecontroller implements Initializable { 
 
    @FXML
    private HBox root;

    Map<String, CheckBox> cut_coordinates = new HashMap<>();
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
    private int tiles_q = 20;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
            coordinates.forEach( (key, value) -> { value.setSelected(false); });
        });

        t1.setPrefColumnCount(3);    
        t2.setPrefColumnCount(3);
        
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(algorithms);
        
        choiceBox.setOnAction(e -> {
            // should call some method to choose which algorithm
            System.out.println(choiceBox.getValue());
        });
              
        Button increasetile = new Button("+");
        Button decreasetile = new Button("-");
        
        increasetile.setOnAction( event -> tiles_q += 1 );
        decreasetile.setOnAction( event -> tiles_q -= 1 );

        // main tiles
        TilePane tilePane = new TilePane();
        tilePane.setId("basePontos");
        tilePane.setPrefColumns(tiles_q);
        tilePane.setPrefRows(tiles_q);
        
        // cut tiles
        TilePane tilePane2 = new TilePane(Orientation.VERTICAL);
        tilePane2.setId("cutPontos");
        tilePane2.setPrefColumns(tiles_q);
        tilePane2.setPrefRows(tiles_q);

        List<CheckBox> tiles = new ArrayList<>();
        List<CheckBox> tiles2 = new ArrayList<>();

        for (int i = tiles_q - 1; i >= 0; i--) {
            for (int j = 0; j < tiles_q; j++) {
                CheckBox tile = new CheckBox();
                tile.getStyleClass().add("selectedCheckBox");
                tiles.add(tile);
                coordinates.put(String.format("(%d, %d)", i, j), tile);
            }
            CheckBox tile0 = new CheckBox();
            tile0.getStyleClass().add("selectedCutBox");
            tiles2.add(tile0);
            cut_coordinates.put(String.format("(%d)", i), tile0);
        }
        tilePane.getChildren().addAll(
               tiles
        );

        tilePane2.getChildren().addAll(
               tiles2
        );
        
        
        
        root.getChildren().add(t1);
        root.getChildren().add(t2);
        root.getChildren().add(submit);
        root.getChildren().add(clean);
        
        root.getChildren().add(choiceBox);

        root.getChildren().add(increasetile);
        root.getChildren().add(decreasetile);

        root.getChildren().addAll(tilePane2);
        root.getChildren().addAll(tilePane);
    }
}
