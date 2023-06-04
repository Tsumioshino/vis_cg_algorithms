package com.compgt01.controller;

import java.util.LinkedList;
import java.util.List;

import com.compgt01.model.CheckBoxXY;
import com.compgt01.model.MalhaModel;
import com.compgt01.model.Ponto;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author mmo
 */
public class MalhaController {

    @FXML
    private HBox root2;

    @FXML
    private GridPane gridPane = new GridPane();

    private List<Ponto> pontosClicados = new LinkedList<>();

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public GridPane getGridPane() {

        return this.gridPane;
    }

    private MalhaModel malhaModel;

    public MalhaModel getMalhaModel() {
        return malhaModel;
    }

    public void setMalhaModel(MalhaModel malhaModel) {
        this.malhaModel = malhaModel;
    }

    static int[] calculate(String coordenada) {

        int[] result = new int[2];

        int commaLoc = coordenada.strip().indexOf(",");

        result[0] = Integer.parseInt(coordenada.substring(commaLoc + 2, coordenada.length() - 1));
        result[1] = Integer.parseInt(coordenada.substring(1, commaLoc));

        return result;
    }

    public HBox initializeMalha() {
        // cria a malha
        GridPane gridpane = new GridPane();
        // gridPane.setScaleX(-1);
        // gridPane.setScaleY(-1);
        // gridPane.rotate
        gridpane.setId("basePontos");

        getMalhaModel()
                .setGridCheckBox(new CheckBox[(getMalhaModel().getX() * 2) + 1][(getMalhaModel().getY() * 2) + 1]);

        for (int x = 0; x < (getMalhaModel().getX() * 2) + 1; x++) {
            for (int y = 0; y < (getMalhaModel().getY() * 2) + 1; y++) {
                getMalhaModel().getGridCheckBox()[x][y] = createTile(x - getMalhaModel().getX(), getMalhaModel()
                        .getY() - y);
                getMalhaModel().getGridCheckBox()[x][y].addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                    CheckBoxXY checkBoxXY = (CheckBoxXY) event.getSource();
                    pontosClicados.add(checkBoxXY.getPonto());
                    System.out.println(checkBoxXY.getPonto());
                    System.out.println(String.format("Real Ponto %d %d", checkBoxXY.getPonto().getX() + getMalhaModel()
                            .getX(),
                            getMalhaModel().getY() - checkBoxXY.getPonto().getY()));
                });
                gridpane.add(getMalhaModel().getGridCheckBox()[x][y], x, y);
            }
        }

        this.makeAnythingDraggable(gridpane);

        setGridPane(gridpane);

        HBox container1 = new HBox();
        container1.getChildren().add(gridpane);

        container1.setFillHeight(false);
        return container1;
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

    public CheckBoxXY createTile(int x, int y) {

        CheckBoxXY tile = new CheckBoxXY(new Ponto(x, y));
        tile.setId(String.format("(%d, %d)", x, y));

        tile.getStyleClass().add("selectedCheckBox");

        return tile;
    }

    public List<Ponto> getPontosClicados() {
        return pontosClicados;
    }

    public void setPontosClicados(List<Ponto> pontosClicados) {
        this.pontosClicados = pontosClicados;
    }

    public Ponto getFistPonto() {
        return this.pontosClicados.get(0);
    }
}

