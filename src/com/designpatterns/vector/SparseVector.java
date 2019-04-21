package com.designpatterns.vector;

import com.designpatterns.app.Constants;

import java.util.HashMap;
import java.util.Map;


public class SparseVector implements Vector {

    private int dimension;
    private Map<Integer, Double> components;

    public SparseVector(int dimension) {
        this.dimension = dimension;
        this.components = new HashMap<>();
    }

    @Override
    public double getComponent(int index) {
        return components.containsKey(index) ? components.get(index) : Constants.EmptyValues.SparseVector.getValue();
    }

    @Override
    public void setComponent(int index, double value) {
        components.put(index, value);
    }

}
