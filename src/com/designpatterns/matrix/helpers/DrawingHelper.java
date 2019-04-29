package com.designpatterns.matrix.helpers;

import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;

public class DrawingHelper {

    private IMatrix matrix;

    private int maxSymbolsLeft = 0;
    private int maxSymbolsRight = 0;
    private int maxElementLength = 0;
    private int maxRowLength = 0;
    private boolean showBorders;
    private boolean elementSpaces;

    public DrawingHelper(IMatrix matrix,boolean showBorders,boolean elementSpaces) {
        this.matrix = matrix;
        this.showBorders = showBorders;
        this.elementSpaces = elementSpaces;
        calculateElementMaxLengths();
    }

    private void calculateElementMaxLengths() {
        for (int row = 0; row < matrix.getRowsNumber(); row++) {
            for (int col = 0; col < matrix.getColumnsNumber(); col++) {
                Double value = matrix.getValue(row, col);
                if(Constants.EmptyValues.getValues().contains(value)) {
                    continue;
                }
                String sValue = value.toString();
                int length = sValue.length();
                int lengthAfterDot = length - sValue.indexOf(".") - 1;
                int lengthBeforeDot = length - lengthAfterDot - 1;
                if (lengthBeforeDot > maxSymbolsLeft) {
                    maxSymbolsLeft = lengthBeforeDot;
                }
                if (lengthAfterDot > maxSymbolsRight) {
                    maxSymbolsRight = lengthAfterDot;
                }

            }

        }
        maxElementLength = maxSymbolsLeft + maxSymbolsRight + 1;

        for (int row = 0; row < matrix.getRowsNumber(); row++) {
            int rowLength = 0;
            for (int col = 0; col < matrix.getColumnsNumber(); col++) {
                rowLength += beautifyElement(matrix.getElementString(row,col)).length();
            }
            if(rowLength > maxRowLength) {
                maxRowLength = rowLength;
            }
        }
        if(showBorders){
            maxRowLength += matrix.getColumnsNumber() + 1;
        }
    }

    public String beautifyElement(String element) {
        String alignedElement = "";
        if(isDouble(element.replace(",","."))) {
            element = element.replace(",",".");
            int length = element.length();
            int lengthAfterDot = length - element.indexOf(".") - 1;
            int lengthBeforeDot = length - lengthAfterDot - 1;
            alignedElement = getNSpaces(maxSymbolsLeft - lengthBeforeDot) +
                    element +
                    getNSpaces(maxSymbolsRight - lengthAfterDot);
        } else if (element.isEmpty()) {
            alignedElement = getNSpaces(maxElementLength);
        } else {
            int length = element.length();
            alignedElement = getNSpaces(maxSymbolsLeft - length/2) +
                    element +
                    getNSpaces(maxSymbolsRight - length/2);
        }
        if(elementSpaces) {
            alignedElement = " " + alignedElement + " ";
        }
        return alignedElement;
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public int getMaxElementLength() {
        return maxElementLength;
    }

    public int getMaxSymbolsLeft() {
        return maxSymbolsLeft;
    }

    public int getMaxSymbolsRight() {
        return maxSymbolsRight;
    }

    public int getMaxRowLength() {
        return maxRowLength;
    }

    public static String getNSpaces(int numberOfSpaces) {
        return numberOfSpaces  > 0 ? multiplyString(" ", numberOfSpaces) : "";
    }

    public static String multiplyString(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }
}
