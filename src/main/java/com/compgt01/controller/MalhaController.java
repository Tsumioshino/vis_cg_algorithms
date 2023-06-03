package com.compgt01.controller;

import com.compgt01.model.MalhaModel;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
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

    public void setTilepane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public GridPane getTilepane() {
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
        GridPane gridPane = new GridPane();
        //gridPane.setScaleX(-1);
        //gridPane.setScaleY(-1);
        //gridPane.rotate
        gridPane.setId("basePontos");

        // gridPane.setPrefColumns(getMalhaModel().getX() * 2 + 1);
        // gridPane.setPrefRows(getMalhaModel().getY() * 2 + 1);
        getMalhaModel().setGridCheckBox(new CheckBox[(getMalhaModel().getX() * 2) + 1][(getMalhaModel().getY() * 2) + 1]);
        
        for (int x = 0; x < (getMalhaModel().getX() * 2) + 1; x++) {
            for (int y = 0; y < (getMalhaModel().getY() * 2) + 1; y++) {
                getMalhaModel().getGridCheckBox()[x][y] = createTile(x, y);
                gridPane.add(getMalhaModel().getGridCheckBox()[x][y], x, y);
            }
        }

        // for (int i = getMalhaModel().getX(); i >= -getMalhaModel().getX(); i--) {
        //     for (int j = -getMalhaModel().getY(); j <= getMalhaModel().getY(); j++) {
        //         CheckBox tile = createTile(j, i, true);
        //         gridPane.getChildren().add(tile);
        //     }
        // }

        this.makeAnythingDraggable(gridPane);

        setTilepane(gridPane);

        HBox container1 = new HBox();
        container1.getChildren().add(gridPane);

        container1.setFillHeight(false);

        //SubScene sub1 = new SubScene(container1, 10000, 10000);

        //HBox scenecontainer = new HBox();
        //scenecontainer.getChildren().add(sub1);

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

    public CheckBox createTile(int x, int y) {

        CheckBox tile = new CheckBox();
        tile.setId(String.format("(%d, %d)", x, y));

        tile.getStyleClass().add("selectedCheckBox");

        return tile;
    }

    // public int getAbsoluteTilePosition(int x, int y) {
    //     int acumulo = 0;
    //     int rowsize = getMalhaModel().getMatrixpixel().get(0).size();
    //     // (10, 9) should return 10
    //     for (int i = 0; i <= y; i++) {
    //         if (i != y) {
    //             acumulo += rowsize;
    //             continue;
    //         }
    //         for (int j = 0; j < x; j++) {
    //             acumulo += 1;
    //         }
    //     }
    //     return acumulo;

    // }

    // public int getAbsoluteTilePosition2(int x, int y) {
    //     int acumulo = 0;
    //     int rowsize = getMalhaModel().getMatrixpixel().get(0).size();

    //     for (int i = getMalhaModel().getMatrixpixel().size() - 1; i > y; i--) {
    //         acumulo += rowsize;
    //     }

    //     acumulo += x;

    //     return acumulo;
    // }

    // public void reajusteTP(TilePane tP, int ov, int nv) {
    //     for (int i = 0; i < getMalhaModel().getMatrixpixel().size(); i++) {
    //         for (int j = ov; j < nv; j++) {
    //             CheckBox tile2 = createTile(j, i, false);
    //             tile2.setId(String.format("(%d, %d)", j, i));

    //             tP.getChildren().add(getAbsoluteTilePosition(j, i), tile2);
    //         }
    //     }
    // }

    // public void reajusteTP2(TilePane tP, int ov, int nv) {
    //     for (int i = ov; i < nv; i++) {
    //         for (int j = 0; j < getMalhaModel().getMatrixpixel().get(0).size(); j++) {
    //             CheckBox tile2 = createTile(j, i, false);
    //             tile2.setId(String.format("(%d, %d)", j, i));

    //             tP.getChildren().add(getAbsoluteTilePosition(j, i), tile2);
    //         }
    //     }
    // }

    // public void criarTileGambiarra(int ov, int nv) {

    //     for (int i = getMalhaModel().getX() - 1; i >= -getMalhaModel().getX(); i--) {
    //         for (int j = ov; j < nv; j++) {
    //             CheckBox tile2 = createTile(j, i, true);
    //             tile2.setId(String.format("(%d, %d)", j, i));

    //             getTilepane().getChildren().set(
    //                     getAbsoluteTilePosition2(j, i),
    //                     tile2);

    //         }
    //     }
    // }

    // public void criarTileGambiarra2(int ov, int nv) {

    //     for (int i = ov; i < nv; i++) {
    //         for (int j = -getMalhaModel().getY(); j <= getMalhaModel().getY(); j++) {
    //             CheckBox tile2 = createTile(j, i, true);
    //             tile2.setId(String.format("(%d, %d)", j, i));

    //             getTilepane().getChildren().set(
    //                     getAbsoluteTilePosition2(j, i),
    //                     tile2);

    //         }
    //     }
    // }

    // public void addColumnX(int ov, int nv) {
    //     getMalhaModel().setX(nv);
    //     getTilepane().setPrefColumns(nv * 2 + 1);

    //     reajusteMatrizBase(nv, 0);
    //     reajusteTP(getTilepane(), ov, nv);

    //     criarTileGambiarra(ov, nv);
    // }

    // public void removeColumnX(int ov, int nv) {
    //     getMalhaModel().setX(nv);

    //     getTilepane().setPrefColumns(nv);

    //     for (int i = 0; i < getMalhaModel().getMatrixpixel().size(); i++) {
    //         for (int j = ov - 1; j > nv - 1; j--) {
    //             getTilepane().getChildren().remove(
    //                     getAbsoluteTilePosition2(j, i));
    //             getMalhaModel().getCoordinates().get(i).remove(j);
    //             getMalhaModel().getMatrixpixel().get(i).remove(j);
    //         }
    //     }
    // }

    // public void addRowY(int ov, int nv) {
    //     getMalhaModel().setY(nv);
    //     getTilepane().setPrefRows(nv);

    //     reajusteMatrizBase(0, nv);
    //     reajusteTP2(getTilepane(), ov, nv);

    //     criarTileGambiarra2(ov, nv);
    // }

    // public void removeRowY(int ov, int nv) {
    //     getMalhaModel().setY(nv);
    //     getTilepane().setPrefRows(nv);

    //     for (int i = ov - 1; i >= nv; i--) {
    //         for (int j = 0; j < getMalhaModel().getMatrixpixel().get(i).size(); j++) {
    //             int absolutePosition = getAbsoluteTilePosition2(j, i);
    //             getTilepane().getChildren().remove(absolutePosition);
    //         }
    //         getMalhaModel().getCoordinates().remove(i);
    //         getMalhaModel().getMatrixpixel().remove(i);
    //     }
    // }

    // public void reajusteMatrizBase(int x, int y) {
    //     if (x > 0) {
    //         int basej = getMalhaModel().getMatrixpixel().get(0).size();
    //         for (int i = -getMalhaModel().getX(); i <= getMalhaModel().getX(); i++) {
    //             for (int j = basej; j < x; j++) { // a | b | c | [adiciona no fim 3 0 caso tenha aumentado 3]
    //                 getMalhaModel().getCoordinates().get(i + getMalhaModel().getX()).add(j + getMalhaModel()
    //                         .getY(), createTile(j, i, false));
    //                 getMalhaModel().getMatrixpixel().get(i + getMalhaModel().getX()).add(j + getMalhaModel().getY(), 0);
    //             }
    //         }
    //     }

    //     if (y > 0) {
    //         int basei = getMalhaModel().getMatrixpixel().size();
    //         for (int i = basei; i < y; i++) {

    //             ArrayList<Integer> myList = new ArrayList<>(
    //                     Arrays.asList(new Integer[getMalhaModel().getMatrixpixel().get(0)
    //                             .size()]));
    //             Collections.fill(myList, 0);
    //             ArrayList<CheckBox> myList2 = new ArrayList<>(getMalhaModel().getMatrixpixel().get(0)
    //                     .size());

    //             for (int j = 0; j < getMalhaModel().getMatrixpixel().get(0)
    //                     .size(); j++) {
    //                 myList2.add(createTile(j, i, false));
    //             }
    //             getMalhaModel().getCoordinates().add(myList2);
    //             getMalhaModel().getMatrixpixel().add(myList);

    //         }
    //     }
    // }
}
