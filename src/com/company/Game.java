package com.company;

import javax.swing.*;
public class Game extends JFrame {

    Game(){
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new ContextPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}
