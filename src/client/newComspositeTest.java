package client;

import com.designpatterns.drawer.ConsoleDrawer;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.composite.MatrixComposite;
import com.designpatterns.matrix.helpers.MatrixInitiator;

public class newComspositeTest {

    public static void main(String[] args) {

        int ROWS = 4;
        int COLS = 5;
        int notEmptyElements = 15;
        int maxValue = 100;

        IMatrix matrix1 = new RegularMatrix(ROWS,COLS);
        MatrixInitiator.fillMatrix(matrix1, notEmptyElements, maxValue);

        IMatrix matrix2 = new RegularMatrix(3,COLS);
        MatrixInitiator.fillMatrix(matrix2, 10, maxValue);

        IMatrix matrix3 = new RegularMatrix(5,COLS);
        MatrixInitiator.fillMatrix(matrix3, notEmptyElements, maxValue);

        IDrawer drawer = new ConsoleDrawer();

        matrix1.draw(drawer);
        System.out.println();
        matrix2.draw(drawer);
        System.out.println();
        matrix3.draw(drawer);

        MatrixComposite matrixComposite = new MatrixComposite();
        matrixComposite.addRight(matrix1);
        matrixComposite.addRight(matrix2);
        matrixComposite.addBottom(matrix3);

        System.out.println(matrixComposite.getValue(3,3));

        System.out.println(matrixComposite.getColumnsNumber());
    }
}
