package com.company;

import javax.swing.*;
import java.awt.*;

public class SudokuActionPanel extends JPanel {

    public static final int WIDTH=295;
    public static final int HEIGHT=395;

    JCustomButton [][] actionButtons = new JCustomButton[6][4];

    public SudokuActionPanel(){
        this.setLayout(null);

        for (int i = 0; i < actionButtons.length; i++) {
            for (int j = 0; j < actionButtons[0].length; j++) {
                actionButtons[i][j]= new JCustomButton();

                this.add(actionButtons[i][j]);
            }
        }
        this.setSize(WIDTH,HEIGHT);
        this.setBackground(Color.DARK_GRAY);
    }

}
