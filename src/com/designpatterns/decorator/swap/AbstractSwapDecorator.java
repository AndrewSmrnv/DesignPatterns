package com.designpatterns.decorator.swap;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.MatrixElement;

import java.util.Iterator;

public abstract class AbstractSwapDecorator implements IMatrix {

    protected IMatrix originalMatrix;

    public AbstractSwapDecorator(IMatrix originalMatrix) {
        this.originalMatrix = originalMatrix;
    }

    @Override
    public void draw(IDrawer drawer) {
        drawer.beginDrawing(this);
        drawElements(drawer);
        drawer.finishDrawing(this);
    }

    @Override
    public int getRowsNumber() {
        return originalMatrix.getRowsNumber();
    }

    @Override
    public int getColumnsNumber() {
        return originalMatrix.getColumnsNumber();
    }

    protected void drawElements(IDrawer drawer) {
        Iterator<MatrixElement> iterator = iterator();
        while (iterator.hasNext()) {
            MatrixElement elementToDraw = iterator.next();
            drawer.drawElement(this,elementToDraw.getI(),elementToDraw.getJ());
        }
    }

    @Override
    public Iterator<MatrixElement> iterator() {
        return new Iterator<MatrixElement>() {

            Iterator<MatrixElement> originalIterator = originalMatrix.iterator();

            @Override
            public boolean hasNext() {
                return originalIterator.hasNext();
            }

            @Override
            public MatrixElement next() {
                MatrixElement matrixElement = originalIterator.next();
                MatrixElement decoratedMatrixElement = new MatrixElement(matrixElement.getI(), matrixElement.getJ(),
                        getValue(matrixElement.getI(), matrixElement.getJ()),
                        getElementString(matrixElement.getI(), matrixElement.getJ()));
                return decoratedMatrixElement;
            }
        };
    }
}
