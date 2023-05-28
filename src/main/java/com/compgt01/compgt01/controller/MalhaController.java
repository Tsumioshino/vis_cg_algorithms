package com.compgt01.compgt01.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.compgt01.compgt01.model.MalhaModel;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 *
 * @author mmo
 */
public class MalhaController {

    @FXML
    private HBox root2;

    @FXML
    private TilePane tilepane;

    public void setTilepane(TilePane tilepane) {
        this.tilepane = tilepane;
    }

    public TilePane getTilepane() {
        return tilepane;
    }

    private int defaultx = 3;

    private int defaulty = 3;

    private MalhaModel malhaModel;

    public MalhaModel getMalhaModel() {
        return malhaModel;
    }

    public void setMalhaModel(MalhaModel malhaModel) {
        if (this.malhaModel == null) {
            this.malhaModel = malhaModel;
        }
    }

    static int[] calculate(String coordenada) {

        int[] result = new int[2];

        int commaLoc = coordenada.strip().indexOf(",");

        result[0] = Integer.parseInt(coordenada.substring(commaLoc + 2, coordenada.length() - 1));
        result[1] = Integer.parseInt(coordenada.substring(1, commaLoc));

        return result;
    }

    public void initializeBase() {
        for (int i = 0; i < defaultx; i++) {
            ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(new Integer[defaultx]));
            Collections.fill(myList, 0);
            getMalhaModel().getMatrixpixel().add(myList);
            ArrayList<CheckBox> myList2 = new ArrayList<>(defaulty);

            for (int j = 0; j < defaulty; j++) {
                myList2.add(new CheckBox());
            }
            getMalhaModel().getCoordinates().add(myList2);
        }
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

        tile.setSelected(getMalhaModel().getMatrixpixel().get(y).get(x) == 1);
        tile.selectedProperty().addListener((observable, oldValue, newValue) -> {
            int[] coord = calculate(tile.getId());
            getMalhaModel().getMatrixpixel().get(coord[0]).set(coord[1], newValue ? 1 : 0);
        });

        if (n) {
            getMalhaModel().getCoordinates().get(y).add(x, tile);
        }
        return tile;
    }

    public int getAbsoluteTilePosition(int x, int y) {
        int acumulo = 0;
        int rowsize = getMalhaModel().getMatrixpixel().get(0).size();
        // (10, 9) should return 10
        for (int i = 0; i <= y; i++) {
            if (i != y) {
                acumulo += rowsize;
                continue;
            }
            for (int j = 0; j < x; j++) {
                acumulo += 1;
            }
        }
        return acumulo;

    }

    public int getAbsoluteTilePosition2(int x, int y) {
        int acumulo = 0;
        int rowsize = getMalhaModel().getMatrixpixel().get(0).size();

        for (int i = getMalhaModel().getMatrixpixel().size() - 1; i > y; i--) {
            acumulo += rowsize;
        }

        acumulo += x;

        return acumulo;
    }

    public void reajusteTP(TilePane tP, int ov, int nv) {
        for (int i = 0; i < getMalhaModel().getMatrixpixel().size(); i++) {
            for (int j = ov; j < nv; j++) {
                CheckBox tile2 = this.createTile(j, i, false);
                tile2.setId(String.format("(%d, %d)", j, i));

                tP.getChildren().add(this.getAbsoluteTilePosition(j, i), tile2);
            }
        }
    }

    public HBox tudoReferenteAosBlocos() {
        // cria a malha
        TilePane tilePane = new TilePane();

        tilePane.setId("basePontos");

        for (int i = defaultx - 1; i >= 0; i--) {
            for (int j = 0; j < defaulty; j++) {
                CheckBox tile = createTile(j, i, true);
                tile.setId(String.format("(%d, %d)", j, i));
                tilePane.getChildren().add(tile);
            }
        }
        this.makeAnythingDraggable(tilePane);

        tilePane.setPrefColumns(defaultx);
        tilePane.setPrefRows(defaulty);

        setTilepane(tilePane);

        HBox rand1 = new HBox();

        rand1.getChildren().add(tilePane);

        SubScene sub1 = new SubScene(rand1, 1000, 1000);

        HBox rand = new HBox();

        rand.getChildren().add(sub1);

        tilepane = tilePane;

        return rand;
    }

    public void criarTileGambiarra(int ov, int nv) {

        for (int i = getMalhaModel().getMatrixpixel().size() - 1; i >= 0; i--) {
            for (int j = ov; j < nv; j++) {
                CheckBox tile2 = this.createTile(j, i, true);
                tile2.setId(String.format("(%d, %d)", j, i));

                getTilepane().getChildren().set(
                        this.getAbsoluteTilePosition2(j, i),
                        tile2);

            }
        }
    }

    public void addColumnX(int ov, int nv) {
        getMalhaModel().setX(nv);

        System.out.println(getTilepane().getChildren());
        System.out.println(getMalhaModel().getMatrixpixel());

        reajusteMatrizBase(nv, 0);
        reajusteTP(getTilepane(), ov, nv);

        getTilepane().setPrefColumns(nv);

        System.out.println(getTilepane().getChildren());
        System.out.println(getMalhaModel().getMatrixpixel());

        criarTileGambiarra(ov, nv);
    }

    public void removeColumnX(int ov, int nv) {
        getMalhaModel().setX(nv);

        getTilepane().setPrefColumns(nv);

        for (int i = 0; i < getMalhaModel().getMatrixpixel().size(); i++) {
            for (int j = ov - 1; j > nv - 1; j--) {
                System.out.println(getAbsoluteTilePosition2(j, i));
                System.out.println(getTilepane());

                getTilepane().getChildren().remove(
                        getAbsoluteTilePosition2(j, i));
                getMalhaModel().getCoordinates().get(i).remove(j);
                getMalhaModel().getMatrixpixel().get(i).remove(j);

                System.out.println(getTilepane().getChildren());
                System.out.println(getMalhaModel().getMatrixpixel());

            }
        }
    }

    public void addRowY(int ov, int nv) {
        getMalhaModel().setY(nv);

        reajusteMatrizBase(nv, 0);
        reajusteTP(getTilepane(), ov, nv);

        getTilepane().setPrefRows(nv);

        criarTileGambiarra(ov, nv);
    }

    public void removeRowY(int ov, int nv) {
        getMalhaModel().setY(nv);

        reajusteMatrizBase(nv, 0);
        reajusteTP(getTilepane(), ov, nv);

        getTilepane().setPrefRows(nv);

        criarTileGambiarra(ov, nv);
    }

    public void reajusteMatrizBase(int x, int y) {
        if (x > 0) {
            int basej = getMalhaModel().getMatrixpixel().get(0).size();
            for (int i = 0; i < getMalhaModel().getMatrixpixel().size(); i++) {
                for (int j = basej; j < x; j++) { // a | b | c | [adiciona no fim 3 0 caso tenha aumentado 3]
                    getMalhaModel().getCoordinates().get(i).add(j, new CheckBox());
                    getMalhaModel().getMatrixpixel().get(i).add(j, 0);
                }
            }
        }

        if (y > 0) {
            int basei = getMalhaModel().getMatrixpixel().size();
            for (int i = basei; i < y; i++) {

                ArrayList<Integer> myList = new ArrayList<>(
                        Arrays.asList(new Integer[getMalhaModel().getMatrixpixel().get(0).size()]));
                Collections.fill(myList, 0);// fills all 10 entries with 0"
                getMalhaModel().getMatrixpixel().add(basei, myList);
            }
        }
    }

}
