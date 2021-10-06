package com.company;

import com.company.fileAndJSON.GsonClass;
import com.company.fileAndJSON.MyFileClass;

import javax.swing.*;
import java.awt.*;

public class ContextPane extends JPanel {

    public final static int WIDTH = 900;
    public final static int HEIGHT = 600;

    private JGrid jGrid;
    private SudokuActionPanel sap;
    private MyFileClass fc;
    private ContextPaneKeyboardListener cpkl;
    private GsonClass gc=null;

    public ContextPane(){
        this(null);
    }

    public ContextPane(MyFileClass fc){
        this.fc=fc;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        if(fc!=null){
            gc=new GsonClass(fc.readBRString());
            jGrid=new JGrid(gc.getSc().getCellInput());
        }else {
            jGrid=new JGrid();
        }
        jGrid.setLocation(5,5);
        sap=new SudokuActionPanel(jGrid);
        sap.setLocation(630,200);

        this.add(jGrid);
        this.add(sap);
        this.setFocusable(true);
        cpkl=new ContextPaneKeyboardListener(this);
        this.addKeyListener(cpkl);
    }

    public MyFileClass getFc() {
        return fc;
    }
    public ContextPaneKeyboardListener getCpkl() {
        return cpkl;
    }
    public SudokuActionPanel getSap() {
        return sap;
    }
    public JGrid getJGrid() {
        return jGrid;
    }
}
