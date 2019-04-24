package com.designpatterns.app.command;

import com.designpatterns.matrix.IMatrix;
import org.apache.commons.lang3.Validate;

public class DoRandomChangeCommand extends AbstractMatrixCommand {

    int xCoord;
    int yCoord;
    double oldValue;
    double newValue;


    public DoRandomChangeCommand(IMatrix matrix,int xCoord,int yCoord,double newValue) {
        super(matrix);
        Validate.isTrue(xCoord > 0 &&
                        yCoord > 0 &&
                        xCoord < matrix.getColumnsNumber() &&
                        yCoord < matrix.getRowsNumber(), "Incorrect coordinates to perform the command");
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        saveOldValue();
    }

    private void saveOldValue() {
        oldValue = matrix.getValue(xCoord,yCoord);
    }

    @Override
    public void execute() {
        matrix.setValue(xCoord,yCoord,newValue);
    }

    @Override
    public void unExecute() {
        matrix.setValue(xCoord,yCoord,oldValue);
    }


}
