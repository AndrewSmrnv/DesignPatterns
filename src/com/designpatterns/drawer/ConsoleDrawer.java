package com.designpatterns.drawer;


import com.designpatterns.matrix.IMatrix;

public class ConsoleDrawer extends AbstractTextDrawer {

    @Override
    protected void executeDrawing(IMatrix matrix) {
        if(consoleOutput != null && consoleOutput.length() != 0) {
            System.out.println(consoleOutput.toString());
        } else {
            System.out.println("Content for drawing in the console is empty");
        }
    }

}