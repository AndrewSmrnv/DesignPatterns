package com.designpatterns.matrix.composite;

import com.designpatterns.app.Constants;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.MatrixElement;

import java.util.Iterator;
//import org.apache.commons.lang3.Validate;

public class HorizontalMatrixComposite implements IMatrix {

    private IMatrix innerMatrix;
    private HorizontalMatrixComposite childComposite;
    private HorizontalMatrixComposite parentComposite;

    private int rows;
    private int cols;

    public HorizontalMatrixComposite(IMatrix innerMatrix) {
        //Validate.notNull(innerMatrix,"Inner Matrix should be not null");
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
            //Validate.isTrue(rowIndex < getRowsNumber() || colIndex < getColumnsNumber(), "Incorrect indexes %d,%d for getting value",rowIndex,colIndex);
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
            //Validate.isTrue(rowIndex < getRowsNumber() || colIndex < getColumnsNumber(), "Incorrect indexes %d,%d for setting value",rowIndex,colIndex);
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
    public String getElementString(int rowIndex, int colIndex) {
        // since all calls of this method will be invoked from a top composite it's enough to validate indexes at the beginning
        if(parentComposite == null) {
            //Validate.isTrue(rowIndex < getRowsNumber() || colIndex < getColumnsNumber(), "Incorrect indexes %d,%d for getting value",rowIndex,colIndex);
        }

        if(colIndex < innerMatrix.getColumnsNumber()) {
            if(rowIndex < innerMatrix.getRowsNumber()) {
                return innerMatrix.getElementString(rowIndex,colIndex);
            }else {
                return "-";
                //throw new IllegalArgumentException("Incorrect index for getting value");
            }
        }else {
            colIndex -= innerMatrix.getColumnsNumber();
            return childComposite.getElementString(rowIndex,colIndex);
        }
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.beginDrawing(this);
        drawElements(drawer);
        drawer.finishDrawing(this);
    }

    @Override
    public Iterator<MatrixElement> iterator() {
        return null;
    }

    protected void drawElements(IDrawer drawer) {
        for (int i = 0; i < getRowsNumber(); i++) {
            for (int j = 0; j < getColumnsNumber(); j++) {
                drawer.drawElement(this,i,j);
            }
        }
    }

    public void addMatrix(IMatrix matrixToAdd) {
        if (childComposite == null) {
            HorizontalMatrixComposite newComposite = new HorizontalMatrixComposite(matrixToAdd);
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

    public HorizontalMatrixComposite getChildComposite() {
        return childComposite;
    }

    public HorizontalMatrixComposite getParentComposite() {
        return parentComposite;
    }

    public void setParentComposite(HorizontalMatrixComposite parentComposite) {
        this.parentComposite = parentComposite;
    }
}
