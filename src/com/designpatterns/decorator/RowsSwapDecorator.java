package com.designpatterns.decorator;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;

public class RowsSwapDecorator implements IMatrix {

    private IMatrix originalMatrix;
    private int row1;
    private int row2;

    public RowsSwapDecorator(IMatrix originalMatrix, int row1ToSwap, int row2ToSwap) {
        this.originalMatrix = originalMatrix;
        this.row1 = row1ToSwap;
        this.row2 = row2ToSwap;
    }

    @Override
    public int getRowsNumber() {
        return originalMatrix.getRowsNumber();
    }

    @Override
    public int getColumnsNumber() {
        return originalMatrix.getColumnsNumber();
    }

    @Override
    public double getValue(int rowIndex, int colIndex) {
        if(rowIndex == row1) {
            rowIndex = row2;
        }else if (rowIndex == row2) {
            rowIndex = row1;
        }
        return originalMatrix.getValue(rowIndex,colIndex);
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        if(rowIndex == row1) {
            rowIndex = row2;
        }else if (rowIndex == row2) {
            rowIndex = row1;
        }
        originalMatrix.setValue(rowIndex,colIndex,value);
    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        if(rowIndex == row1) {
            rowIndex = row2;
        }else if (rowIndex == row2) {
            rowIndex = row1;
        }
        return originalMatrix.getElementString(rowIndex,colIndex);
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
