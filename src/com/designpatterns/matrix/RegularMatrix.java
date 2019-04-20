package com.designpatterns.matrix;

import com.designpatterns.vector.Vector;
import com.designpatterns.vector.RegularVector;

public class RegularMatrix extends AbstractMatrix {

    public RegularMatrix(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    Vector create(int dimension) {
        return new RegularVector(dimension);
    }

}
