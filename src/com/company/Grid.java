package com.company;

import java.util.ArrayList;

public class Grid {
    public static final int SIZE=9;

    private Cell[][] cells= new Cell[SIZE][SIZE];
    public Grid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j]=new Cell(i,j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public ArrayList<Cell> getMarkedCells() {
        ArrayList<Cell> markedCells= new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].isMarked()){
                    markedCells.add(cells[i][j]);
                }
            }
        }
        return markedCells;
    }
}
