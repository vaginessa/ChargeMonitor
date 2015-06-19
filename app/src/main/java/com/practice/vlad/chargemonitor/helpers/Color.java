package com.practice.vlad.chargemonitor.helpers;

/**
 * Created by vlad.itu on 6/19/2015.
 */
public enum Color {
    WHITE       (0xFFFFFFFF),
    RED         (0xFFFF0000),
    GREEN       (0xFF00FF00),
    BLUE        (0xFF0000FF),
    YELLOW      (0xFFFFFF00),
    CYAN        (0xFF00FFFF),
    MAGENTA     (0xFFFF00FF);

    private int color;

    Color (int color) {
        this.color = color;
    }

    public int getValue() {
        return color;
    }

    public static int getPosition(int color) {
        for (int i = 0; i < Color.values().length; i++) {
            if (Color.values()[i].getValue() == color)
                return i;
        }
        return 0;
    }

    public static Color getColorByPosition(int position) {
        return Color.values()[position];
    }

}
