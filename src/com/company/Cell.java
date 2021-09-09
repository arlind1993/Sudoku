package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private int row;
    private int col;
    private int box;
    private boolean marked=false;
    private Integer finalDigit = null;
    private ArrayList<Integer> centerDigits = new ArrayList<>();
    private ArrayList<Integer> cornerDigits = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();

    public Cell(int row, int col){
        this.row=row;
        this.col=col;
        this.box=3*(row/3)+col/3;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getBox() {
        return box;
    }
    public boolean isMarked() {
        return marked;
    }
    public Integer getFinalDigit() {
        return finalDigit;
    }
    public ArrayList<Integer> getCenterDigits() {
        return centerDigits;
    }
    public ArrayList<Integer> getCornerDigits() {
        return cornerDigits;
    }
    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setFinalDigit(Integer finalDigit) {
        this.finalDigit = finalDigit;
    }
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    public void addCenterDigit(Integer centerDigit) {
        centerDigits.add(centerDigit);
    }
    public void removeCenterDigit(Integer centerDigit) {
        centerDigits.remove(centerDigit);
    }
    public void addCornerDigit(Integer cornerDigit) {
        centerDigits.add(cornerDigit);
    }
    public void removeCornerDigit(Integer cornerDigit) {
        centerDigits.remove(cornerDigit);
    }
    public void addColor(Color color) {
        colors.add(color);
    }
    public void removeColor(Color color) {
        colors.remove(color);
    }

}
