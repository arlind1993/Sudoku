package com.company;


import com.company.fileAndJSON.CellInput;
import com.company.fileAndJSON.CellSolved;

import java.util.ArrayList;

public class Methods {

    public static String formatted2DArrayList(ArrayList<ArrayList<CellInput>> cellInput){
        StringBuilder sb=new StringBuilder();

        sb.append("[\n");
        for (int i = 0; i < cellInput.size(); i++) {
            sb.append("\t[\n");
            for (int j = 0; j < cellInput.get(i).size(); j++) {
                sb.append("\t\t");
                sb.append(cellInput.get(i).get(j));
                if (j != cellInput.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            sb.append("\t]");
            if (i != cellInput.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    public static String formatted2DIArrayList(ArrayList<ArrayList<CellSolved>> cellSolved) {
        StringBuilder sb=new StringBuilder();

        sb.append("[\n");
        for (int i = 0; i < cellSolved.size(); i++) {
            sb.append("\t[\n");
            for (int j = 0; j < cellSolved.get(i).size(); j++) {
                sb.append("\t\t");
                sb.append(cellSolved.get(i).get(j));
                if (j != cellSolved.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            sb.append("\t]");
            if (i != cellSolved.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
