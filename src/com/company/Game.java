package com.company;

import com.company.fileAndJSON.MyFileClass;

import javax.swing.*;
public class Game extends JFrame {
    private MyFileClass fc;
    public Game() {
        this(null);
    }
    Game(MyFileClass fc){
        this.fc=fc;
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new ContextPane(fc));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



}
