package com.compgt01.model;

import javafx.scene.control.CheckBox;

public class CheckBoxXY extends CheckBox {

    private Ponto ponto;

    private Ponto pontoReal;

    public CheckBoxXY(Ponto ponto, Ponto pontoReal) {
        this.ponto = ponto;
        this.pontoReal = pontoReal;
    }

    public CheckBoxXY(String text, Ponto ponto, Ponto pontoReal) {
        super(text);
        this.ponto = ponto;
        this.pontoReal = pontoReal;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    public Ponto getPontoReal() {
        return pontoReal;
    }

    public void setPontoReal(Ponto pontoReal) {
        this.pontoReal = pontoReal;
    }

    @Override
    public String toString() {
        return "CheckBoxXY [ponto=" + ponto + ", pontoReal=" + pontoReal + "]";
    }

}
