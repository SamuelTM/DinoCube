package com.samueltm.dinocube;

import java.awt.*;

public enum FaceColor {

    ORANGE(new Color(239,104,3)),
    BLUE(new Color(52,115,208)),
    WHITE(Color.WHITE),
    GREEN(new Color(41,175,41)),
    YELLOW(new Color(242,210,6)),
    RED(new Color(191,40,50));

    private final Color color;

    FaceColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
