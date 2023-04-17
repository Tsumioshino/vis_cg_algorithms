package com.compgt01.compgt01;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class Compgt01 extends Application {
    //private static final Logger logger = System.getLogger("MAIN");
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        // Rotate camera to use Y up.
//        Camera camera = new PerspectiveCamera();
//        camera.setRotationAxis(Rotate.Z_AXIS);
//        camera.setRotate(180.0);

        // Rotate scene content for correct drawing.
//        Group yUp = new Group();
//        yUp.setRotationAxis(Rotate.Z_AXIS);
//        yUp.setRotate(180.0);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));
      
        final ColorPicker colorPicker = new ColorPicker();    
        
         colorPicker.setOnAction(e -> {
            Color c = colorPicker.getValue();
            System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
        });
         
        primaryStage.setTitle("CheckBox Experiment 1");
        Pane box = loader.<TilePane>load();

        Scene scene = new Scene(box);
        String csslink = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(csslink);
        
                scene.setFill(Color.web("#ccffcc"));
                
        primaryStage.setScene(scene);
        primaryStage.setResizable (false);
        primaryStage.show();
    }
}