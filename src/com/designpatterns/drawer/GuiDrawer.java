package com.designpatterns.drawer;

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
    public void prepare(IMatrix matrix) {
        //System.out.println("Starting GuiDrawer...");
        super.prepare(matrix);
    }

    @Override
    public void finish(IMatrix matrix) {
        //System.out.println("GuiDrawer has finished it's work");
        super.finish(matrix);
    }

    @Override
    public void drawMatrix(IMatrix matrix) {
       /* System.out.println();
        System.out.println("GuiDrawer is drawing the matrix : ");*/
        checkAndPerformGuiOutput();
    }

    private void checkAndPerformGuiOutput() {
        if(consoleOutput != null && consoleOutput.length() != 0) {
            performGuiOutput(consoleOutput.toString());
        } else {
            performGuiOutput("Content for Gui console is empty");
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
