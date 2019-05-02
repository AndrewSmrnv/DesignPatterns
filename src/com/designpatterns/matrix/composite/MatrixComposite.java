package com.designpatterns.matrix.composite;

import com.designpatterns.drawer.IDrawer;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.MatrixElement;

import java.util.Iterator;

public class MatrixComposite implements IMatrix {

    private IMatrix parent;
    private IMatrix left;
    private IMatrix right;

    private int rows;
    private int cols;

    @Override
    public int getRowsNumber() {
        return rows;
    }

    @Override
    public int getColumnsNumber() {
        return cols;
    }

    @Override
    public double getValue(int rowIndex, int colIndex) {

        if(left == null && right == null) {
            throw new IllegalStateException("composite is empty");
        }

        if(left != null) {
            if(colIndex < left.getColumnsNumber()) {
                System.out.println("going left by columns");
                if(rowIndex < left.getRowsNumber()) {
                    System.out.println("getting form left");
                    return left.getValue(rowIndex,colIndex);
                }
            }
        }
        System.out.println("getting form right");
        return right.getValue(rowIndex,colIndex);
    }

    @Override
    public void setValue(int rowIndex, int colIndex, double value) {

    }

    @Override
    public String getElementString(int rowIndex, int colIndex) {
        return null;
    }

    @Override
    public void draw(IDrawer drawer) {

    }

    @Override
    public Iterator<MatrixElement> iterator() {
        return null;
    }

    public void addRight(IMatrix matrix) {
        if(left == null) {
            left = matrix;
        } else if(right == null) {
            right = matrix;
        } else {
            MatrixComposite mc = new MatrixComposite();
            mc.addRight(matrix);
            right = mc;
        }
        /*if(matrix.getColumnsNumber() > left.getColumnsNumber()) {
        }*/

        cols += matrix.getColumnsNumber();
    }

    public void addBottom(IMatrix matrix) {
        if(left == null) {
            left = matrix;
        } else if(right == null) {
            right = matrix;
        } else {
            MatrixComposite mc = new MatrixComposite();
            mc.addBottom(matrix);
            left = mc;
        }
        rows += matrix.getRowsNumber();
    }

}
