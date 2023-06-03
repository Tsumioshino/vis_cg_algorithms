package com.compgt01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Compgt01 extends Application {

    private final int PREF_MIN_WIDTH = 1000;
    private final int PREF_MIN_HEIGHT = 1000;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));

        primaryStage.setTitle("CheckBox Experiment 1");
        Pane box = loader.<BorderPane>load();

        Scene scene = new Scene(box, PREF_MIN_WIDTH, PREF_MIN_HEIGHT);
        String csslink = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(csslink);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}