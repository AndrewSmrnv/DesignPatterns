package com.designpatterns.drawer;


import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.helpers.DrawingHelper;

import static com.designpatterns.drawer.DrawingMode.CompositeMatrix;
import static com.designpatterns.drawer.DrawingMode.Sparse;

public abstract class AbstractTextDrawer implements IDrawer{

    protected static final String BAR = "|";
    protected static final String DASH = "-";

    private boolean showBorders;
    private DrawingMode mode = DrawingMode.Default;

    protected DrawingHelper drawingHelper;
    protected StringBuilder consoleOutput;

    private int maxRowLength = 0;

    protected void prepare(IMatrix matrix) {
        getDrawingHelper(matrix);
        composeConsoleOutput(matrix);
    }

    private void composeConsoleOutput(IMatrix matrix) {
        maxRowLength = 0;
        consoleOutput = new StringBuilder();
        for (int row = 0; row < matrix.getRowsNumber(); row++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int col = 0; col < matrix.getColumnsNumber(); col++) {

                double value = matrix.getValue(row, col);
                String alignedElement = getAlignedElement(value);

                rowBuilder.append(" " + alignedElement + " ");
                if (col != matrix.getColumnsNumber() - 1 && showBorders) {
                    rowBuilder.append(BAR);
                }
            }
            updateMaxRowLength(rowBuilder.length());
            consoleOutput.append(rowBuilder + "\n");
        }
        addBorders(consoleOutput);
    }

    protected String getAlignedElement(double value) {
        String alignedElement = drawingHelper.getBeautifiedElement(value);
        if((Sparse.equals(getMode()) || CompositeMatrix.equals(getMode()))
                && Constants.EmptyValues.SparseVector.getValue() == value) {
            alignedElement = drawingHelper.getNSpaces(drawingHelper.getMaxElementLength());
        } else if(CompositeMatrix.equals(getMode()) && Constants.EmptyValues.CompositeMatrix.getValue() == value) {
            alignedElement = drawingHelper.getNSpaces(drawingHelper.getMaxSymbolsLeft()) +
                    DASH +
                    drawingHelper.getNSpaces(drawingHelper.getMaxSymbolsRight());
        }
        return alignedElement;
    }

    protected void finish(IMatrix matrix) {}

    @Override
    public void drawMatrix(IMatrix matrix) {
        prepare(matrix);
        executeDrawing(matrix);
        finish(matrix);
    }

    protected abstract void executeDrawing(IMatrix matrix);

    public void setDrawingMode(DrawingMode mode) {
        this.mode = mode;
    }

    protected DrawingMode getMode() {
        return mode;
    }

    private void updateMaxRowLength(int rowLength) {
        if (rowLength > maxRowLength) {
            maxRowLength = rowLength;
        }
    }

    public void setShowBorders(boolean showBorders) {
        this.showBorders = showBorders;
    }

    protected void addBorders(StringBuilder sBuilder) {
        if (!showBorders) return;

        maxRowLength += 2;
        String content = sBuilder.toString();
        sBuilder.setLength(0);
        sBuilder.append(drawingHelper.multiplyString(DASH, maxRowLength) + "\n");
        for (String row : content.split("\n")) {
            String rowWithB = BAR + row + BAR + "\n";
            sBuilder.append(rowWithB);
        }
        sBuilder.append(drawingHelper.multiplyString(DASH, maxRowLength) + "\n");
    }

    protected DrawingHelper getDrawingHelper(IMatrix matrix) {
        if(drawingHelper == null) {
            drawingHelper = new DrawingHelper(matrix);
        }
        return drawingHelper;
    }

}
