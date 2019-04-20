package com.designpatterns.drawer;


import com.designpatterns.matrix.IMatrix;

public class ConsoleDrawer extends AbstractTextDrawer {

    @Override
    public void prepare(IMatrix matrix){
        //System.out.println("Starting ConsoleDrawer...");
        super.prepare(matrix);
    }

    @Override
    public void finish(IMatrix matrix) {
        //System.out.println("ConsoleDrawer has finished it's work");
    }

    @Override
    public void drawMatrix(IMatrix matrix) {
        /*System.out.println();
        System.out.println("ConsoleDrawer is drawing the matrix : ");*/
        System.out.println(consoleOutput.toString());
    }

}