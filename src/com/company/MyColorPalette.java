package com.company;

import java.awt.*;
import java.awt.color.ColorSpace;

public class MyColorPalette extends Color {
    public static final Color PASTEL_VIOLET = new Color(210, 180, 200);
    public static final Color LAVENDER = new Color(200, 150, 210);
    public static final Color DARK_VIOLET = new Color(40, 25, 40);
    public static final Color ORCHID = new Color( 100, 50, 100);
    public static final Color LIME = new Color(80, 200, 80);
    public static final Color LIGHT_BLUE = new Color(100, 180, 200);
    public static final Color LIGHT_CYAN = new Color(126,183,248);
    public static final Color TRANSPARENT = new Color(0,0,0, 0);


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

    public static Color cellColoring(int id){
        switch (id){
            case 1: return LIGHT_GRAY;
            case 2: return GRAY;
            case 3: return BLACK;
            case 4: return LIME;
            case 5: return LAVENDER;
            case 6: return ORANGE;
            case 7: return RED;
            case 8: return YELLOW;
            case 9: return LIGHT_CYAN;
            case 0: return TRANSPARENT;
            default: return null;
        }
    }
}
