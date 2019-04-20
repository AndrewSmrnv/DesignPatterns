package com.designpatterns.matrix.helpers;

import com.designpatterns.matrix.Matrix;
import com.designpatterns.vector.VectorsEmptyValues;
import org.apache.commons.lang3.Validate;

import java.text.DecimalFormat;

public class MatrixStatistic {

    private double sum, mean, max;
    private int notEmptyElements;
    private Matrix m;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public MatrixStatistic(Matrix m) {
        Validate.notNull(m, "Matrix must not be null to calculate it's statics");
        this.m = m;
        calculateStatistic();
    }

    private void calculateStatistic() {
        double s = 0;
        double maxV = 0;
        int count = 0;

        int rows = m.getRowsNumber();
        int cols = m.getColumnsNumber();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                double elem = m.getValue(i, j);
                s += elem;
                if (!VectorsEmptyValues.getValues().contains(elem)) count++;
                if (elem > maxV) maxV = elem;
            }
        sum = s;
        max = maxV;
        mean = sum / (cols * rows);
        notEmptyElements = count;
    }

    @Override
    public String toString() {
        return  "statistic : " +   "\n"  +
                "n sum: "  + decimalFormat.format(sum)+ "\n"  +
                "mean : " + decimalFormat.format(mean)+ "\n"  +
                "max: "   + decimalFormat.format(max)+  "\n"  +
                "number of not empty elements : " + notEmptyElements;
    }


}