package com.designpatterns.vector;

public class RegularVector implements Vector {

    private int dimension;
    private double[] components;

    public RegularVector(int dimension) {
        this.dimension = dimension;
        components = new double[dimension];
    }

    @Override
    public double getComponent(int index) {
        return components[index];
    }

    @Override
    public void setComponent(int index, double value) {
        components[index] = value;
    }

}
