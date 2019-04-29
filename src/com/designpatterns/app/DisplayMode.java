package com.designpatterns.app;


import java.util.HashMap;
import java.util.Map;

public enum DisplayMode {

    Console(0),
    Graphical(1);

    private final int id;

    private static final Map<Integer, DisplayMode> lookup = new HashMap<>();

    static {
        for (DisplayMode mType : DisplayMode.values()) {
            lookup.put(mType.getId(), mType);
        }
    }

    DisplayMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DisplayMode getDisplayMode(int id) {
        return lookup.get(id);
    }
}
