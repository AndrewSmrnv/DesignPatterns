package client;

import com.designpatterns.drawer.ConsoleDrawer;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.SparseMatrix;
import com.designpatterns.matrix.composite.CompositeMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;

import static com.designpatterns.app.Constants.Application.MATRIX_MAX_VALUE;

public class compositeTest1 {

    public static void main(String[] args) {
        RegularMatrix matrix1 = new RegularMatrix(5,1);
        SparseMatrix matrix2 = new SparseMatrix(3,3);
        RegularMatrix matrix3 = new RegularMatrix(4,2);

        MatrixInitiator.fillMatrix(matrix1,4,MATRIX_MAX_VALUE);
        MatrixInitiator.fillMatrix(matrix2,6,MATRIX_MAX_VALUE);
        MatrixInitiator.fillMatrix(matrix3,7,MATRIX_MAX_VALUE);

        CompositeMatrix compositeMatrix = new CompositeMatrix(matrix1);
        compositeMatrix.addMatrix(matrix2);
        compositeMatrix.addMatrix(matrix3);

        IDrawer drawer = new ConsoleDrawer();


        matrix1.draw(drawer);
        System.out.println();
        matrix2.draw(drawer);
        System.out.println();
        matrix3.draw(drawer);

        //System.out.println(compositeMatrix.getRowsNumber());
        //System.out.println(compositeMatrix.getColumnsNumber());

        //System.out.println("get [0,3] from matrix1 = " + matrix1.getValue(3,0));

        //System.out.println("get [0,2] from matrix2 = " + matrix2.getValue(0,2));
        //System.out.println("get [1,2] from matrix2 = " + matrix2.getValue(1,2));

        System.out.println("get [0,3] = " + compositeMatrix.getValue(0,3));
        System.out.println();
        System.out.println("get [1,3] = " + compositeMatrix.getValue(1,3));
        System.out.println();
        System.out.println("get [5,5] = " + compositeMatrix.getValue(2,5));

        System.out.println("get [1,5] = " + compositeMatrix.getValue(1,5));
        System.out.println();
        compositeMatrix.setValue(1,5,10);
        System.out.println();
        System.out.println("get [1,5] = " + compositeMatrix.getValue(1,5));


        //ompositeMatrix.draw(drawer);
    }
}
