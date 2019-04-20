package com.designpatterns.matrix;

import java.util.HashMap;
import java.util.Map;

public enum MatrixType {
    Regular(0),
    Sparse(1);

    private final int id;

    private static final Map<Integer, MatrixType> lookup = new HashMap<>();

    static {
        for (MatrixType mType : MatrixType.values()) {
            lookup.put(mType.getId(), mType);
        }
    }

    MatrixType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MatrixType getMatrixTypeById(int id) {
        return lookup.get(id);
    }

}
