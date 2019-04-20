package com.designpatterns.matrix.helpers;

import com.designpatterns.matrix.Matrix;
import com.designpatterns.vector.VectorsEmptyValues;
import org.apache.commons.lang3.Validate;

import java.text.DecimalFormat;
import java.util.Random;


public class MatrixInitiator {

    public static void fillMatrix (Matrix m, int notEmptyElements, int maxValue) {
        Validate.isTrue(notEmptyElements < m.getRowsNumber() * m.getRowsNumber(),
                "Quantity of not empty elements can not be greater than number of matrix elements");
        Random ran = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("#,##");

        for (int p = 0; p < notEmptyElements; p++) {
            int rowInd, colInd;
            double val;
            do {
                rowInd = ran.nextInt(m.getRowsNumber());
                colInd = ran.nextInt(m.getColumnsNumber());
                val = (2 * ran.nextDouble() - 1) * maxValue;
            } while (!VectorsEmptyValues.getValues().contains(m.getValue(rowInd, colInd)));
            m.setValue(rowInd, colInd, Double.parseDouble(decimalFormat.format(val)));
        }
    }
}