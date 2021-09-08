package com.company;

import java.awt.*;

public class MyGridLayout<Type> {

    private int rows;
    private int cols;

    private Type[][] objects;
    private int offsets;

    public MyGridLayout(int rows,int cols, int offsets, Type[][] objects){
        this.rows=rows;
        this.cols=cols;
        this.offsets=offsets;
    }

    void setLocationOfType(int posY, int posX){
        if (posX>0&&posX<rows
        && posY>0&&posY<cols){
            Container c=(Container)(objects[posY][posX]);
            c.setLocation(posX*(offsets+JCustomButton.SIZE),posY*(offsets+JCustomButton.SIZE));
        }

    }

    public Type[][] getObject() {
        return objects;
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    public void setObjects(Type [][] objects) {
        this.objects = objects;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setCols(int cols) {
        this.cols = cols;
    }
}
