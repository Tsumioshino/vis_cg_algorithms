package com.compgt01.model;

import javafx.scene.control.CheckBox;

public class CheckBoxXY extends CheckBox {

    private Ponto ponto;

    public Ponto getPonto() {
        return ponto;
    }

    public CheckBoxXY(Ponto ponto) {
        this.ponto = ponto;
    }



    public CheckBoxXY(String text, Ponto ponto) {
        super(text);
        this.ponto = ponto;
    }


    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    @Override
    public String toString() {
        return "CheckBoxXY [ponto=" + ponto + "]";
    }

}
