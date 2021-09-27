package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    public static final int SIZE=9;
    private Cell[][] cells= new Cell[SIZE][SIZE];

    private Cell lastCellMarked = null;

    public Grid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j]=new Cell(i,j);
            }
        }
    }

    public static void checkGrid(Grid Grid) {

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


    public Cell getLastCellMarked() {
        return lastCellMarked;
    }

    public void setLastCellMarked(Cell lastCellMarked) {
        if (lastCellMarked!=null) {
            lastCellMarked.setMarked(true);
        }
        this.lastCellMarked = lastCellMarked;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }
}
