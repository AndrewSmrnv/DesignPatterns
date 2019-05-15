package com.designpatterns.vector;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum EmptyValues {

    Regular(0.0),
    Sparse(-99.99),
    Composite(-999.999);

    private double value;

    EmptyValues(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Set<Double> getValues() {
        return Arrays.stream(EmptyValues.values()).map(EmptyValues::getValue).collect(Collectors.toSet());
    }
}
