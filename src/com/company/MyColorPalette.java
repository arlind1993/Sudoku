package com.company;

import java.awt.*;
import java.awt.color.ColorSpace;

public class MyColorPalette extends Color {
    public static final Color PASTEL_VIOLET = new Color(210, 180, 200);
    public static final Color LAVENDER = new Color(200, 150, 210);
    public static final Color DARK_VIOLET = new Color(40, 25, 40);
    public static final Color ORCHID = new Color( 100, 50, 100);

    public MyColorPalette(int r, int g, int b) {
        super(r, g, b);
    }
    public MyColorPalette(int r, int g, int b, int a) {
        super(r, g, b, a);
    }
    public MyColorPalette(int rgb) {
        super(rgb);
    }
    public MyColorPalette(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }
    public MyColorPalette(float r, float g, float b) {
        super(r, g, b);
    }
    public MyColorPalette(float r, float g, float b, float a) {
        super(r, g, b, a);
    }
    public MyColorPalette(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }
}
