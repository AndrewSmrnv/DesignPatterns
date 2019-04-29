package com.designpatterns.matrix;

import com.designpatterns.vector.Vector;
import com.designpatterns.vector.SparseVector;
import com.designpatterns.vector.VectorsEmptyValues;

public class SparseMatrix extends AbstractMatrix {

    public SparseMatrix(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Vector create(int dimensions) {
        return new SparseVector(dimensions);
    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        return getValue(rowIndex,colIndex) == VectorsEmptyValues.Sparse.getValue() ?
                "" : String.valueOf(getValue(rowIndex,colIndex));
    }
}