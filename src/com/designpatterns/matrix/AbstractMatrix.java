package com.designpatterns.matrix;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.vector.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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

}
