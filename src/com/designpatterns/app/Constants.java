package com.designpatterns.app;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public interface Constants {

    class Application {
        public static final int MAIN_WINDOW_WIDTH = 1000;
        public static int MAIN_WINDOW_HEIGHT = 500;

        public static int SWAP_WINDOW_WIDTH = 350;
        public static int SWAP_WINDOW_HEIGHT = 200;

        public static int DEFAULT_BUTTON_WIDTH = 180;
        public static int DEFAULT_BUTTON_HEIGHT = 40;

        public static int DEFAULT_COMBO_BOX_WIDTH = 180;
        public static int DEFAULT_COMBO_BOX_HEIGHT = 30;

        public static int MENU_WIDTH = 250;

        public static int GRAPHICAL_CONSOLE_WIDTH =  650;
        public static int GRAPHICAL_CONSOLE_HEIGHT = 400;
        public static int GRAPHICAL_CONSOLE_FONT_SIZE = 21;

        public static int MATRIX_ROWS = 4;
        public static int MATRIX_COLUMNS = 5;
        public static int MATRIX_MAX_VALUE = 50;
        public static int QUANTITY_OF_NOT_NULL_ELEMENTS = 15;
    }

    enum EmptyValues {

        RegularVector(0.0),
        SparseVector(-99.99),
        CompositeMatrix(-999.999);

        private final double value;

        EmptyValues(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public static Set<Double> getValues() {
            return Arrays.stream(EmptyValues.values()).map(EmptyValues::getValue).collect(Collectors.toSet());
        }
    }
}
