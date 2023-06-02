package com.compgt01.model;

import javafx.scene.control.CheckBox;

/**
 *
 * @author mmo
 */
public class MalhaModel {

    private int x;
    private int y;

    CheckBox[][] gridCheckBox;


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


    public CheckBox[][] getGridCheckBox() {
        return gridCheckBox;
    }


    public void setGridCheckBox(CheckBox[][] gridPane) {
        this.gridCheckBox = gridPane;
    }

   
}
