package com.company;

import com.company.fileAndJSON.MyFileClass;

import java.io.IOException;
public class Main {

    public static void main(String[] args){
        MyFileClass fc=null;
        try {
            fc=new MyFileClass("input/input_solve.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game game = new Game(fc);
    }
}
