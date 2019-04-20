package com.designpatterns.matrix;

public interface Matrix {

    int getRowsNumber();

    int getColumnsNumber();

    double getValue(int rowIndex, int colIndex);

    void setValue(int rowIndex, int colIndex,double value);
}
