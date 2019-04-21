package com.designpatterns.drawer;

import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;
import org.apache.commons.lang3.Validate;

import javax.swing.*;

public class GuiDrawer extends AbstractTextDrawer {

    private JTextArea guiOutputElement;

    public GuiDrawer(JTextArea guiOutputElement) {
        this.guiOutputElement = guiOutputElement;
        Validate.notNull(guiOutputElement,"GUI output element should be defined for drawing");
    }

    @Override
    protected void executeDrawing(IMatrix matrix) {
        checkAndPerformGuiOutput();
    }

    private void checkAndPerformGuiOutput() {
        if(consoleOutput != null && consoleOutput.length() != 0) {
            performGuiOutput(consoleOutput.toString());
        } else {
            performGuiOutput("Content for Gui console is empty");
        }
    }

    @Override
    protected String getAlignedElement(double value) {
        if(Constants.EmptyValues.getValues().contains(value)) {
            return " " + super.getAlignedElement(value) + " ";
        }else {
            return super.getAlignedElement(value);

           /* String element = super.getAlignedElement(value);
            String leftPart = element.split("\\.")[0];
            String rightPart = element.split("\\.")[1];

            int leftPartNumbers =  drawingHelper.getMaxSymbolsLeft() - leftPart.trim().length();
            int rightPartNumbers = drawingHelper.getMaxSymbolsRight() - rightPart.trim().length();

            if(value > 0) leftPartNumbers --;

            return DrawingHelper.getNSpaces(leftPartNumbers) + element  + DrawingHelper.getNSpaces(rightPartNumbers);*/
        }
    }

    private void performGuiOutput(String content) {
        guiOutputElement.setText(null);
        guiOutputElement.setText("\n\n");
        guiOutputElement.append(" Graphical output :");
        guiOutputElement.append("\n\n");

        String tab = "    ";
        for (String line : content.split("\n")) {
            guiOutputElement.append(tab + line + "\n");
        }
    }

}
