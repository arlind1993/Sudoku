package com.company.fileAndJSON;

import java.util.ArrayList;
import java.util.List;

import com.company.Methods;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructureClass{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private ArrayList<Integer> size = null;
    @SerializedName("cellInput")
    @Expose
    private ArrayList<ArrayList<CellInput>> cellInput = null;
    @SerializedName("cellSolved")
    @Expose
    private ArrayList<ArrayList<CellSolved>> cellSolved = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StructureClass withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(ArrayList<Integer> size) {
        this.size = size;
    }

    public StructureClass withSize(ArrayList<Integer> size) {
        this.size = size;
        return this;
    }

    public ArrayList<ArrayList<CellInput>> getCellInput() {
        return cellInput;
    }

    public void setCellInput(ArrayList<ArrayList<CellInput>> cellInput) {
        this.cellInput = cellInput;
    }

    public StructureClass withCellInput(ArrayList<ArrayList<CellInput>> cellInput) {
        this.cellInput = cellInput;
        return this;
    }

    public ArrayList<ArrayList<CellSolved>> getCellSolved() {
        return cellSolved;
    }

    public void setCellSolved(ArrayList<ArrayList<CellSolved>> cellSolved) {
        this.cellSolved = cellSolved;
    }

    public StructureClass withCellSolved(ArrayList<ArrayList<CellSolved>> cellSolved) {
        this.cellSolved = cellSolved;
        return this;
    }

    @Override
    public String toString() {
        return "SC{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", cI=" + Methods.formatted2DArrayList(cellInput) +
                ", cS=" + Methods.formatted2DIArrayList(cellSolved) +
                '}';
    }
}
