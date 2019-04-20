package com.designpatterns.drawer;

public enum DrawingMode {

    Default(0),
    Sparse(1);

    private int id;

    DrawingMode(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }
}
