package com.designpatterns.matrix.composite;

import com.designpatterns.app.Constants;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.AbstractMatrix;
import com.designpatterns.matrix.IMatrix;
import org.apache.commons.lang3.Validate;

public class CompositeMatrix implements IMatrix {

    private IMatrix innerMatrix;
    private CompositeMatrix childComposite;
    private CompositeMatrix parentComposite;

    private int rows;
    private int cols;

    public CompositeMatrix(IMatrix innerMatrix) {
        Validate.notNull(innerMatrix,"Inner Matrix should be not null");
        this.innerMatrix = innerMatrix;
        rows = innerMatrix.getRowsNumber();
        cols = innerMatrix.getColumnsNumber();
    }

    @Override
    public int getColumnsNumber() {
        return cols;
    }

    @Override
    public int getRowsNumber() {
        return rows;
    }

    @Override
    public double getValue(int rowIndex, int colIndex) {
        // since all calls of this method will be invoked from a top composite it's enough to validate indexes at the beginning
        if(parentComposite == null) {
            Validate.isTrue(rowIndex < getRowsNumber() || colIndex < getColumnsNumber(), "Incorrect indexes %d,%d for getting value",rowIndex,colIndex);
        }

        if(colIndex < innerMatrix.getColumnsNumber()) {
            if(rowIndex < innerMatrix.getRowsNumber()) {
                return innerMatrix.getValue(rowIndex,colIndex);
            }else {
                return Constants.EmptyValues.CompositeMatrix.getValue();
                //throw new IllegalArgumentException("Incorrect index for getting value");
            }
        }else {
            colIndex -= innerMatrix.getColumnsNumber();
            return childComposite.getValue(rowIndex,colIndex);
        }
    }


    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        // since all calls of this method will be invoked from a top composite it's enough to validate indexes at the beginning
        if(parentComposite == null) {
            Validate.isTrue(rowIndex < getRowsNumber() || colIndex < getColumnsNumber(), "Incorrect indexes %d,%d for setting value",rowIndex,colIndex);
        }

        if(colIndex < innerMatrix.getColumnsNumber()) {
            if(rowIndex < innerMatrix.getRowsNumber()) {
                innerMatrix.setValue(rowIndex,colIndex,value);
            }
        }else {
            colIndex -= innerMatrix.getColumnsNumber();
            childComposite.setValue(rowIndex,colIndex,value);
        }
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.drawMatrix(this);
    }

    public void addMatrix(IMatrix matrixToAdd) {
        if (childComposite == null) {
            CompositeMatrix newComposite = new CompositeMatrix(matrixToAdd);
            childComposite = newComposite;
        } else {
            childComposite.addMatrix(matrixToAdd);
        }
        cols += matrixToAdd.getColumnsNumber();
        int matrixToAddRows = childComposite.getRowsNumber();
        rows = rows > matrixToAddRows ? rows : matrixToAddRows;
    }

    public IMatrix getInnerMatrix() {
        return innerMatrix;
    }

    public CompositeMatrix getChildComposite() {
        return childComposite;
    }

    public CompositeMatrix getParentComposite() {
        return parentComposite;
    }

    public void setParentComposite(CompositeMatrix parentComposite) {
        this.parentComposite = parentComposite;
    }
}
