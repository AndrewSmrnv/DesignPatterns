package com.designpatterns.app.command;

import com.designpatterns.matrix.IMatrix;

public abstract class AbstractMatrixCommand implements Command{

    IMatrix matrix;

    public AbstractMatrixCommand(IMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    abstract public void execute();

    @Override
    abstract public void unExecute();
}
