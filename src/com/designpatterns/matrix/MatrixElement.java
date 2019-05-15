package com.designpatterns.matrix;

public class MatrixElement {
    int i;
    int j;
    public String stringValue;
    public double value;
    public MatrixElement next;

    public MatrixElement(int i, int j, double value,String stringValue) {
        this.i = i;
        this.j = j;
        this.value = value;
        this.stringValue = stringValue;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getStringValue() {
        return stringValue;
    }

    public double getValue() {
        return value;
    }
}
