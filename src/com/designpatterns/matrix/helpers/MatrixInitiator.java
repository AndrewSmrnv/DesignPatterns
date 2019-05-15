package com.designpatterns.matrix.helpers;

import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;


public class MatrixInitiator {

    public static void fillMatrix (IMatrix m, int notEmptyElements, int maxValue) {
       /* Validate.isTrue(notEmptyElements < m.getRowsNumber() * m.getColumnsNumber(),
                "Quantity of not empty elements can not be greater than number of matrix elements");*/

        Random ran = new Random();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        for (int p = 0; p < notEmptyElements; p++) {
            int rowInd, colInd;
            double val;
            do {
                rowInd = ran.nextInt(m.getRowsNumber());
                colInd = ran.nextInt(m.getColumnsNumber());
                val = (2 * ran.nextDouble() - 1) * maxValue;
            } while (!Constants.EmptyValues.getValues().contains(m.getValue(rowInd, colInd)));
            m.setValue(rowInd, colInd, Double.parseDouble(decimalFormat.format(val)));
        }
    }
}
