package com.compgt01.model;

/**
 *
 * @author mmo
 */
public class MalhaModel {

    private int x;
    private int y;

    CheckBoxXY[][] gridCheckBox;

    public MalhaModel(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CheckBoxXY[][] getGridCheckBox() {
        return gridCheckBox;
    }

    public void setGridCheckBox(CheckBoxXY[][] gridPane) {
        this.gridCheckBox = gridPane;
    }

}
