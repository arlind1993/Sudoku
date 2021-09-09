package com.company;

import javax.swing.*;
import java.awt.*;

public class JGrid extends JPanel {
    public static final int LINE_WIDTH=5;
    public static final int SIZE_PER_CELL = 60;
    public static final int SIZE=SIZE_PER_CELL*Grid.SIZE+LINE_WIDTH*(Grid.SIZE+1);//590
    private Grid grid;
    private JGridMouseListener jgml;
    private boolean markPenActivated = false;
    public JGrid(){
        this.setLayout(null);
        this.setSize(SIZE,SIZE);
        this.setBackground(Color.WHITE);

        grid = new Grid();
        System.out.println(SIZE);


        jgml = new JGridMouseListener(this);
        this.addMouseListener(jgml);
        this.addMouseMotionListener(jgml);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoundaries(g);
        drawMarkedCells(g);

    }

    private void drawMarkedCells(Graphics g) {
        g.setColor(MyColorPalette.LIGHT_BLUE);
        for (Cell markedCell: grid.getMarkedCells()) {
            g.fillRect(LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getCol(),
                    LINE_WIDTH+(SIZE_PER_CELL+LINE_WIDTH)*markedCell.getRow(),
                    SIZE_PER_CELL,SIZE_PER_CELL);
        }
    }

    private void drawBoundaries(Graphics g) {
        for (int i = 0; i <= SIZE; i++) {
            g.setColor(Color.LIGHT_GRAY);
            if (i%3==1||i%3==2){
                g.fillRect(0,i*(SIZE_PER_CELL+LINE_WIDTH),SIZE,LINE_WIDTH);
                g.fillRect(i*(SIZE_PER_CELL+LINE_WIDTH),0,LINE_WIDTH,SIZE);
            }

        }
        for (int i = 0; i < SIZE; i++) {
            g.setColor(Color.BLACK);
            if(i%3==0) {
                g.fillRect(0, i * (SIZE_PER_CELL + LINE_WIDTH), SIZE, LINE_WIDTH);
                g.fillRect(i * (SIZE_PER_CELL + LINE_WIDTH), 0, LINE_WIDTH, SIZE);
            }
        }

    }

    public Grid getGrid() {
        return grid;
    }

    public boolean isMarkPenActivated() {
        return markPenActivated;
    }

    public void setMarkPenActivated(boolean markPenActivated) {
        this.markPenActivated = markPenActivated;
    }
}
