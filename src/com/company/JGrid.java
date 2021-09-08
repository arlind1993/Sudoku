package com.company;

import javax.swing.*;
import java.awt.*;

public class JGrid extends JPanel {
    public static final int LINE_WIDTH=5;
    public static final int SIZE_PER_CELL = 60;
    public static final int SIZE=SIZE_PER_CELL*Grid.SIZE+LINE_WIDTH*(Grid.SIZE+1);//590
    private Grid grid;

    private JGridMouseListener jgml;
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
}
