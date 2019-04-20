package com.designpatterns.drawer;


import com.designpatterns.matrix.IMatrix;

public interface IDrawer {

    void prepare(IMatrix matrix);

    void finish(IMatrix matrix);

    void drawMatrix(IMatrix m);

}
