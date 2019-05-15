package com.designpatterns.matrix.composite;


import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.MatrixElement;
import com.designpatterns.vector.EmptyValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class CompositeMatrix implements IMatrix {

    private List<IMatrix> innerMatrices = new ArrayList<>();

    private int rows;
    private int cols;

    public CompositeMatrix(IMatrix baseMatrix) {
        addMatrix(baseMatrix);
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
        AtomicInteger adaptedColumnIndex = new AtomicInteger(colIndex);
        IMatrix matrix = getMatrixByColumnIndex(adaptedColumnIndex);
        if(rowIndex <= matrix.getRowsNumber() - 1) {
            return matrix.getValue(rowIndex, adaptedColumnIndex.intValue());
        }else {
            if(rowIndex < getRowsNumber()) {
                return EmptyValues.Composite.getValue();
            }else {
                throw new IllegalArgumentException("No such matrix found for row index = " + rowIndex);
            }

        }
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {
        if(rowIndex < 0 || rowIndex > getRowsNumber() - 1 ||
                colIndex < 0 || colIndex > getColumnsNumber() - 1) {
            throw new IllegalArgumentException("No such index exists in the matrix");
        }
        AtomicInteger adaptedColumnIndex = new AtomicInteger(colIndex);
        IMatrix matrix = getMatrixByColumnIndex(adaptedColumnIndex);
        if(rowIndex <= matrix.getRowsNumber() - 1) {
            matrix.setValue(rowIndex, colIndex,value);
        }
    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        return  getValue(rowIndex,colIndex) == EmptyValues.Composite.getValue() ?
                "-" : String.valueOf(getValue(rowIndex,colIndex));
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.beginDrawing(this);
        drawElements(drawer);
        drawer.finishDrawing(this);
    }

    protected void drawElements(IDrawer drawer) {
        MatrixIterator iterator = iterator();
        while (iterator .hasNext()) {
            MatrixElement me = iterator.next();
            drawer.drawElement(this,me.getI(), me.getJ());
        }
    }

    public MatrixIterator iterator() {
        return new MatrixIterator();
    }

    class MatrixIterator implements Iterator<MatrixElement> {

        List<MatrixElement> matrixElements = new ArrayList<>();
        private MatrixElement element;

        public MatrixIterator() {
            MatrixElement previous = null;
            for (int i = 0; i < getRowsNumber(); i++) {
                for (int j = 0; j < getColumnsNumber(); j++) {
                    MatrixElement matrixElement = new MatrixElement(i,j,getValue(i,j),getElementString(i,j));
                    if(matrixElements.size() != 0) {
                        previous.next = matrixElement;
                    }
                    previous = matrixElement;
                    matrixElements.add(matrixElement);
                }
            }
            element = matrixElements.iterator().next();
        }

        @Override
        public boolean hasNext() {
            return element != null;
        }

        @Override
        public MatrixElement next() {
            if(hasNext()) {
                MatrixElement current = element;
                element = element.next;
                return current;
            }else {
                throw new NoSuchElementException();
            }
        }
    }

    public void addMatrix(IMatrix matrixToAdd) {
        innerMatrices.add(matrixToAdd);
        cols += matrixToAdd.getColumnsNumber();
        int matrixToAddRows = matrixToAdd.getRowsNumber();
        rows = rows > matrixToAddRows ? rows : matrixToAddRows;
    }

    private IMatrix getMatrixByColumnIndex(AtomicInteger columnIndex) {
        int rangeLeft = 0;
        int rangeRight = 0;
        Iterator<IMatrix> iterator = innerMatrices.iterator();

        for (IMatrix innerMatrix : innerMatrices) {
            rangeRight += iterator.next().getColumnsNumber() - 1;
            if(columnIndex.intValue() >= rangeLeft && columnIndex.intValue() <= rangeRight) {
                columnIndex.set(columnIndex.intValue() - rangeLeft);
                return innerMatrix;
            }
            rangeRight++;
            rangeLeft = rangeRight;
        }
        throw new IllegalArgumentException("No such matrix found for column index = " + columnIndex);
    }
}
