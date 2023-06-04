package com.compgt01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneExperiments extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GridPane Experiment");

        CheckBox[][] testeMatriz = new CheckBox[10][10];

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testeMatriz[i][j] = new CheckBox();
                gridPane.add(testeMatriz[i][j], i, j);
            }
        }

        Scene scene = new Scene(gridPane, 240, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}