package client;

import com.designpatterns.matrix.Matrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;
import com.designpatterns.matrix.helpers.MatrixStatistic;

public class Lab1Demo {

    public static void main(String[] args) {

        int ROWS = 4;
        int COLS = 5;
        int notEmptyElements = 15;
        int maxValue = 100;

        Matrix testMatrix = new RegularMatrix(ROWS,COLS);
        //Matrix testMatrix = new SparseMatrix(ROWS, COLS);

        MatrixInitiator.fillMatrix(testMatrix, notEmptyElements, maxValue);

        System.out.println("\n Matrix : ");
        for (int row = 0; row < testMatrix.getRowsNumber(); row++) {
            for (int col = 0; col < testMatrix.getColumnsNumber(); col++) {
                System.out.print(testMatrix.getValue(row, col) + " ") ;
            }
            System.out.println();
        }

        MatrixStatistic matrixStatistic = new MatrixStatistic(testMatrix);
        System.out.print("\n" + "testMatrix " + matrixStatistic.toString() + "\n");

    }
}
