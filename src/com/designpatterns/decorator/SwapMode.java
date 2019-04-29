package com.designpatterns.decorator;

import java.util.HashMap;
import java.util.Map;

public enum SwapMode {

    Rows(0),
    Columns(1);

    private final int id;

    private static final Map<Integer, SwapMode> lookup = new HashMap<>();

    SwapMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    static {
        for (SwapMode mode : SwapMode.values()) {
            lookup.put(mode.getId(), mode);
        }
    }

    public static SwapMode getSwapModeById(int id) {
        return lookup.get(id);
    }

}
