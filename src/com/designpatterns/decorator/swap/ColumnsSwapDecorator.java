package com.designpatterns.decorator.swap;

import com.designpatterns.matrix.IMatrix;

public class ColumnsSwapDecorator extends AbstractSwapDecorator {

    private int col1;
    private int col2;

    public ColumnsSwapDecorator(IMatrix originalMatrix, int col1ToSwap, int col2ToSwap) {
        super(originalMatrix);
        this.col1 = col1ToSwap;
        this.col2 = col2ToSwap;
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
    public String getElementString(int rowIndex, int colIndex) {
        if(colIndex == col1) {
            colIndex = col2;
        }else if (colIndex == col2) {
            colIndex = col1;
        }
        return originalMatrix.getElementString(rowIndex,colIndex);
    }
}
