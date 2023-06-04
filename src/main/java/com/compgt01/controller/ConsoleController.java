package com.compgt01.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 *
 * @author mmo
 */
public class ConsoleController {

    @FXML
    private TextArea console;

    public void executeAlgorithm(String algorithm) {
        console.setWrapText(true);
        console.appendText("> ./" + algorithm + "\n");
    }

    public void redirectToConsole(String text) {
        console.setWrapText(true);
        console.appendText(text + "\n");
    }

    public void clearConsole() {
        console.clear();
    }
}
