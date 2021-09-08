package com.company;

public class Grid {
    public static final int SIZE=9;

    private Cell[][] cells= new Cell[SIZE][SIZE];
    public Grid(){

    }

    public Cell[][] getCells() {
        return cells;
    }

}
