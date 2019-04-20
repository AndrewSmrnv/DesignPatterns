package com.designpatterns.matrix.helpers;

import com.designpatterns.matrix.IMatrix;

public class DrawingHelper {

    private IMatrix matrix;

    private int maxSymbolsLeft = 0;
    private int maxSymbolsRight = 0;
    private int maxElementLength = 0;

    public DrawingHelper(IMatrix matrix) {
        this.matrix = matrix;
        calculateElementMaxLengths();
    }

    private void calculateElementMaxLengths() {
        for (int row = 0; row < matrix.getRowsNumber(); row++) {
            for (int col = 0; col < matrix.getColumnsNumber(); col++) {
                Double value = matrix.getValue(row, col);
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
    }

    public String getBeautyElement(Double value) {
        String sValue = value.toString();
        int length = sValue.length();
        int lengthAfterDot = length - sValue.indexOf(".") - 1;
        int lengthBeforeDot = length - lengthAfterDot - 1;
        String alignedElement = getNSpaces(maxSymbolsLeft - lengthBeforeDot) +
                sValue +
                getNSpaces(maxSymbolsRight - lengthAfterDot);
        return alignedElement;
    }

    public int getMaxElementLength() {
        return maxElementLength;
    }

    public static String getNSpaces(int numberOfSpaces) {
        return numberOfSpaces  > 0 ? multiplyString(" ", numberOfSpaces) : "";
    }

    public static String multiplyString(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }
}
