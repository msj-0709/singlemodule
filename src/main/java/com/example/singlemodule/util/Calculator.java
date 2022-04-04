package com.example.singlemodule.util;

public class Calculator {
    private int a1;
    private int a2;

    public Calculator(int a1, int a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public int plus() {
        return a1 + a2;
    }

}
