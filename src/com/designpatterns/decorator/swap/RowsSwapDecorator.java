package com.designpatterns.decorator.swap;

import com.designpatterns.matrix.IMatrix;

public class RowsSwapDecorator extends AbstractSwapDecorator {

    private int row1;
    private int row2;

    public RowsSwapDecorator(IMatrix originalMatrix, int row1ToSwap, int row2ToSwap) {
        super(originalMatrix);
        this.row1 = row1ToSwap;
        this.row2 = row2ToSwap;
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
}
