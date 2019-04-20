package com.designpatterns.vector;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum VectorsEmptyValues {

    Regular(0.0),
    Sparse(-99.99);

    private final double value;

    VectorsEmptyValues(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Set<Double> getValues() {
        return Arrays.stream(VectorsEmptyValues.values()).map(VectorsEmptyValues::getValue).collect(Collectors.toSet());
    }
}
