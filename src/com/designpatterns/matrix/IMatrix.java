package com.designpatterns.matrix;

import com.designpatterns.drawer.IDrawer;

import java.util.Iterator;

public interface IMatrix {

    int getRowsNumber();

    int getColumnsNumber();

    double getValue(int rowIndex, int colIndex);

    void setValue(int rowIndex, int colIndex,double value);

    String getElementString(int rowIndex, int colIndex);

    void draw(IDrawer drawer);

    Iterator<MatrixElement> iterator();
}
