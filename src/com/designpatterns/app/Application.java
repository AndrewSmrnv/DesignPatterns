package com.designpatterns.app;

import com.designpatterns.decorator.TransposingDecorator;
import com.designpatterns.decorator.swap.ColumnsSwapDecorator;
import com.designpatterns.decorator.swap.RowsSwapDecorator;
import com.designpatterns.decorator.swap.SwapMode;
import com.designpatterns.drawer.*;
import com.designpatterns.matrix.IMatrix;
import com.designpatterns.matrix.RegularMatrix;
import com.designpatterns.matrix.SparseMatrix;
import com.designpatterns.matrix.composite.CompositeMatrix;
import com.designpatterns.matrix.helpers.MatrixInitiator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.designpatterns.app.Constants.Application.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Application {

    private SwapMode swapMode = SwapMode.Rows;
    private int swapParam1 = 0;
    private int swapParam2 = 1;
    private JComboBox swapParam1CBox;
    private JComboBox swapParam2CBox;

    private JFrame mainFrame;
    private IMatrix matrix;
    private IDrawer drawer;
    private JTextArea guiConsole;
    private JLabel checkConsoleLabel;
    private JButton swapBut;

    private MatrixType matrixType = MatrixType.Regular;
    private DisplayMode displayMode = DisplayMode.Graphical;
    private boolean showBorders = true;

    public void buildAndRun() {

        mainFrame = new JFrame();

        JLabel mainLabel = new JLabel("Generate and display Matrix");
        mainLabel.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,30, DEFAULT_BUTTON_WIDTH,50);

        JLabel matrixTypeLabel = new JLabel("Matrix type");
        matrixTypeLabel.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,100, DEFAULT_BUTTON_WIDTH /2,50);

        JComboBox matrixTypeComboBox = new JComboBox(MatrixType.values());
        matrixTypeComboBox.setSelectedIndex(0);
        matrixTypeComboBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            matrixType = MatrixType.getMatrixTypeById(cb.getSelectedIndex());
            matrix = null;
        });
        matrixTypeComboBox.setBounds(MENU_WIDTH/2,110, DEFAULT_COMBO_BOX_WIDTH /2,DEFAULT_COMBO_BOX_HEIGHT);

        JLabel displayModeLabel = new JLabel("Display Mode");
        displayModeLabel.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,140, DEFAULT_BUTTON_WIDTH /2,50);

        JComboBox displayModesComboBox = new JComboBox(DisplayMode.values());
        displayModesComboBox.setSelectedIndex(DisplayMode.Graphical.getId());
        displayModesComboBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            displayMode = DisplayMode.getDisplayMode(cb.getSelectedIndex());
        });
        displayModesComboBox.setBounds(MENU_WIDTH/2,150, DEFAULT_COMBO_BOX_WIDTH /2, DEFAULT_COMBO_BOX_HEIGHT);

        JCheckBox showMatrixBordersCBox = new JCheckBox("Show Matrix Borders");
        showMatrixBordersCBox.setSelected(true);
        showMatrixBordersCBox.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,180, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
        showMatrixBordersCBox.addActionListener(e -> {
            JCheckBox cb = (JCheckBox)e.getSource();
            showBorders = cb.isSelected();
            displayMatrix();
        });

        JButton genBut = new JButton("Generate");
        genBut.addActionListener(e -> generate());
        genBut.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,220, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

        swapBut = new JButton("Swap");
        swapBut.addActionListener(e -> onSwapButtonClicked());
        swapBut.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2,270, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
        swapBut.setEnabled(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(MENU_WIDTH/2 - DEFAULT_BUTTON_WIDTH /2, MAIN_WINDOW_HEIGHT - (2* DEFAULT_BUTTON_HEIGHT + 10), DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
        exitButton.addActionListener(e -> mainFrame.dispose());

        JSeparator buttonMenuSep = new JSeparator(SwingConstants.VERTICAL);
        buttonMenuSep.setBounds(MENU_WIDTH,0,200, MAIN_WINDOW_HEIGHT);

        initConsole();

        checkConsoleLabel = new JLabel("Console display mode was selected.Check the console for the result ...");
        checkConsoleLabel.setVisible(false);
        checkConsoleLabel.setBounds(MENU_WIDTH + 100, GRAPHICAL_CONSOLE_HEIGHT /2, GRAPHICAL_CONSOLE_WIDTH, 50);

        mainFrame.add(mainLabel);
        mainFrame.add(matrixTypeLabel);
        mainFrame.add(matrixTypeComboBox);
        mainFrame.add(displayModeLabel);
        mainFrame.add(displayModesComboBox);
        mainFrame.add(genBut);
        mainFrame.add(swapBut);
        mainFrame.add(showMatrixBordersCBox);
        mainFrame.add(exitButton);
        mainFrame.add(buttonMenuSep);
        mainFrame.add(guiConsole);
        mainFrame.add(checkConsoleLabel);

        mainFrame.setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        mainFrame.setLayout(null);//using no layout managers
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setTitle("Matrix Drawer");
        mainFrame.setVisible(true);

    }

    private void onSwapButtonClicked() {
        swapBut.setEnabled(false);
        setSwapParam1(0);
        setSwapParam2(1);

        JFrame swapWindowFrame = new JFrame();
        swapWindowFrame.setTitle("Choose parameters for swap");
        swapWindowFrame.setSize(SWAP_WINDOW_WIDTH,SWAP_WINDOW_HEIGHT);
        swapWindowFrame.setResizable(false);
        swapWindowFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel swapLabel = new JLabel("You want to swap :");
        swapLabel.setBounds(SWAP_WINDOW_WIDTH/2 - 75,20, 150,25);

        JComboBox swapModeComboBox = new JComboBox(SwapMode.values());
        swapModeComboBox.setSelectedIndex(0);
        swapModeComboBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            setSwapMode(SwapMode.getSwapModeById(cb.getSelectedIndex()));
        });
        swapModeComboBox.setBounds(20,60,80,25);

        JLabel withIndexesLabel = new JLabel("with indexes");
        withIndexesLabel.setBounds(110,60,80 ,25);

        swapParam1CBox = new JComboBox(getArrayOfRowIndexes());
        swapParam1CBox.setSelectedIndex(0);
        swapParam1CBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            setSwapParam1(cb.getSelectedIndex());
        });
        swapParam1CBox.setBounds(190,60,35,25);


        JLabel andLabel = new JLabel("and");
        andLabel.setBounds(230  ,60, 30,25);

        swapParam2CBox = new JComboBox(getArrayOfRowIndexes());
        swapParam2CBox.setSelectedIndex(1);
        swapParam2CBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            setSwapParam2(cb.getSelectedIndex());
        });
        swapParam2CBox.setBounds(260,60,35,25);

        JButton okBut = new JButton("Ok");
        okBut.addActionListener(e -> { doSwap();});
        okBut.setBounds(SWAP_WINDOW_WIDTH - 200,SWAP_WINDOW_HEIGHT - 80,70,25);

        JButton cancelSwapBut = new JButton("Cancel");
        cancelSwapBut.addActionListener(e -> { swapWindowFrame.dispose();});
        cancelSwapBut.setBounds(SWAP_WINDOW_WIDTH - 120,SWAP_WINDOW_HEIGHT - 80,75,25);

        swapWindowFrame.setLayout(null);
        swapWindowFrame.add(swapLabel);
        swapWindowFrame.add(swapModeComboBox);
        swapWindowFrame.add(withIndexesLabel);
        swapWindowFrame.add(swapParam1CBox);
        swapWindowFrame.add(andLabel);
        swapWindowFrame.add(swapParam2CBox);
        swapWindowFrame.add(cancelSwapBut);
        swapWindowFrame.add(okBut);


        swapWindowFrame.setLocationRelativeTo(mainFrame);
        swapWindowFrame.setLocation(MAIN_WINDOW_WIDTH*2/3,MAIN_WINDOW_HEIGHT*2/3);
        swapWindowFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        swapWindowFrame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {
                swapBut.setEnabled(true);
                swapMode = SwapMode.Rows;
                swapParam1 = 0;
                swapParam2 = 1;
            }
        });
        swapWindowFrame.setVisible(true);
    }

    private void doSwap() {
        if(SwapMode.Rows.equals(swapMode)) {
            RowsSwapDecorator rowsSwapDecorator = new RowsSwapDecorator(matrix,swapParam1,swapParam2);
            matrix = rowsSwapDecorator;
        }else {
            ColumnsSwapDecorator columnsSwapDecorator = new ColumnsSwapDecorator(matrix,swapParam1,swapParam2);
            matrix = columnsSwapDecorator;
        }
        matrix.draw(drawer);
    }

    private void initConsole() {
        guiConsole = new JTextArea(50,50);
        guiConsole.setBounds(MENU_WIDTH + 50,30, GRAPHICAL_CONSOLE_WIDTH, GRAPHICAL_CONSOLE_HEIGHT);
        guiConsole.setEnabled(false);
        Font font = guiConsole.getFont();
        Font consoleFont = new Font(font.getName(),font.getStyle(), GRAPHICAL_CONSOLE_FONT_SIZE);
        guiConsole.setFont(consoleFont);
    }

    private void generate() {

        switch (displayMode) {
            case Graphical :
                drawer = new GuiDrawer(guiConsole);
                guiConsole.setVisible(true);
                checkConsoleLabel.setVisible(false);
                break;
            default :
                drawer = new ConsoleDrawer();
                guiConsole.setVisible(false);
                checkConsoleLabel.setVisible(true);
                break;
        }

        switch (matrixType) {
            case Sparse :
                matrix = new SparseMatrix(MATRIX_ROWS,MATRIX_COLUMNS);
                MatrixInitiator.fillMatrix(matrix, QUANTITY_OF_NOT_NULL_ELEMENTS, MATRIX_MAX_VALUE);
                break;
            case Composite:
                matrix = createCompositeMatrix();
                break;
            default :
                matrix = new RegularMatrix(MATRIX_ROWS,MATRIX_COLUMNS);
                MatrixInitiator.fillMatrix(matrix, QUANTITY_OF_NOT_NULL_ELEMENTS, MATRIX_MAX_VALUE);
                break;
        }

        displayMatrix();
        swapBut.setEnabled(true);

    }

    private void displayMatrix() {
        if(matrix != null) {
            ((AbstractTextDrawer)drawer).setShowBorders(showBorders);
            matrix.draw(drawer);
        }
    }

    private CompositeMatrix createCompositeMatrix() {
        RegularMatrix matrix1 = new RegularMatrix(5,1);
        SparseMatrix matrix2 = new SparseMatrix(3,3);
        RegularMatrix matrix3 = new RegularMatrix(4,2);

        MatrixInitiator.fillMatrix(matrix1,4,MATRIX_MAX_VALUE);
        MatrixInitiator.fillMatrix(matrix2,6,MATRIX_MAX_VALUE);
        MatrixInitiator.fillMatrix(matrix3,7,MATRIX_MAX_VALUE);

        CompositeMatrix innercompositeMatrix = new CompositeMatrix(matrix1);
        innercompositeMatrix.addMatrix(matrix2);
        innercompositeMatrix.addMatrix(matrix3);

        TransposingDecorator transposingDecorator = new TransposingDecorator(innercompositeMatrix);
        CompositeMatrix compositeMatrix = new CompositeMatrix(transposingDecorator);
        compositeMatrix.addMatrix(matrix1);

        return compositeMatrix;
    }

    private String[] getArrayOfRowIndexes() {
        return getStringArrayByNumber(matrix.getRowsNumber());
    }

    private String[] getArrayOfColIndexes() {
        return getStringArrayByNumber(matrix.getColumnsNumber());
    }

    private String[] getStringArrayByNumber(int num) {
        String[] arr = new String[num];
        for (int i = 0; i < num; i++) {
            arr[i] = String.valueOf(i+1);
        }
        return arr;
    }

    private void setSwapParam1(int swapParam1) {
        this.swapParam1 = swapParam1;
    }

    private void setSwapParam2(int swapParam2) {
        this.swapParam2 = swapParam2;
    }

    private void setSwapMode(SwapMode swapMode) {
        this.swapMode = swapMode;
        if(SwapMode.Rows.equals(swapMode)) {
            swapParam1CBox.setModel(new DefaultComboBoxModel(getArrayOfRowIndexes()));
            swapParam2CBox.setModel(new DefaultComboBoxModel(getArrayOfRowIndexes()));
        }else {
            swapParam1CBox.setModel(new DefaultComboBoxModel(getArrayOfColIndexes()));
            swapParam2CBox.setModel(new DefaultComboBoxModel(getArrayOfColIndexes()));
        }
        swapParam1CBox.setSelectedIndex(0);
        swapParam2CBox.setSelectedIndex(1);
    }

    public void setMatrix(IMatrix matrix) {
        this.matrix = matrix;
    }
}