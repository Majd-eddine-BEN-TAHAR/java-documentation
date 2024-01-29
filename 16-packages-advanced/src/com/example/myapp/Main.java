package com.example.myapp;

import com.example.utils.MathUtils; // Import from a different package

public class Main {
    public static void main(String[] args) {
        // Using MathUtils from com.example.utils
        int sum = MathUtils.add(10, 20);

        // Using Util from the same package
        Util.printMessage("Hello from Main");

        System.out.println("Sum is: " + sum);
    }
}