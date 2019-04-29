package com.designpatterns.drawer;


import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.helpers.DrawingHelper;

import static com.designpatterns.drawer.DrawingMode.CompositeMatrix;
import static com.designpatterns.drawer.DrawingMode.Sparse;

public abstract class AbstractTextDrawer implements IDrawer {

    protected static final String BAR = "|";
    protected static final String LEFT_BORDER =  BAR;
    protected static final String RIGHT_BORDER = BAR;
    protected static final String DASH = "-";

    protected boolean showBorders;

    protected DrawingHelper drawingHelper;
    protected StringBuilder consoleOutput;

    protected int maxRowLength = 0;
    private boolean elementSpaces = true;

    private void composeConsoleOutput(IMatrix matrix) {

    }

   /* protected String getAlignedElement(String value) {
        String alignedElement = drawingHelper.getBeautifiedElement(value);
        *//*if((Sparse.equals(getMode()) || HorizontalMatrixComposite.equals(getMode()))
                && Constants.EmptyValues.SparseVector.getValue() == value) {
            alignedElement = drawingHelper.getNSpaces(drawingHelper.getMaxElementLength());
        } else if(HorizontalMatrixComposite.equals(getMode()) && Constants.EmptyValues.HorizontalMatrixComposite.getValue() == value) {
            alignedElement = drawingHelper.getNSpaces(drawingHelper.getMaxSymbolsLeft()) +
                    DASH +
                    drawingHelper.getNSpaces(drawingHelper.getMaxSymbolsRight());
        }*//*
        return alignedElement;
    }*/

    @Override
    public void beginDrawing(IMatrix m) {
        getDrawingHelper(m);
        composeConsoleOutput(m);
    }

    private void updateMaxRowLength(int rowLength) {
        if (rowLength > maxRowLength) {
            maxRowLength = rowLength;
        }
    }

    public void setShowBorders(boolean showBorders) {
        this.showBorders = showBorders;
    }

    public void setElementSpaces(boolean elementSpaces) {
        this.elementSpaces = elementSpaces;
    }

    protected DrawingHelper getDrawingHelper(IMatrix matrix) {
        if(drawingHelper == null) {
            drawingHelper = new DrawingHelper(matrix,showBorders,elementSpaces);
        }
        return drawingHelper;
    }

}
