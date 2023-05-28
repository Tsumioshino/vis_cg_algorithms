package com.compgt01.model;

public class PontoBasier {

    private int x;
    private int y;
    private int indice;

    public PontoBasier(int x, int y, int indice) {
        this.x = x;
        this.y = y;
        this.indice = indice;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndice() {
        return indice;
    }

	@Override
	public String toString() {
		return "PontoBasier [x=" + x + ", y=" + y + ", indice=" + indice + "]";
	}




}
