package com.company.fileAndJSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.awt.*;
import java.util.ArrayList;

public class CellInput {
    @SerializedName("fI")
    @Expose
    private Integer fI;
    @SerializedName("ceD")
    @Expose
    private ArrayList<Integer> centerDigits = new ArrayList<>();
    @SerializedName("coD")
    @Expose
    private ArrayList<Integer> cornerDigits = new ArrayList<>();
    @SerializedName("cl")
    @Expose
    private ArrayList<Color> colors = new ArrayList<>();
    @SerializedName("ex")
    @Expose
    private ArrayList<Integer> extra = new ArrayList<>();

    public CellInput withfI(Integer fI) {
        this.fI=fI;
        return this;
    }
    public Integer getfI() {
        return fI;
    }
    public void setfI(Integer fI) {
        this.fI = fI;
    }


    public CellInput withCenterDigits(ArrayList<Integer> centerDigits) {
        this.centerDigits=centerDigits;
        return this;
    }
    public ArrayList<Integer> getCenterDigits() {
        return centerDigits;
    }
    public void setCenterDigits(ArrayList<Integer> centerDigits) {
        this.centerDigits = centerDigits;
    }

    public CellInput withCornerDigits(ArrayList<Integer> cornerDigits) {
        this.cornerDigits=cornerDigits;
        return this;
    }
    public ArrayList<Integer> getCornerDigits() {
        return cornerDigits;
    }
    public void setCornerDigits(ArrayList<Integer> cornerDigits) {
        this.cornerDigits = cornerDigits;
    }

    public CellInput withColors(ArrayList<Color> colors) {
        this.colors=colors;
        return this;
    }
    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    public CellInput withExtra(ArrayList<Integer> extra) {
        this.extra=extra;
        return this;
    }
    public ArrayList<Integer> getExtra() {
        return extra;
    }
    public void setExtra(ArrayList<Integer> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "CI{" +
                "fI=" + fI +
                ", ceD=" + centerDigits +
                ", coD=" + cornerDigits +
                ", cl=" + colors +
                ", ex=" + extra +
                '}';
    }
}
