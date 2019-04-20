package com.designpatterns.decorator;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;

public class ColumnsSwapDecorator implements IMatrix {

    private IMatrix originalMatrix;
    private int col1;
    private int col2;

    public ColumnsSwapDecorator(IMatrix originalMatrix,int col1ToSwap,int col2ToSwap) {
        this.originalMatrix = originalMatrix;
        this.col1 = col1ToSwap;
        this.col2 = col2ToSwap;
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
        if(colIndex == col1) {
            colIndex = col2;
        }else if (colIndex == col2) {
            colIndex = col1;
        }
        return originalMatrix.getValue(rowIndex,colIndex);
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        if(colIndex == col1) {
            colIndex = col2;
        }else if (colIndex == col2) {
            colIndex = col1;
        }
        originalMatrix.setValue(rowIndex,colIndex,value);
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.prepare(this);
        drawer.drawMatrix(this);
        drawer.finish(this);
    }
}
