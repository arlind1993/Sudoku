package com.company.fileAndJSON;

import com.google.gson.*;

public class GsonClass {
    Gson gson;
    String jsonInput;
    StructureClass sc;
    public GsonClass(String jsonInput){
        this.jsonInput=jsonInput;
        gson=new Gson();
        sc = gson.fromJson(jsonInput,StructureClass.class);
        System.out.println(sc.toString());
    }

    public StructureClass getSc() {
        return sc;
    }
}
