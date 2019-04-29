package com.designpatterns.drawer;

import com.designpatterns.app.Constants;
import com.designpatterns.matrix.IMatrix;
//import org.apache.commons.lang3.Validate;

import javax.swing.*;

public class GuiDrawer extends AbstractTextDrawer {

    private JTextArea guiOutputElement;
    private String TAB = "    ";


    public GuiDrawer(JTextArea guiOutputElement) {
        this.guiOutputElement = guiOutputElement;
        //Validate.notNull(guiOutputElement,"GUI output element should be defined for drawing");
    }

    @Override
    public void beginDrawing(IMatrix m) {
        super.beginDrawing(m);
        addLabel();
        if (showBorders) {
            drawUpperBorder();
        }
    }

    private void drawUpperBorder() {
        guiOutputElement.append(TAB + drawingHelper.multiplyString(DASH, drawingHelper.getMaxRowLength()));
        nextLine();
    }

    private void drawBottomBorder() {
        guiOutputElement.append(TAB + drawingHelper.multiplyString(DASH, drawingHelper.getMaxRowLength()));
        nextLine();
    }


    private void nextLine() {
        guiOutputElement.append("\n");
    }


    private void performGuiOutput(String content) {
        addLabel();
        /*for (String line : content.split("\n")) {
            guiOutputElement.append(tab + line + "\n");
        }*/
    }

    private void addLabel() {
        guiOutputElement.setText(null);
        guiOutputElement.setText("\n\n");
        guiOutputElement.append(" Graphical output :");
        guiOutputElement.append("\n\n");
    }

    @Override
    public void drawElement(IMatrix m, int i, int j) {
        if(j == 0) {
            guiOutputElement.append(TAB);
        }
        if(showBorders) {
            drawLeftBorder(m,i,j);
        }
        guiOutputElement.append(drawingHelper.beautifyElement(m.getElementString(i,j)));
        if(showBorders) {
            drawRightBorder(m,i,j);
        }
        if(j == m.getColumnsNumber() -1){
            guiOutputElement.append("\n");
        }
    }

    private void drawLeftBorder(IMatrix m,int i,int j) {
        if(j%2 == 0){
            guiOutputElement.append(LEFT_BORDER);
        }
    }

    private void drawRightBorder(IMatrix m,int i,int j) {
        if(j%2 == 0 || j == m.getColumnsNumber() - 1) {
            guiOutputElement.append(RIGHT_BORDER);
        }
    }

    @Override
    public void finishDrawing(IMatrix m) {
        if (showBorders) {
            drawBottomBorder();
        }
    }
}
