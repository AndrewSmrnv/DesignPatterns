package client;

import com.designpatterns.decorator.swap.RowsSwapDecorator;
import com.designpatterns.drawer.ConsoleDrawer;
import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;

public class iteratorTest {

    public static void main(String[] args) {
        int ROWS = 4;
        int COLS = 5;
        int notEmptyElements = 15;
        int maxValue = 100;

        IMatrix matrix1 = new RegularMatrix(ROWS,COLS);
        MatrixInitiator.fillMatrix(matrix1, notEmptyElements, maxValue);

        IDrawer drawer = new ConsoleDrawer();

        matrix1.draw(drawer);

        /*while(matrix1.iterator().hasNext()) {
            System.out.println(matrix1.iterator().next().getStringValue());
        }*/

        System.out.println("decorator");

        RowsSwapDecorator rowsSwapDecorator = new RowsSwapDecorator(matrix1,1,2);
        rowsSwapDecorator.draw(drawer);
    }
}
