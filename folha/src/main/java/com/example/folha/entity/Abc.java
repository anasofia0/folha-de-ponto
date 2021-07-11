package com.example.folha.entity;

public class Abc {

    private static Abc abc;

    private Abc(){

    }

    public static Abc getInstance() {
        if(abc == null) {
            abc = new Abc();
        }
        return abc;
    }
}
