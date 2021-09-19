package com.company;

import javax.swing.*;
import java.awt.*;

public class ContextPane extends JPanel {

    public final static int WIDTH = 900;
    public final static int HEIGHT = 600;

    private JGrid jGrid;
    private SudokuActionPanel sap;

    private ContextPaneKeyboardListener cpkl;

    public ContextPane(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jGrid=new JGrid();
        jGrid.setLocation(5,5);
        sap=new SudokuActionPanel(jGrid);
        sap.setLocation(630,200);

        this.add(jGrid);
        this.add(sap);
        this.setFocusable(true);
        cpkl=new ContextPaneKeyboardListener(this);
        this.addKeyListener(cpkl);
    }

    public ContextPaneKeyboardListener getCpkl() {
        return cpkl;
    }

    public SudokuActionPanel getSap() {
        return sap;
    }
}
