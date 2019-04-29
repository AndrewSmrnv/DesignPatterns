package com.designpatterns.matrix;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.vector.Vector;

public abstract class AbstractMatrix implements IMatrix {

    private final Vector[] vectors;
    private final int rows, columns;

    AbstractMatrix(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
        vectors = new Vector[rows];
        for (int i = 0; i < rows; i++)
            vectors[i] = this.create(cols);
    }

    abstract Vector create(int dimension);

    public double getValue(int rowIndex, int colIndex) {
        return vectors[rowIndex].getComponent(colIndex);
    }

    public void setValue(int rowIndex, int colIndex, double value) {
        vectors[rowIndex].setComponent(colIndex, value);
    }

    public int getRowsNumber() {
        return rows;
    }

    public int getColumnsNumber() {
        return columns;
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.beginDrawing(this);
        drawElements(drawer);
        drawer.finishDrawing(this);
    }

    protected void drawElements(IDrawer drawer) {
        for (int i = 0; i < getRowsNumber(); i++) {
            for (int j = 0; j < getColumnsNumber(); j++) {
                drawer.drawElement(this,i,j);
            }
        }
    }
}
