package com.designpatterns.matrix.composite;

import com.designpatterns.decorator.TransposingDecorator;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.MatrixElement;

import java.util.Iterator;

public class VerticalMatrixComposite implements IMatrix {

    private HorizontalMatrixComposite horizontalMatrixComposite;

    public VerticalMatrixComposite(IMatrix innerMatrix) {
        //Validate.notNull(innerMatrix,"Inner Matrix should be not null");
        horizontalMatrixComposite = new HorizontalMatrixComposite(innerMatrix);
    }

    @Override
    public int getColumnsNumber() {
        return new TransposingDecorator(horizontalMatrixComposite).getRowsNumber();
    }

    @Override
    public int getRowsNumber() {
        return new TransposingDecorator(horizontalMatrixComposite).getColumnsNumber();
    }

    @Override
    public double getValue(int rowIndex, int colIndex) {
        return new TransposingDecorator(horizontalMatrixComposite).getValue(colIndex,rowIndex);
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        new TransposingDecorator(horizontalMatrixComposite).setValue(colIndex,rowIndex,value);
    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        return new TransposingDecorator(horizontalMatrixComposite).getElementString(colIndex,rowIndex);
    }

    public void addMatrix(IMatrix matrixToAdd) {
        horizontalMatrixComposite.addMatrix(matrixToAdd);
    }

    @Override
    public void draw(IDrawer drawer) {
        new TransposingDecorator(horizontalMatrixComposite).draw(drawer);
    }

    @Override
    public Iterator<MatrixElement> iterator() {
        return null;
    }
}
