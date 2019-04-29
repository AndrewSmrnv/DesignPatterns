package com.designpatterns.drawer;


import com.designpatterns.matrix.IMatrix;

public class ConsoleDrawer extends AbstractTextDrawer {

    @Override
    public void beginDrawing(IMatrix m) {
        super.beginDrawing(m);
        if (showBorders) {
            drawUpperBorder();
        }
    }

    private void drawUpperBorder() {
        System.out.println(drawingHelper.multiplyString(DASH, drawingHelper.getMaxRowLength()));
    }

    private void drawBottomBorder() {
        System.out.println(drawingHelper.multiplyString(DASH, drawingHelper.getMaxRowLength()) + "\n");
    }

    @Override
    public void drawElement(IMatrix m, int i, int j) {
        if(showBorders) {
            drawLeftBorder(m,i,j);
        }
        System.out.print(drawingHelper.beautifyElement(m.getElementString(i,j)));
        if(showBorders) {
            drawRightBorder(m,i,j);
        }
        if(j == m.getColumnsNumber() -1){
            System.out.println();
        }
    }

    private void drawLeftBorder(IMatrix m,int i,int j) {
        if(j%2 == 0){
            System.out.print(LEFT_BORDER);
        }
    }

    private void drawRightBorder(IMatrix m,int i,int j) {
        if(j%2 == 0 || j == m.getColumnsNumber() - 1) {
            System.out.print(RIGHT_BORDER);
        }
    }

    @Override
    public void finishDrawing(IMatrix m) {
        if (showBorders) {
            drawBottomBorder();
        }
    }

}