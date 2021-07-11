package com.example.folha.entity;

public class Main
{
    public static void main(String[] args) {
        Abc a = Abc.getInstance();
        Abc b = Abc.getInstance();

        System.out.println(a);
        System.out.println(b);
    }
}
