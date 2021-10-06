package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Cell {
    public static final int COLOR_LIMIT = 4;
    public static final int CORNER_LIMIT = 8;
    public static final int CENTER_LIMIT = 10;

    private int row;
    private int col;
    private int box;
    private boolean marked=false;
    private Integer finalDigit = null;
    private ArrayList<Integer> centerDigits = new ArrayList<>();
    private ArrayList<Integer> cornerDigits = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private ArrayList<Integer> extra = new ArrayList<>();

    public Cell(int row, int col){
        this.row=row;
        this.col=col;
        this.box=3*(row/3)+col/3;
    }

    public Cell(Cell other) {
        this(other.getRow(), other.getCol());
        marked=other.marked;
        finalDigit=other.finalDigit;
        centerDigits.addAll(other.centerDigits);
        cornerDigits.addAll(other.cornerDigits);
        colors.addAll(other.colors);
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
        if (cornerDigits.size()>=CENTER_LIMIT){
            cornerDigits.remove(0);
        }
        centerDigits.add(centerDigit);
        Collections.sort(centerDigits);
    }
    public void removeCenterDigit(Integer centerDigit) {
        centerDigits.remove(centerDigit);
    }
    public void addCornerDigit(Integer cornerDigit) {

        if (cornerDigits.size()>=CORNER_LIMIT){
            cornerDigits.remove(0);
        }
        cornerDigits.add(cornerDigit);
        Collections.sort(cornerDigits);
    }
    public void removeCornerDigit(Integer cornerDigit) {
        cornerDigits.remove(cornerDigit);
    }
    public void addColor(Color color) {
        if (colors.size()>=COLOR_LIMIT){
            colors.remove(0);
        }
            colors.add(color);
    }
    public void removeColor(Color color) {
        colors.remove(color);
    }

    public void setExtra(ArrayList<Integer> extra) {
        this.extra = extra;
    }

    public ArrayList<Integer> getExtra() {
        return extra;
    }

    public void setCornerDigits(ArrayList<Integer> cornerDigits) {
        this.cornerDigits = cornerDigits;
    }

    public void setCenterDigits(ArrayList<Integer> centerDigits) {
        this.centerDigits = centerDigits;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "[" + row +
                "][" + col +
                "], box=" + box +
                ", isMa=" + (marked?"T":"F") +
                ", fD=" + finalDigit +
                ", ceD=" + centerDigits +
                ", coD=" + cornerDigits +
                ", col=" + colors +
                '}';
    }

}
