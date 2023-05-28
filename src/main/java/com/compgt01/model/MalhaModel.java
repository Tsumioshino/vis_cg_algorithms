package com.compgt01.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;

/**
 *
 * @author mmo
 */
public class MalhaModel {

    private int qtdPixelX;
    private int qtdPixelY;

    List<CheckBox> tilepane = new ArrayList<>();

    // Representa as coordenadas em Checkbox.
    // para utilizar setSelected.
    List<List<CheckBox>> matrixcoordinates = new ArrayList<>();

    // Representa as coordenadas para manipulação matemática.
    // -> 0, caso pixel não pintado. 1, caso pintado.
    List<List<Integer>> matrixpixel = new ArrayList<>();

    public MalhaModel(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public List<CheckBox> getTilePane() {
        return tilepane;
    }

    public void setTilePane(List<CheckBox> tilepane) {
        this.tilepane = tilepane;
    }

    public List<List<CheckBox>> getCoordinates() {
        return this.matrixcoordinates;
    }

    public void setCoordinates(List<List<CheckBox>> matrixcoordinates) {
        this.matrixcoordinates = matrixcoordinates;
    }

    public List<List<Integer>> getMatrixpixel() {
        return matrixpixel;
    }

    public void setMatrixpixel(List<List<Integer>> matrixpixel) {
        this.matrixpixel = matrixpixel;
    }

    public int getX() {
        return qtdPixelX;
    }

    public void setX(int x) {
        this.qtdPixelX = x;
    }

    public int getY() {
        return qtdPixelY;
    }

    public void setY(int y) {
        this.qtdPixelY = y;
    }
}
