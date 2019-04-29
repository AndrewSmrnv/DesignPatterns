package com.designpatterns.drawer;


import com.designpatterns.matrix.IMatrix;

public interface IDrawer {

    void beginDrawing(IMatrix m);

    void drawElement(IMatrix m,int i,int j);

    void finishDrawing(IMatrix m);

}
