package com.compgt01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Compgt01 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));

        primaryStage.setTitle("CheckBox Experiment 1");
        Pane box = loader.<HBox>load();

        Scene scene = new Scene(box);
        String csslink = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(csslink);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}