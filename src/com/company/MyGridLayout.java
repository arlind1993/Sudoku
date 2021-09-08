package com.company;

import java.awt.*;

public class MyGridLayout<Type> {

    private int rows;
    private int cols;
    private Type[][] objects;
    private int offsets;

    public MyGridLayout(int rows,int cols, int offsets,  Type[][] objects){
        this.rows=rows;
        this.cols=cols;
        this.offsets=offsets;
        this.objects=objects;
    }

    void setLocationOfType(int posY, int posX){

        Container c=(Container)(objects[posY][posX]);
        System.out.print(posX +","+posY+" = "+(posX*(offsets+c.getWidth())+offsets)+","+(posY*(offsets+c.getHeight())+offsets));
        if (posX>=0&&posX<cols && posY>=0&&posY<rows){

            c.setLocation(posX*(offsets+c.getWidth())+offsets,posY*(offsets+c.getHeight())+offsets);
        }
        System.out.println("       "+c);
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
