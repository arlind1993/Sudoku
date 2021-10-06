package com.company.fileAndJSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CellSolved {
    @SerializedName("fI")
    @Expose
    private Integer fI;

    public Integer getfI() {
        return fI;
    }
    public void setfI(Integer fI) {
        this.fI = fI;
    }
    public CellSolved withfI(Integer fI) {
        this.fI = fI;
        return this;
    }

    @Override
    public String toString() {
        return "CS{" +
                "fI=" + fI +
                '}';
    }
}
