package client;

import com.designpatterns.app.Application;
import com.designpatterns.decorator.ColumnsSwapDecorator;
import com.designpatterns.drawer.AbstractTextDrawer;
import com.designpatterns.drawer.ConsoleDrawer;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.SparseMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;
import com.designpatterns.matrix.helpers.MatrixStatistic;

public class Lab2Demo {

    public static void main(String[] args) {

        int ROWS = 4;
        int COLS = 5;
        int notEmptyElements = 15;
        int maxValue = 100;

      /*  IMatrix regMat = new RegularMatrix(ROWS,COLS);
        //Matrix testMatrix = new SparseMatrix(ROWS, COLS);

        MatrixInitiator.fillMatrix(regMat, notEmptyElements, maxValue);

        System.out.println("\n Matrix : ");*/

      /*  for (int row = 0; row < regMat.getRowsNumber(); row++) {
            for (int col = 0; col < regMat.getColumnsNumber(); col++) {
                System.out.print(regMat.getValue(row, col) + " ") ;
            }
            System.out.println();
        }*/

        IMatrix spMat = new SparseMatrix(ROWS,COLS);
        //Matrix testMatrix = new SparseMatrix(ROWS, COLS);

        MatrixInitiator.fillMatrix(spMat, notEmptyElements, maxValue);

        System.out.println("\n Sparse Matrix : ");

        /*for (int row = 0; row < spMat.getRowsNumber(); row++) {
            for (int col = 0; col < spMat.getColumnsNumber(); col++) {
                System.out.print(spMat.getValue(row, col) + " ") ;
            }
            System.out.println();
        }
*/
        IDrawer drawer = new ConsoleDrawer();
        ((AbstractTextDrawer) drawer).setShowBorders(true);
        ((AbstractTextDrawer) drawer).setElementSpaces(true);
        //regMat.draw(drawer);
        spMat.draw(drawer);


        ColumnsSwapDecorator columnsSwapDecorator = new ColumnsSwapDecorator(spMat,1,2);
        columnsSwapDecorator.draw(drawer);
    }

}
