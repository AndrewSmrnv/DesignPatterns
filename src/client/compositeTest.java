package client;

import com.designpatterns.decorator.TransposingDecorator;
import com.designpatterns.drawer.ConsoleDrawer;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.composite.CompositeMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;

public class compositeTest {

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
        matrix2.draw(drawer);
        matrix3.draw(drawer);

        CompositeMatrix compositeMatrix = new CompositeMatrix(matrix1);
        compositeMatrix.addMatrix(matrix2);
        compositeMatrix.addMatrix(matrix3);

        System.out.println(compositeMatrix.getColumnsNumber());
        System.out.println(compositeMatrix.getRowsNumber());
        System.out.println("get [2,7] = " + compositeMatrix.getValue(2,7));
        System.out.println("get [3,14] = " + compositeMatrix.getValue(3,14));

        compositeMatrix.draw(drawer);

        TransposingDecorator transposingDecorator = new TransposingDecorator(compositeMatrix);
        transposingDecorator.draw(drawer);

        System.out.println();


    }
}
