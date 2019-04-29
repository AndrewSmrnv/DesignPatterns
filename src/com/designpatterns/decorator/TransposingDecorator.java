package com.designpatterns.decorator;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;

public class TransposingDecorator implements IMatrix {

    private IMatrix originalMatrix;

    public TransposingDecorator(IMatrix originalMatrix) {
        this.originalMatrix = originalMatrix;
    }

    @Override
    public int getRowsNumber() {
        return originalMatrix.getColumnsNumber();
    }

    @Override
    public int getColumnsNumber() {
        return originalMatrix.getRowsNumber();
    }

    @Override
    public double getValue(int rowIndex, int colIndex) {
        return originalMatrix.getValue(colIndex,rowIndex);
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        originalMatrix.setValue(colIndex,rowIndex,value);
    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        return originalMatrix.getElementString(colIndex,rowIndex);
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
