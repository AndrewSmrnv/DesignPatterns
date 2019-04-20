package com.designpatterns.matrix;

import com.designpatterns.vector.Vector;
import com.designpatterns.vector.SparseVector;

public class SparseMatrix extends AbstractMatrix {

    public SparseMatrix(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public Vector create(int dimensions) {
        return new SparseVector(dimensions);
    }

}